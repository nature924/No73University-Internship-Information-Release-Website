
package com.controller;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;
import com.service.TokenService;
import com.utils.*;
import java.lang.reflect.InvocationTargetException;

import com.service.DictionaryService;
import org.apache.commons.lang3.StringUtils;
import com.annotation.IgnoreAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.*;
import com.entity.view.*;
import com.service.*;
import com.utils.PageUtils;
import com.utils.R;
import com.alibaba.fastjson.*;

/**
 * 实习记录
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/shixijilu")
public class ShixijiluController {
    private static final Logger logger = LoggerFactory.getLogger(ShixijiluController.class);

    private static final String TABLE_NAME = "shixijilu";

    @Autowired
    private ShixijiluService shixijiluService;


    @Autowired
    private TokenService tokenService;

    @Autowired
    private DictionaryService dictionaryService;//字典表
    @Autowired
    private ForumService forumService;//论坛
    @Autowired
    private GonggaoService gonggaoService;//公告
    @Autowired
    private JianliService jianliService;//简历
    @Autowired
    private QiyeService qiyeService;//企业
    @Autowired
    private ShixizhiweiService shixizhiweiService;//职位实习
    @Autowired
    private ShixizhiweiCollectionService shixizhiweiCollectionService;//职位收藏
    @Autowired
    private ShixizhiweiLiuyanService shixizhiweiLiuyanService;//职位留言
    @Autowired
    private ShixizhiweiToudiService shixizhiweiToudiService;//实习申请
    @Autowired
    private YonghuService yonghuService;//用户
    @Autowired
    private UsersService usersService;//管理员


    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永不会进入");
        else if("用户".equals(role))
            params.put("yonghuId",request.getSession().getAttribute("userId"));
        else if("企业".equals(role))
            params.put("qiyeId",request.getSession().getAttribute("userId"));
        CommonUtil.checkMap(params);
        PageUtils page = shixijiluService.queryPage(params);

        //字典表数据转换
        List<ShixijiluView> list =(List<ShixijiluView>)page.getList();
        for(ShixijiluView c:list){
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c, request);
        }
        return R.ok().put("data", page);
    }

    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("info方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        ShixijiluEntity shixijilu = shixijiluService.selectById(id);
        if(shixijilu !=null){
            //entity转view
            ShixijiluView view = new ShixijiluView();
            BeanUtils.copyProperties( shixijilu , view );//把实体数据重构到view中
            //级联表 用户
            //级联表
            YonghuEntity yonghu = yonghuService.selectById(shixijilu.getYonghuId());
            if(yonghu != null){
            BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "username", "password", "newMoney", "yonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setYonghuId(yonghu.getId());
            }
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody ShixijiluEntity shixijilu, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,shixijilu:{}",this.getClass().getName(),shixijilu.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("用户".equals(role))
            shixijilu.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        Wrapper<ShixijiluEntity> queryWrapper = new EntityWrapper<ShixijiluEntity>()
            .eq("yonghu_id", shixijilu.getYonghuId())
            .eq("shixijilu_name", shixijilu.getShixijiluName())
            .eq("shixizhiwei_types", shixijilu.getShixizhiweiTypes())
            .eq("danwei_address", shixijilu.getDanweiAddress())
            .eq("danwei_lianxiren", shixijilu.getDanweiLianxiren())
            .eq("danwei_lianxi_phone", shixijilu.getDanweiLianxiPhone())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ShixijiluEntity shixijiluEntity = shixijiluService.selectOne(queryWrapper);
        if(shixijiluEntity==null){
            shixijilu.setInsertTime(new Date());
            shixijilu.setCreateTime(new Date());
            shixijiluService.insert(shixijilu);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody ShixijiluEntity shixijilu, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,shixijilu:{}",this.getClass().getName(),shixijilu.toString());
        ShixijiluEntity oldShixijiluEntity = shixijiluService.selectById(shixijilu.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("用户".equals(role))
//            shixijilu.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        if("".equals(shixijilu.getShixijiluContent()) || "null".equals(shixijilu.getShixijiluContent())){
                shixijilu.setShixijiluContent(null);
        }

            shixijiluService.updateById(shixijilu);//根据id更新
            return R.ok();
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<ShixijiluEntity> oldShixijiluList =shixijiluService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        shixijiluService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }


    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName, HttpServletRequest request){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        Integer yonghuId = Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId")));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //.eq("time", new SimpleDateFormat("yyyy-MM-dd").format(new Date()))
        try {
            List<ShixijiluEntity> shixijiluList = new ArrayList<>();//上传的东西
            Map<String, List<String>> seachFields= new HashMap<>();//要查询的字段
            Date date = new Date();
            int lastIndexOf = fileName.lastIndexOf(".");
            if(lastIndexOf == -1){
                return R.error(511,"该文件没有后缀");
            }else{
                String suffix = fileName.substring(lastIndexOf);
                if(!".xls".equals(suffix)){
                    return R.error(511,"只支持后缀为xls的excel文件");
                }else{
                    URL resource = this.getClass().getClassLoader().getResource("static/upload/" + fileName);//获取文件路径
                    File file = new File(resource.getFile());
                    if(!file.exists()){
                        return R.error(511,"找不到上传文件，请联系管理员");
                    }else{
                        List<List<String>> dataList = PoiUtil.poiImport(file.getPath());//读取xls文件
                        dataList.remove(0);//删除第一行，因为第一行是提示
                        for(List<String> data:dataList){
                            //循环
                            ShixijiluEntity shixijiluEntity = new ShixijiluEntity();
//                            shixijiluEntity.setYonghuId(Integer.valueOf(data.get(0)));   //用户 要改的
//                            shixijiluEntity.setShixijiluUuidNumber(data.get(0));                    //实习记录编号 要改的
//                            shixijiluEntity.setShixijiluName(data.get(0));                    //实习单位名称 要改的
//                            shixijiluEntity.setShixizhiweiTypes(Integer.valueOf(data.get(0)));   //实习职位 要改的
//                            shixijiluEntity.setDanweiAddress(data.get(0));                    //单位位置 要改的
//                            shixijiluEntity.setDanweiLianxiren(data.get(0));                    //联系人 要改的
//                            shixijiluEntity.setDanweiLianxiPhone(data.get(0));                    //联系方式 要改的
//                            shixijiluEntity.setShixijiluXinzi(data.get(0));                    //实习薪资 要改的
//                            shixijiluEntity.setShixijiluKaishiTime(sdf.parse(data.get(0)));          //实习开始时间 要改的
//                            shixijiluEntity.setShixijiluJieshuTime(sdf.parse(data.get(0)));          //实习结束时间 要改的
//                            shixijiluEntity.setShixijiluContent("");//详情和图片
//                            shixijiluEntity.setInsertTime(date);//时间
//                            shixijiluEntity.setCreateTime(date);//时间
                            shixijiluList.add(shixijiluEntity);


                            //把要查询是否重复的字段放入map中
                                //实习记录编号
                                if(seachFields.containsKey("shixijiluUuidNumber")){
                                    List<String> shixijiluUuidNumber = seachFields.get("shixijiluUuidNumber");
                                    shixijiluUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> shixijiluUuidNumber = new ArrayList<>();
                                    shixijiluUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("shixijiluUuidNumber",shixijiluUuidNumber);
                                }
                        }

                        //查询是否重复
                         //实习记录编号
                        List<ShixijiluEntity> shixijiluEntities_shixijiluUuidNumber = shixijiluService.selectList(new EntityWrapper<ShixijiluEntity>().in("shixijilu_uuid_number", seachFields.get("shixijiluUuidNumber")));
                        if(shixijiluEntities_shixijiluUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(ShixijiluEntity s:shixijiluEntities_shixijiluUuidNumber){
                                repeatFields.add(s.getShixijiluUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [实习记录编号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        shixijiluService.insertBatch(shixijiluList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }




    /**
    * 前端列表
    */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("list方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));

        CommonUtil.checkMap(params);
        PageUtils page = shixijiluService.queryPage(params);

        //字典表数据转换
        List<ShixijiluView> list =(List<ShixijiluView>)page.getList();
        for(ShixijiluView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Integer id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        ShixijiluEntity shixijilu = shixijiluService.selectById(id);
            if(shixijilu !=null){


                //entity转view
                ShixijiluView view = new ShixijiluView();
                BeanUtils.copyProperties( shixijilu , view );//把实体数据重构到view中

                //级联表
                    YonghuEntity yonghu = yonghuService.selectById(shixijilu.getYonghuId());
                if(yonghu != null){
                    BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "username", "password", "newMoney", "yonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYonghuId(yonghu.getId());
                }
                //修改对应字典表字段
                dictionaryService.dictionaryConvert(view, request);
                return R.ok().put("data", view);
            }else {
                return R.error(511,"查不到数据");
            }
    }


    /**
    * 前端保存
    */
    @RequestMapping("/add")
    public R add(@RequestBody ShixijiluEntity shixijilu, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,shixijilu:{}",this.getClass().getName(),shixijilu.toString());
        Wrapper<ShixijiluEntity> queryWrapper = new EntityWrapper<ShixijiluEntity>()
            .eq("yonghu_id", shixijilu.getYonghuId())
            .eq("shixijilu_uuid_number", shixijilu.getShixijiluUuidNumber())
            .eq("shixijilu_name", shixijilu.getShixijiluName())
            .eq("shixizhiwei_types", shixijilu.getShixizhiweiTypes())
            .eq("danwei_address", shixijilu.getDanweiAddress())
            .eq("danwei_lianxiren", shixijilu.getDanweiLianxiren())
            .eq("danwei_lianxi_phone", shixijilu.getDanweiLianxiPhone())
//            .notIn("shixijilu_types", new Integer[]{102})
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ShixijiluEntity shixijiluEntity = shixijiluService.selectOne(queryWrapper);
        if(shixijiluEntity==null){
            shixijilu.setInsertTime(new Date());
            shixijilu.setCreateTime(new Date());
        shixijiluService.insert(shixijilu);

            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

}


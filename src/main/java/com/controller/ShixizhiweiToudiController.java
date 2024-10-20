
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
 * 实习申请
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/shixizhiweiToudi")
public class ShixizhiweiToudiController {
    private static final Logger logger = LoggerFactory.getLogger(ShixizhiweiToudiController.class);

    private static final String TABLE_NAME = "shixizhiweiToudi";

    @Autowired
    private ShixizhiweiToudiService shixizhiweiToudiService;


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
        PageUtils page = shixizhiweiToudiService.queryPage(params);

        //字典表数据转换
        List<ShixizhiweiToudiView> list =(List<ShixizhiweiToudiView>)page.getList();
        for(ShixizhiweiToudiView c:list){
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
        ShixizhiweiToudiEntity shixizhiweiToudi = shixizhiweiToudiService.selectById(id);
        if(shixizhiweiToudi !=null){
            //entity转view
            ShixizhiweiToudiView view = new ShixizhiweiToudiView();
            BeanUtils.copyProperties( shixizhiweiToudi , view );//把实体数据重构到view中
            //级联表 简历
            //级联表
            JianliEntity jianli = jianliService.selectById(shixizhiweiToudi.getJianliId());
            if(jianli != null){
            BeanUtils.copyProperties( jianli , view ,new String[]{ "id", "createTime", "insertTime", "updateTime"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setJianliId(jianli.getId());
            }
            //级联表 职位实习
            //级联表
            ShixizhiweiEntity shixizhiwei = shixizhiweiService.selectById(shixizhiweiToudi.getShixizhiweiId());
            if(shixizhiwei != null){
            BeanUtils.copyProperties( shixizhiwei , view ,new String[]{ "id", "createTime", "insertTime", "updateTime"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setShixizhiweiId(shixizhiwei.getId());
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
    public R save(@RequestBody ShixizhiweiToudiEntity shixizhiweiToudi, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,shixizhiweiToudi:{}",this.getClass().getName(),shixizhiweiToudi.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");

        Wrapper<ShixizhiweiToudiEntity> queryWrapper = new EntityWrapper<ShixizhiweiToudiEntity>()
            .eq("jianli_id", shixizhiweiToudi.getJianliId())
            .eq("shixizhiwei_id", shixizhiweiToudi.getShixizhiweiId())
            .in("shixizhiwei_toudi_yesno_types", new Integer[]{1,2})
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ShixizhiweiToudiEntity shixizhiweiToudiEntity = shixizhiweiToudiService.selectOne(queryWrapper);
        if(shixizhiweiToudiEntity==null){
            shixizhiweiToudi.setInsertTime(new Date());
            shixizhiweiToudi.setShixizhiweiToudiYesnoTypes(1);
            shixizhiweiToudi.setCreateTime(new Date());
            shixizhiweiToudiService.insert(shixizhiweiToudi);
            return R.ok();
        }else {
            if(shixizhiweiToudiEntity.getShixizhiweiToudiYesnoTypes()==1)
                return R.error(511,"有相同的待审核的数据");
            else if(shixizhiweiToudiEntity.getShixizhiweiToudiYesnoTypes()==2)
                return R.error(511,"有相同的审核通过的数据");
            else
                return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody ShixizhiweiToudiEntity shixizhiweiToudi, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,shixizhiweiToudi:{}",this.getClass().getName(),shixizhiweiToudi.toString());
        ShixizhiweiToudiEntity oldShixizhiweiToudiEntity = shixizhiweiToudiService.selectById(shixizhiweiToudi.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");

            shixizhiweiToudiService.updateById(shixizhiweiToudi);//根据id更新
            return R.ok();
    }


    /**
    * 审核
    */
    @RequestMapping("/shenhe")
    public R shenhe(@RequestBody ShixizhiweiToudiEntity shixizhiweiToudiEntity, HttpServletRequest request){
        logger.debug("shenhe方法:,,Controller:{},,shixizhiweiToudiEntity:{}",this.getClass().getName(),shixizhiweiToudiEntity.toString());

        ShixizhiweiToudiEntity oldShixizhiweiToudi = shixizhiweiToudiService.selectById(shixizhiweiToudiEntity.getId());//查询原先数据

//        if(shixizhiweiToudiEntity.getShixizhiweiToudiYesnoTypes() == 2){//通过
//            shixizhiweiToudiEntity.setShixizhiweiToudiTypes();
//        }else if(shixizhiweiToudiEntity.getShixizhiweiToudiYesnoTypes() == 3){//拒绝
//            shixizhiweiToudiEntity.setShixizhiweiToudiTypes();
//        }
        shixizhiweiToudiEntity.setShixizhiweiToudiShenheTime(new Date());//回复时间
        shixizhiweiToudiService.updateById(shixizhiweiToudiEntity);//审核

        return R.ok();
    }

    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<ShixizhiweiToudiEntity> oldShixizhiweiToudiList =shixizhiweiToudiService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        shixizhiweiToudiService.deleteBatchIds(Arrays.asList(ids));

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
            List<ShixizhiweiToudiEntity> shixizhiweiToudiList = new ArrayList<>();//上传的东西
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
                            ShixizhiweiToudiEntity shixizhiweiToudiEntity = new ShixizhiweiToudiEntity();
//                            shixizhiweiToudiEntity.setJianliId(Integer.valueOf(data.get(0)));   //简历 要改的
//                            shixizhiweiToudiEntity.setShixizhiweiId(Integer.valueOf(data.get(0)));   //实习 要改的
//                            shixizhiweiToudiEntity.setInsertTime(date);//时间
//                            shixizhiweiToudiEntity.setShixizhiweiToudiYesnoTypes(Integer.valueOf(data.get(0)));   //审核状态 要改的
//                            shixizhiweiToudiEntity.setShixizhiweiToudiYesnoText(data.get(0));                    //投递回复 要改的
//                            shixizhiweiToudiEntity.setShixizhiweiToudiShenheTime(sdf.parse(data.get(0)));          //回复时间 要改的
//                            shixizhiweiToudiEntity.setCreateTime(date);//时间
                            shixizhiweiToudiList.add(shixizhiweiToudiEntity);


                            //把要查询是否重复的字段放入map中
                        }

                        //查询是否重复
                        shixizhiweiToudiService.insertBatch(shixizhiweiToudiList);
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
        PageUtils page = shixizhiweiToudiService.queryPage(params);

        //字典表数据转换
        List<ShixizhiweiToudiView> list =(List<ShixizhiweiToudiView>)page.getList();
        for(ShixizhiweiToudiView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        ShixizhiweiToudiEntity shixizhiweiToudi = shixizhiweiToudiService.selectById(id);
            if(shixizhiweiToudi !=null){


                //entity转view
                ShixizhiweiToudiView view = new ShixizhiweiToudiView();
                BeanUtils.copyProperties( shixizhiweiToudi , view );//把实体数据重构到view中

                //级联表
                    JianliEntity jianli = jianliService.selectById(shixizhiweiToudi.getJianliId());
                if(jianli != null){
                    BeanUtils.copyProperties( jianli , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setJianliId(jianli.getId());
                }
                //级联表
                    ShixizhiweiEntity shixizhiwei = shixizhiweiService.selectById(shixizhiweiToudi.getShixizhiweiId());
                if(shixizhiwei != null){
                    BeanUtils.copyProperties( shixizhiwei , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setShixizhiweiId(shixizhiwei.getId());
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
    public R add(@RequestBody ShixizhiweiToudiEntity shixizhiweiToudi, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,shixizhiweiToudi:{}",this.getClass().getName(),shixizhiweiToudi.toString());
        Wrapper<ShixizhiweiToudiEntity> queryWrapper = new EntityWrapper<ShixizhiweiToudiEntity>()
            .eq("jianli_id", shixizhiweiToudi.getJianliId())
            .eq("shixizhiwei_id", shixizhiweiToudi.getShixizhiweiId())
            .in("shixizhiwei_toudi_yesno_types", new Integer[]{1,2})
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ShixizhiweiToudiEntity shixizhiweiToudiEntity = shixizhiweiToudiService.selectOne(queryWrapper);
        if(shixizhiweiToudiEntity==null){
            shixizhiweiToudi.setInsertTime(new Date());
            shixizhiweiToudi.setShixizhiweiToudiYesnoTypes(1);
            shixizhiweiToudi.setCreateTime(new Date());
        shixizhiweiToudiService.insert(shixizhiweiToudi);

            return R.ok();
        }else {
            if(shixizhiweiToudiEntity.getShixizhiweiToudiYesnoTypes()==1)
                return R.error(511,"您已经申请了该岗位的实习,请等待审核");
            else if(shixizhiweiToudiEntity.getShixizhiweiToudiYesnoTypes()==2)
                return R.error(511,"您已经通过了该岗位的实习,不能重复申请");
            else
                return R.error(511,"表中有相同数据");
        }
    }

}


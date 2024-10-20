
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
 * 职位实习
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/shixizhiwei")
public class ShixizhiweiController {
    private static final Logger logger = LoggerFactory.getLogger(ShixizhiweiController.class);

    private static final String TABLE_NAME = "shixizhiwei";

    @Autowired
    private ShixizhiweiService shixizhiweiService;


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
        PageUtils page = shixizhiweiService.queryPage(params);

        //字典表数据转换
        List<ShixizhiweiView> list =(List<ShixizhiweiView>)page.getList();
        for(ShixizhiweiView c:list){
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
        ShixizhiweiEntity shixizhiwei = shixizhiweiService.selectById(id);
        if(shixizhiwei !=null){
            //entity转view
            ShixizhiweiView view = new ShixizhiweiView();
            BeanUtils.copyProperties( shixizhiwei , view );//把实体数据重构到view中
            //级联表 企业
            //级联表
            QiyeEntity qiye = qiyeService.selectById(shixizhiwei.getQiyeId());
            if(qiye != null){
            BeanUtils.copyProperties( qiye , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "qiyeId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setQiyeId(qiye.getId());
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
    public R save(@RequestBody ShixizhiweiEntity shixizhiwei, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,shixizhiwei:{}",this.getClass().getName(),shixizhiwei.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("企业".equals(role))
            shixizhiwei.setQiyeId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        Wrapper<ShixizhiweiEntity> queryWrapper = new EntityWrapper<ShixizhiweiEntity>()
            .eq("qiye_id", shixizhiwei.getQiyeId())
            .eq("shixizhiwei_name", shixizhiwei.getShixizhiweiName())
            .eq("shixizhiwei_daiyu", shixizhiwei.getShixizhiweiDaiyu())
            .eq("shixizhiwei_address", shixizhiwei.getShixizhiweiAddress())
            .eq("lianxiren_name", shixizhiwei.getLianxirenName())
            .eq("shixizhiwei_phone", shixizhiwei.getShixizhiweiPhone())
            .eq("shixizhiwei_types", shixizhiwei.getShixizhiweiTypes())
            .eq("shixizhiwei_renshu_number", shixizhiwei.getShixizhiweiRenshuNumber())
            .eq("shangxia_types", shixizhiwei.getShangxiaTypes())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ShixizhiweiEntity shixizhiweiEntity = shixizhiweiService.selectOne(queryWrapper);
        if(shixizhiweiEntity==null){
            shixizhiwei.setShangxiaTypes(1);
            shixizhiwei.setCreateTime(new Date());
            shixizhiweiService.insert(shixizhiwei);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody ShixizhiweiEntity shixizhiwei, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,shixizhiwei:{}",this.getClass().getName(),shixizhiwei.toString());
        ShixizhiweiEntity oldShixizhiweiEntity = shixizhiweiService.selectById(shixizhiwei.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("企业".equals(role))
//            shixizhiwei.setQiyeId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        if("".equals(shixizhiwei.getShixizhiweiPhoto()) || "null".equals(shixizhiwei.getShixizhiweiPhoto())){
                shixizhiwei.setShixizhiweiPhoto(null);
        }

            shixizhiweiService.updateById(shixizhiwei);//根据id更新
            return R.ok();
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<ShixizhiweiEntity> oldShixizhiweiList =shixizhiweiService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        shixizhiweiService.deleteBatchIds(Arrays.asList(ids));

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
            List<ShixizhiweiEntity> shixizhiweiList = new ArrayList<>();//上传的东西
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
                            ShixizhiweiEntity shixizhiweiEntity = new ShixizhiweiEntity();
//                            shixizhiweiEntity.setQiyeId(Integer.valueOf(data.get(0)));   //企业 要改的
//                            shixizhiweiEntity.setShixizhiweiName(data.get(0));                    //实习信息名称 要改的
//                            shixizhiweiEntity.setShixizhiweiPhoto("");//详情和图片
//                            shixizhiweiEntity.setShixizhiweiDaiyu(data.get(0));                    //薪资待遇 要改的
//                            shixizhiweiEntity.setShixizhiweiAddress(data.get(0));                    //上班地点 要改的
//                            shixizhiweiEntity.setLianxirenName(data.get(0));                    //联系人 要改的
//                            shixizhiweiEntity.setShixizhiweiPhone(data.get(0));                    //实习电话 要改的
//                            shixizhiweiEntity.setShixizhiweiTypes(Integer.valueOf(data.get(0)));   //实习职位 要改的
//                            shixizhiweiEntity.setShixizhiweiRenshuNumber(Integer.valueOf(data.get(0)));   //实习人数 要改的
//                            shixizhiweiEntity.setShangxiaTypes(Integer.valueOf(data.get(0)));   //是否上架 要改的
//                            shixizhiweiEntity.setShixizhiweiContent("");//详情和图片
//                            shixizhiweiEntity.setCreateTime(date);//时间
                            shixizhiweiList.add(shixizhiweiEntity);


                            //把要查询是否重复的字段放入map中
                                //实习电话
                                if(seachFields.containsKey("shixizhiweiPhone")){
                                    List<String> shixizhiweiPhone = seachFields.get("shixizhiweiPhone");
                                    shixizhiweiPhone.add(data.get(0));//要改的
                                }else{
                                    List<String> shixizhiweiPhone = new ArrayList<>();
                                    shixizhiweiPhone.add(data.get(0));//要改的
                                    seachFields.put("shixizhiweiPhone",shixizhiweiPhone);
                                }
                        }

                        //查询是否重复
                         //实习电话
                        List<ShixizhiweiEntity> shixizhiweiEntities_shixizhiweiPhone = shixizhiweiService.selectList(new EntityWrapper<ShixizhiweiEntity>().in("shixizhiwei_phone", seachFields.get("shixizhiweiPhone")));
                        if(shixizhiweiEntities_shixizhiweiPhone.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(ShixizhiweiEntity s:shixizhiweiEntities_shixizhiweiPhone){
                                repeatFields.add(s.getShixizhiweiPhone());
                            }
                            return R.error(511,"数据库的该表中的 [实习电话] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        shixizhiweiService.insertBatch(shixizhiweiList);
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
    * 个性推荐
    */
    @IgnoreAuth
    @RequestMapping("/gexingtuijian")
    public R gexingtuijian(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("gexingtuijian方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        CommonUtil.checkMap(params);
        List<ShixizhiweiView> returnShixizhiweiViewList = new ArrayList<>();

        //查看收藏
        Map<String, Object> params1 = new HashMap<>(params);params1.put("sort","id");params1.put("yonghuId",request.getSession().getAttribute("userId"));
        PageUtils pageUtils = shixizhiweiCollectionService.queryPage(params1);
        List<ShixizhiweiCollectionView> collectionViewsList =(List<ShixizhiweiCollectionView>)pageUtils.getList();
        Map<Integer,Integer> typeMap=new HashMap<>();//购买的类型list
        for(ShixizhiweiCollectionView collectionView:collectionViewsList){
            Integer shixizhiweiTypes = collectionView.getShixizhiweiTypes();
            if(typeMap.containsKey(shixizhiweiTypes)){
                typeMap.put(shixizhiweiTypes,typeMap.get(shixizhiweiTypes)+1);
            }else{
                typeMap.put(shixizhiweiTypes,1);
            }
        }
        List<Integer> typeList = new ArrayList<>();//排序后的有序的类型 按最多到最少
        typeMap.entrySet().stream().sorted((o1, o2) -> o2.getValue() - o1.getValue()).forEach(e -> typeList.add(e.getKey()));//排序
        Integer limit = Integer.valueOf(String.valueOf(params.get("limit")));
        for(Integer type:typeList){
            Map<String, Object> params2 = new HashMap<>(params);params2.put("shixizhiweiTypes",type);
            PageUtils pageUtils1 = shixizhiweiService.queryPage(params2);
            List<ShixizhiweiView> shixizhiweiViewList =(List<ShixizhiweiView>)pageUtils1.getList();
            returnShixizhiweiViewList.addAll(shixizhiweiViewList);
            if(returnShixizhiweiViewList.size()>= limit) break;//返回的推荐数量大于要的数量 跳出循环
        }
        //正常查询出来商品,用于补全推荐缺少的数据
        PageUtils page = shixizhiweiService.queryPage(params);
        if(returnShixizhiweiViewList.size()<limit){//返回数量还是小于要求数量
            int toAddNum = limit - returnShixizhiweiViewList.size();//要添加的数量
            List<ShixizhiweiView> shixizhiweiViewList =(List<ShixizhiweiView>)page.getList();
            for(ShixizhiweiView shixizhiweiView:shixizhiweiViewList){
                Boolean addFlag = true;
                for(ShixizhiweiView returnShixizhiweiView:returnShixizhiweiViewList){
                    if(returnShixizhiweiView.getId().intValue() ==shixizhiweiView.getId().intValue()) addFlag=false;//返回的数据中已存在此商品
                }
                if(addFlag){
                    toAddNum=toAddNum-1;
                    returnShixizhiweiViewList.add(shixizhiweiView);
                    if(toAddNum==0) break;//够数量了
                }
            }
        }else {
            returnShixizhiweiViewList = returnShixizhiweiViewList.subList(0, limit);
        }

        for(ShixizhiweiView c:returnShixizhiweiViewList)
            dictionaryService.dictionaryConvert(c, request);
        page.setList(returnShixizhiweiViewList);
        return R.ok().put("data", page);
    }

    /**
    * 前端列表
    */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("list方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));

        CommonUtil.checkMap(params);
        PageUtils page = shixizhiweiService.queryPage(params);

        //字典表数据转换
        List<ShixizhiweiView> list =(List<ShixizhiweiView>)page.getList();
        for(ShixizhiweiView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        ShixizhiweiEntity shixizhiwei = shixizhiweiService.selectById(id);
            if(shixizhiwei !=null){


                //entity转view
                ShixizhiweiView view = new ShixizhiweiView();
                BeanUtils.copyProperties( shixizhiwei , view );//把实体数据重构到view中

                //级联表
                    QiyeEntity qiye = qiyeService.selectById(shixizhiwei.getQiyeId());
                if(qiye != null){
                    BeanUtils.copyProperties( qiye , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setQiyeId(qiye.getId());
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
    public R add(@RequestBody ShixizhiweiEntity shixizhiwei, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,shixizhiwei:{}",this.getClass().getName(),shixizhiwei.toString());
        Wrapper<ShixizhiweiEntity> queryWrapper = new EntityWrapper<ShixizhiweiEntity>()
            .eq("qiye_id", shixizhiwei.getQiyeId())
            .eq("shixizhiwei_name", shixizhiwei.getShixizhiweiName())
            .eq("shixizhiwei_daiyu", shixizhiwei.getShixizhiweiDaiyu())
            .eq("shixizhiwei_address", shixizhiwei.getShixizhiweiAddress())
            .eq("lianxiren_name", shixizhiwei.getLianxirenName())
            .eq("shixizhiwei_phone", shixizhiwei.getShixizhiweiPhone())
            .eq("shixizhiwei_types", shixizhiwei.getShixizhiweiTypes())
            .eq("shixizhiwei_renshu_number", shixizhiwei.getShixizhiweiRenshuNumber())
            .eq("shangxia_types", shixizhiwei.getShangxiaTypes())
//            .notIn("shixizhiwei_types", new Integer[]{102})
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ShixizhiweiEntity shixizhiweiEntity = shixizhiweiService.selectOne(queryWrapper);
        if(shixizhiweiEntity==null){
            shixizhiwei.setCreateTime(new Date());
        shixizhiweiService.insert(shixizhiwei);

            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

}


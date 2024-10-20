package com.dao;

import com.entity.ShixizhiweiLiuyanEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.ShixizhiweiLiuyanView;

/**
 * 职位留言 Dao 接口
 *
 * @author 
 */
public interface ShixizhiweiLiuyanDao extends BaseMapper<ShixizhiweiLiuyanEntity> {

   List<ShixizhiweiLiuyanView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}

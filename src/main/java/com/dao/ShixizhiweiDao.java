package com.dao;

import com.entity.ShixizhiweiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.ShixizhiweiView;

/**
 * 职位实习 Dao 接口
 *
 * @author 
 */
public interface ShixizhiweiDao extends BaseMapper<ShixizhiweiEntity> {

   List<ShixizhiweiView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}

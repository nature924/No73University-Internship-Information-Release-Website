package com.dao;

import com.entity.ShixijiluEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.ShixijiluView;

/**
 * 实习记录 Dao 接口
 *
 * @author 
 */
public interface ShixijiluDao extends BaseMapper<ShixijiluEntity> {

   List<ShixijiluView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}

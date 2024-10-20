package com.dao;

import com.entity.ShixizhiweiCollectionEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.ShixizhiweiCollectionView;

/**
 * 职位收藏 Dao 接口
 *
 * @author 
 */
public interface ShixizhiweiCollectionDao extends BaseMapper<ShixizhiweiCollectionEntity> {

   List<ShixizhiweiCollectionView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}

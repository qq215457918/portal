package com.portal.service;

import com.portal.bean.Criteria;
import com.portal.bean.GoodsSort;
import java.util.List;

public interface GoodsSortService {
    int countByExample(Criteria example);

    GoodsSort selectByPrimaryKey(String id);

    List<GoodsSort> selectByExample(Criteria example);

    int deleteByPrimaryKey(String id);

    int updateByPrimaryKeySelective(GoodsSort record);

    int updateByPrimaryKey(GoodsSort record);

    int deleteByExample(Criteria example);

    int updateByExampleSelective(GoodsSort record, Criteria example);

    int updateByExample(GoodsSort record, Criteria example);

    int insert(GoodsSort record);

    int insertSelective(GoodsSort record);
}
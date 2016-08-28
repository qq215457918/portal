package com.portal.service;

import com.portal.bean.Criteria;
import com.portal.bean.GoodsInfo;
import java.util.List;

public interface GoodsInfoService {
    int countByExample(Criteria example);

    GoodsInfo selectByPrimaryKey(String id);

    List<GoodsInfo> selectByExample(Criteria example);

    int deleteByPrimaryKey(String id);

    int updateByPrimaryKeySelective(GoodsInfo record);

    int updateByPrimaryKey(GoodsInfo record);

    int deleteByExample(Criteria example);

    int updateByExampleSelective(GoodsInfo record, Criteria example);

    int updateByExample(GoodsInfo record, Criteria example);

    int insert(GoodsInfo record);

    int insertSelective(GoodsInfo record);
}
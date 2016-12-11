package com.portal.service;

import com.portal.bean.Criteria;
import com.portal.bean.SellGoodsDetail;
import java.util.List;

public interface SellGoodsDetailService {
    int countByExample(Criteria example);

    SellGoodsDetail selectByPrimaryKey(String id);

    List<SellGoodsDetail> selectByExample(Criteria example);

    int deleteByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SellGoodsDetail record);

    int updateByPrimaryKey(SellGoodsDetail record);

    int deleteByExample(Criteria example);

    int updateByExampleSelective(SellGoodsDetail record, Criteria example);

    int updateByExample(SellGoodsDetail record, Criteria example);

    int insert(SellGoodsDetail record);

    int insertSelective(SellGoodsDetail record);
}
package com.portal.service;

import com.portal.bean.Criteria;
import com.portal.bean.SellDailyDetail;
import java.util.List;

public interface SellDailyDetailService {
    int countByExample(Criteria example);

    SellDailyDetail selectByPrimaryKey(String id);

    List<SellDailyDetail> selectByExample(Criteria example);

    int deleteByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SellDailyDetail record);

    int updateByPrimaryKey(SellDailyDetail record);

    int deleteByExample(Criteria example);

    int updateByExampleSelective(SellDailyDetail record, Criteria example);

    int updateByExample(SellDailyDetail record, Criteria example);

    int insert(SellDailyDetail record);

    int insertSelective(SellDailyDetail record);
}
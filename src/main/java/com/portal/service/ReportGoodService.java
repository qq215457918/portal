package com.portal.service;

import com.portal.bean.Criteria;
import com.portal.bean.ReportGood;
import java.util.List;

public interface ReportGoodService {
    int countByExample(Criteria example);

    ReportGood selectByPrimaryKey(String id);

    List<ReportGood> selectByExample(Criteria example);

    int deleteByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ReportGood record);

    int updateByPrimaryKey(ReportGood record);

    int deleteByExample(Criteria example);

    int updateByExampleSelective(ReportGood record, Criteria example);

    int updateByExample(ReportGood record, Criteria example);

    int insert(ReportGood record);

    int insertSelective(ReportGood record);
}
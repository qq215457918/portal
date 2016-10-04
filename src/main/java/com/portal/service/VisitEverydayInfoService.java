package com.portal.service;

import com.portal.bean.Criteria;
import com.portal.bean.VisitEverydayInfo;
import java.util.List;

public interface VisitEverydayInfoService {
    int countByExample(Criteria example);

    VisitEverydayInfo selectByPrimaryKey(String id);

    List<VisitEverydayInfo> selectByExample(Criteria example);

    int deleteByPrimaryKey(String id);

    int updateByPrimaryKeySelective(VisitEverydayInfo record);

    int updateByPrimaryKey(VisitEverydayInfo record);

    int deleteByExample(Criteria example);

    int updateByExampleSelective(VisitEverydayInfo record, Criteria example);

    int updateByExample(VisitEverydayInfo record, Criteria example);

    int insert(VisitEverydayInfo record);

    int insertSelective(VisitEverydayInfo record);
}
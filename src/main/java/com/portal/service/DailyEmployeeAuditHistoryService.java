package com.portal.service;

import com.portal.bean.Criteria;
import com.portal.bean.DailyEmployeeAuditHistory;
import java.util.List;

public interface DailyEmployeeAuditHistoryService {
    int countByExample(Criteria example);

    DailyEmployeeAuditHistory selectByPrimaryKey(String id);

    List<DailyEmployeeAuditHistory> selectByExample(Criteria example);

    int deleteByPrimaryKey(String id);

    int updateByPrimaryKeySelective(DailyEmployeeAuditHistory record);

    int updateByPrimaryKey(DailyEmployeeAuditHistory record);

    int deleteByExample(Criteria example);

    int updateByExampleSelective(DailyEmployeeAuditHistory record, Criteria example);

    int updateByExample(DailyEmployeeAuditHistory record, Criteria example);

    int insert(DailyEmployeeAuditHistory record);

    int insertSelective(DailyEmployeeAuditHistory record);
}
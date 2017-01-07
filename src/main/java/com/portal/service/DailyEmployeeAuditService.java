package com.portal.service;

import com.portal.bean.Criteria;
import com.portal.bean.DailyEmployeeAudit;
import java.util.List;

public interface DailyEmployeeAuditService {
    int countByExample(Criteria example);

    DailyEmployeeAudit selectByPrimaryKey(String id);

    List<DailyEmployeeAudit> selectByExample(Criteria example);

    int deleteByPrimaryKey(String id);

    int updateByPrimaryKeySelective(DailyEmployeeAudit record);

    int updateByPrimaryKey(DailyEmployeeAudit record);

    int deleteByExample(Criteria example);

    int updateByExampleSelective(DailyEmployeeAudit record, Criteria example);

    int updateByExample(DailyEmployeeAudit record, Criteria example);

    int insert(DailyEmployeeAudit record);

    int insertSelective(DailyEmployeeAudit record);
}
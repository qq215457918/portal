package com.portal.service;

import com.portal.bean.Criteria;
import com.portal.bean.EmployeeInfo;
import java.util.List;

public interface EmployeeInfoService {
    int countByExample(Criteria example);

    EmployeeInfo selectByPrimaryKey(String id);

    List<EmployeeInfo> selectByExample(Criteria example);

    int deleteByPrimaryKey(String id);

    int updateByPrimaryKeySelective(EmployeeInfo record);

    int updateByPrimaryKey(EmployeeInfo record);

    int deleteByExample(Criteria example);

    int updateByExampleSelective(EmployeeInfo record, Criteria example);

    int updateByExample(EmployeeInfo record, Criteria example);

    int insert(EmployeeInfo record);

    int insertSelective(EmployeeInfo record);
}
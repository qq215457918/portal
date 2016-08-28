package com.portal.service;

import com.portal.bean.Criteria;
import com.portal.bean.CustomerInfo;
import java.util.List;

public interface CustomerInfoService {
    int countByExample(Criteria example);

    CustomerInfo selectByPrimaryKey(String id);

    List<CustomerInfo> selectByExample(Criteria example);

    int deleteByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CustomerInfo record);

    int updateByPrimaryKey(CustomerInfo record);

    int deleteByExample(Criteria example);

    int updateByExampleSelective(CustomerInfo record, Criteria example);

    int updateByExample(CustomerInfo record, Criteria example);

    int insert(CustomerInfo record);

    int insertSelective(CustomerInfo record);
}
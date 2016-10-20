package com.portal.service;

import com.portal.bean.Criteria;
import com.portal.bean.CustomerInfo;
import com.portal.bean.result.CustomerSimpleInfoForm;
import java.util.List;

public interface CustomerInfoService {

    public CustomerSimpleInfoForm getFristQueryInfo(String id);

    public CustomerInfo selectByPhone(String phone);

    public boolean isCustomer(String phone);

    public int insertSelective(CustomerInfo record);

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

    public List<CustomerInfo> selectCustomerExList(Criteria criteria);

	public int countCustomerEx(Criteria criteria);
}

package com.portal.service;

import java.util.List;

import com.portal.bean.Criteria;
import com.portal.bean.CustomerInfo;

public interface CustomerInfoService {

	 public CustomerInfo selectByPhone(String phone);
	
	 public boolean isCustomer(String phone);
	 
	 public int insertSelective(CustomerInfo record) ;
	 
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
	
}
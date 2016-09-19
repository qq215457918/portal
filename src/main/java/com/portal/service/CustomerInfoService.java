package com.portal.service;

import com.portal.bean.CustomerInfo;

public interface CustomerInfoService {

	 public CustomerInfo selectByPhone(String phone);
	
	 public boolean isCustomer(String phone);
	 
	 public int insertSelective(CustomerInfo record) ;
	
}
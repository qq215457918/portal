package com.portal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portal.dao.TestUserDao;
import com.portal.service.TestUserService;

@Service
@Transactional
public class TestUserServiceImpl implements TestUserService {
	
	@Autowired
	private TestUserDao testUserDao;
	
	@Override
	public void insertUserInfo(){
		testUserDao.insertUserInfo();
	}
}

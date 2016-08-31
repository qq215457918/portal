package com.portal.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.portal.service.TestUserService;

@Controller
public class TestAction {

	@Autowired
	private TestUserService testUserService;
	
	@RequestMapping("/user")
	public void insertUserinfo(){
		testUserService.insertUserInfo();
	}
}

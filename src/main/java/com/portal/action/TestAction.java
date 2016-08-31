package com.portal.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.portal.common.util.WebUtils;
import com.portal.service.TestUserService;

@Controller
public class TestAction {

	@Autowired
	private TestUserService testUserService;
	
	@RequestMapping("/user")
	public String insertUserinfo(HttpServletRequest request, HttpServletResponse response){
	    //获取项目基础路径
        String basePath = WebUtils.getBasePath(request, response);
        //供页面和后台引用项目路径使用
        request.getSession().setAttribute("basePath", basePath); 
	    return "index";
		//testUserService.insertUserInfo();
	}
}

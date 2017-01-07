package com.portal.action.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.springframework.beans.factory.annotation.Autowired;

import com.portal.common.util.StringUtil;
import com.portal.common.util.WebUtils;
import com.portal.service.EmployeeInfoService;

public class SysUserFilter extends PathMatchingFilter {

    @Autowired
    private EmployeeInfoService employeeService;

    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue)
            throws Exception {

        String username = (String) SecurityUtils.getSubject().getPrincipal();
        // 加一个拦截器，在每次访问的时候都获取员工的基本信息。
        request.setAttribute("employeeInfo", employeeService.selectByUserName(username));
        
        // 存储BasePath
        HttpServletRequest requests = (HttpServletRequest) request;
        String basePath = WebUtils.getBasePath(requests, (HttpServletResponse)response);
        if(!StringUtil.isNotBlank(basePath)) {
            requests.getSession().setAttribute("basePath", basePath);
        }
        
        return true;
    }
    
}

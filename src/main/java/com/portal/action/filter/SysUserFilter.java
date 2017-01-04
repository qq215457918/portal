package com.portal.action.filter;

import com.portal.service.EmployeeInfoService;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.springframework.beans.factory.annotation.Autowired;

public class SysUserFilter extends PathMatchingFilter {

    @Autowired
    private EmployeeInfoService employeeService;

    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue)
            throws Exception {

        String username = (String) SecurityUtils.getSubject().getPrincipal();
        // 加一个拦截器，在每次访问的时候都获取员工的基本信息。
        request.setAttribute("employeeInfo", employeeService.selectByUserName(username));
        return true;
    }
}

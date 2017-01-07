
package com.portal.action;

import com.portal.action.bind.annotation.CurrentUser;
import com.portal.bean.EmployeeInfo;
import com.portal.bean.Resource;
import com.portal.common.util.WebUtils;
import com.portal.service.EmployeeInfoService;
import com.portal.service.ResourceService;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 获取可访问的菜单项
 */
@Controller
public class IndexController {
    @Autowired
    private ResourceService resourceService;
    @Autowired
    private EmployeeInfoService employeeService;

    @RequestMapping("/index")
    public String index(@CurrentUser EmployeeInfo loginUser, Model model, HttpServletRequest request, HttpServletResponse response) {
        Set<String> permissions = employeeService.findPermissions(loginUser.getLoginName());
        List<Resource> menus = resourceService.findMenus(permissions);
        model.addAttribute("menus", menus);
        getBasePath(request, response);
        return "main"; 
    }
    
    public void getBasePath(HttpServletRequest request, HttpServletResponse response) {
        String basePath = WebUtils.getBasePath(request, response);
        request.getSession().setAttribute("basePath", basePath);
    }
}

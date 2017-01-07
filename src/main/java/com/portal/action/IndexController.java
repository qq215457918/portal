
package com.portal.action;

import com.portal.action.bind.annotation.CurrentUser;
import com.portal.bean.EmployeeInfo;
import com.portal.bean.Resource;
import com.portal.service.EmployeeInfoService;
import com.portal.service.ResourceService;
import java.util.List;
import java.util.Set;
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

    @RequestMapping("/")
    public String index(@CurrentUser EmployeeInfo loginUser, Model model) {
        Set<String> permissions = employeeService.findPermissions(loginUser.getLoginName());
        List<Resource> menus = resourceService.findMenus(permissions);
        model.addAttribute("menus", menus);
        return "main";
    }
}

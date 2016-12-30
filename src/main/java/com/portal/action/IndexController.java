
package com.portal.action;

import com.portal.action.bind.annotation.CurrentUser;
import com.portal.bean.EmployeeInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    @RequestMapping("/")
    public String index(@CurrentUser EmployeeInfo loginUser, Model model) {

        //        Set<String> permissions = userService.findPermissions(loginUser.getLoginName());
        //        List<Resource> menus = resourceService.findMenus(permissions);
        //        List<Resource> menus = null;
        //        model.addAttribute("menus", menus);
        return "index";
    }
}

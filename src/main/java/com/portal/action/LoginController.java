
package com.portal.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用户控制器
 * 
 * @author StarZou
 * @since 2014年5月28日 下午3:54:00
 **/
@Controller
public class LoginController {

    /**
     * 转到login页面，具体的验证工作交给shiro
     * 密码匹配任务交给shiro 此处主要返回错误信息。
     * @param user
     * @param result
     * @return
     */
    @RequestMapping(value = "/login")
    public String showLoginForm(HttpServletRequest req, Model model, HttpServletResponse response) {
        String exceptionClassName = (String) req.getAttribute("shiroLoginFailure");
        String error = null;
        if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
            error = "用户名/密码错误";
        } else if (IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
            error = "用户名/密码错误";
        } else if (exceptionClassName != null) {
            error = "其他错误：" + exceptionClassName;
        }
        model.addAttribute("error", error);
        return "login";
    }

}


package com.portal.action;

import com.portal.service.EmployeeInfoService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private EmployeeInfoService employeeService;

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
    /*    @RequestMapping(value = "/login")
    public String login(HttpServletRequest request, HttpServletResponse response) {
        getBasePath(request, response);
        try {
            String userName = request.getParameter("username");
            String password = request.getParameter("password");
            Subject subject = SecurityUtils.getSubject();
            // 已登陆则 跳到首页
            if (subject.isAuthenticated()) {
                return "main";
            }
            // 身份验证
            subject.login(new UsernamePasswordToken(userName, password));
            // 验证成功在Session中保存用户信息
            final EmployeeInfo employeeInfo = employeeService.selectByUserName(userName);
            request.getSession().setAttribute("userInfo", employeeInfo);
            request.getSession().setAttribute("userId", employeeInfo.getId());
            request.getSession().setAttribute("userName", employeeInfo.getName());
            //获取项目基础路径
            String basePath = WebUtils.getBasePath(request, response);
            //供页面和后台引用项目路径使用
            request.getSession().setAttribute("basePath", basePath);
            // 保存活动导航标识
            WebUtils.setAttributeToSession(request);
        } catch (AuthenticationException e) {
            // 身份验证失败
            // model.addAttribute("error", "用户名或密码错误 ！");
            return "login";
        }
        return "main";
    }*/

    /**
     * 用户登出
     * 
     * @param session
     * @return
     */
    /*    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        session.removeAttribute("userInfo");
        // 登出操作
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "login";
    }*/
}

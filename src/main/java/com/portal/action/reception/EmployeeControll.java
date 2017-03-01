
package com.portal.action.reception;

import com.portal.bean.EmployeeInfo;
import com.portal.common.util.WebUtils;
import com.portal.service.EmployeeInfoService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("password")
public class EmployeeControll {
    @Autowired
    private EmployeeInfoService employeeService;

    /**
     * 修改密码页面
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "modify")
    public String modify(HttpServletRequest request, HttpServletResponse response) {
        getBasePath(request, response);
        return "common/password";
    }

    /**
     * 修改密码页面
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "save")
    public String save(HttpServletRequest request, HttpServletResponse response) {
        getBasePath(request, response);
        EmployeeInfo employeeInfo = (EmployeeInfo) request.getAttribute("employeeInfo");
        if (employeeInfo == null || employeeInfo.equals("")) {
            return "login";
        }
        employeeInfo.setPassword(request.getParameter("password"));
        employeeService.updateByPrimaryKeySelective(employeeInfo);
        return "main";
    }

    public void getBasePath(HttpServletRequest request, HttpServletResponse response) {
        String basePath = WebUtils.getBasePath(request, response);
        request.getSession().setAttribute("basePath", basePath);
    }
}

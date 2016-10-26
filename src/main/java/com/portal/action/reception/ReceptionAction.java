
package com.portal.action.reception;

import com.portal.common.util.WebUtils;
import com.portal.service.CustomerInfoService;
import com.portal.service.ReceptionInfoService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 接待模块查询业务
 */
@Controller
@RequestMapping("/visit")
public class ReceptionAction {
    private final Logger logger = LoggerFactory.getLogger(ReceptionAction.class);

    @Autowired
    protected ReceptionInfoService receptionInfoService;
    @Autowired
    protected CustomerInfoService customerInfoService;

    /**
     * 进入用户查询页面
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/query")
    public String queryCustomer(HttpServletRequest request, HttpServletResponse response) {
        getBasePath(request, response);
        return "reception/inquiry_query";
    }

    /**
     * 查询用手机号，是否是我们系统中已经录入的客户
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/frist")
    public String fristCustomerInfo(HttpServletRequest request, HttpServletResponse response) {
        getBasePath(request, response);
        String phoneNo = request.getParameter("phoneNo");
        boolean exist = customerInfoService.isCustomer(phoneNo);
        if (!exist) {
            return "reception/inquiry_add";
        }
        customerInfoService.getFristQueryInfo(phoneNo);
        return "reception/query_frist";
    }

    @RequestMapping(value = "/add")
    public String customerAdd(HttpServletRequest request, HttpServletResponse response) {
        getBasePath(request, response);
        //新增用户信息

        return "reception/inquiry_add";
    }

    public void getBasePath(HttpServletRequest request, HttpServletResponse response) {
        String basePath = WebUtils.getBasePath(request, response);
        request.getSession().setAttribute("basePath", basePath);
    }

}

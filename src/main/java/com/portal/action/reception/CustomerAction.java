package com.portal.action.reception;

import com.portal.common.util.WebUtils;
import com.portal.service.CustomerInfoService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 接待模块查询业务
 */
@Controller
@RequestMapping("/customer/modify")
public class CustomerAction {
    private final Logger logger = LoggerFactory.getLogger(CustomerAction.class);

    @Autowired
    protected CustomerInfoService customerInfoService;

    /**
     * 修改基础信息
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/basic")
    public ModelAndView modifyCustomerInfo(HttpServletRequest request, HttpServletResponse response) {
        getBasePath(request, response);
        ModelAndView model = new ModelAndView();
        model.addObject("customerInfo", customerInfoService.selectByPrimaryKey(request.getParameter("cid")));
        model.setViewName("reception/customer_modify");
        return model;
    }

    /**
     * 修改文交所信息
     * @param request
     * @param response
     */
    @RequestMapping("/exchange")
    public String queryReception(HttpServletRequest request, HttpServletResponse response) {
        getBasePath(request, response);
        // 向前端输出
        return "reception/inquiry_record";
    }

    public void getBasePath(HttpServletRequest request, HttpServletResponse response) {
        String basePath = WebUtils.getBasePath(request, response);
        request.getSession().setAttribute("basePath", basePath);
    }

}

package com.portal.action.reception;

import com.portal.common.util.WebUtils;
import com.portal.service.CustomerCultureInfoService;
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

    @Autowired
    protected CustomerCultureInfoService cultureInfoService;

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
        model.addObject("customerInfo", customerInfoService.selectByPrimaryKey(request.getParameter("id")));
        model.setViewName("reception/customer_modify");
        return model;
    }

    /**
     * 保存基础信息
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/basic/save")
    public String saveCustomerInfo(HttpServletRequest request, HttpServletResponse response) {
        getBasePath(request, response);
        customerInfoService.updateCustomer(request);
        return "redirect:/visit/second?phone=" + request.getParameter("phone") + "&id="
                + request.getParameter("cid");
    }

    /**
     * 修改文交所信息
     * @param request
     * @param response
     */
    @RequestMapping("/exchange")
    public ModelAndView modifyExchange(HttpServletRequest request, HttpServletResponse response) {
        getBasePath(request, response);

        ModelAndView model = new ModelAndView();
        model.addObject("cultureInfo", cultureInfoService.selectByPrimaryKey(request.getParameter("id")));
        model.setViewName("reception/culture_modify");
        return model;
    }

    /**
     * 保存基础信息
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/exchange/save")
    public String saveExchange(HttpServletRequest request, HttpServletResponse response) {
        getBasePath(request, response);
        cultureInfoService.updateCulture(request);
        return "redirect:/visit/second?phone=" + request.getParameter("phone") + "&id="
                + request.getParameter("cid");
    }

    public void getBasePath(HttpServletRequest request, HttpServletResponse response) {
        String basePath = WebUtils.getBasePath(request, response);
        request.getSession().setAttribute("basePath", basePath);
    }
}

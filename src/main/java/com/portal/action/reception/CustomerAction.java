package com.portal.action.reception;

import com.portal.bean.CustomerInfo;
import com.portal.common.util.DateUtil;
import com.portal.common.util.WebUtils;
import com.portal.service.CustomerCultureInfoService;
import com.portal.service.CustomerInfoService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 客户基本信息管理
 */
@Controller
@RequestMapping("/customer/modify")
public class CustomerAction {

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
    @RequestMapping(value = "basic")
    public ModelAndView modifyCustomerInfo(HttpServletRequest request, HttpServletResponse response) {
        getBasePath(request, response);
        ModelAndView model = new ModelAndView();
        model.addObject("customerInfo", customerInfoService.selectByPrimaryKey(request.getParameter("cId")));
        model.setViewName("reception/customer_modify");
        return model;
    }

    /**
     * 保存基础信息
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "basic/save")
    public String saveCustomerInfo(CustomerInfo customerInfo, HttpServletRequest request) {
        customerInfo
                .setBirthday(DateUtil.parseDate(customerInfo.getBirthdayStr(), DateUtil.DATE_FMT_YYYY_MM_DD));
        customerInfoService.updateByPrimaryKeySelective(customerInfo);
        return "redirect:/visit/second?cId="
                + request.getSession().getAttribute("cId");
    }

    /**
     * 修改文交所信息
     * @param request
     * @param response
     */
    @RequestMapping("exchange")
    public ModelAndView modifyExchange(HttpServletRequest request, HttpServletResponse response) {
        getBasePath(request, response);

        ModelAndView model = new ModelAndView();
        model.addObject("customerInfo", customerInfoService.selectByPrimaryKey(request.getParameter("cId")));
        model.addObject("cultureInfo", cultureInfoService.selectByPrimaryKey(request.getParameter("cId")));
        model.setViewName("reception/culture_modify");
        return model;
    }

    /**
     * 保存基础信息
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "exchange/save")
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

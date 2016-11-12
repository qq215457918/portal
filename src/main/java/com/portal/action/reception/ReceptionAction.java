
package com.portal.action.reception;

import com.portal.bean.result.CustomerSimpleInfoForm;
import com.portal.common.util.WebUtils;
import com.portal.service.CustomerInfoService;
import com.portal.service.OrderInfoService;
import com.portal.service.ReceptionInfoService;
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
@RequestMapping("/visit")
public class ReceptionAction {
    private final Logger logger = LoggerFactory.getLogger(ReceptionAction.class);

    @Autowired
    protected ReceptionInfoService receptionInfoService;

    @Autowired
    protected CustomerInfoService customerInfoService;

    @Autowired
    protected OrderInfoService orderInfoService;

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
    @RequestMapping(value = "/first")
    public ModelAndView fristCustomerInfo(HttpServletRequest request, HttpServletResponse response) {
        getBasePath(request, response);
        String phoneNo = request.getParameter("phoneNo");
        boolean exist = customerInfoService.isCustomer(phoneNo);
        ModelAndView model = new ModelAndView();
        if (!exist) {
            model.setViewName("reception/inquiry_add");
            return model;
        }
        CustomerSimpleInfoForm info = customerInfoService.getFristQueryInfo(phoneNo);
        model.setViewName("reception/query_frist");
        model.addObject("result", info);
        return model;
    }

    @RequestMapping(value = "/second")
    public ModelAndView secondCustomerInfo(HttpServletRequest request, HttpServletResponse response) {
        getBasePath(request, response);
        ModelAndView model = new ModelAndView();
        CustomerSimpleInfoForm info = customerInfoService.getFristQueryInfo(request.getParameter("phone"));
       // receptionInfoService.insertReceptionTime("1", "1");
        String customerId = request.getParameter("id");
        model.addObject("info", info);
        model.addObject("goods", orderInfoService.queryGoodsInfo(customerId));
        model.addObject("returnGoods", orderInfoService.queryReturnGoodsInfo(customerId));
        model.addObject("revokeDeposit", orderInfoService.queryRevokeDepositInfo(customerId));
        model.addObject("receptionInfo", receptionInfoService.queryRecordListbyId(customerId));
        model.setViewName("reception/query_second");
        return model;
    }

    @RequestMapping(value = "/add")
    public ModelAndView customerAdd(HttpServletRequest request, HttpServletResponse response) {
        getBasePath(request, response);
        //新增用户信息
        CustomerSimpleInfoForm info = customerInfoService.insertCustomer(request);
        ModelAndView model = new ModelAndView();
        model.addObject("result", info);
        model.setViewName("reception/query_frist");
        return model;
  
    }

    /**
     * 结束接待
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/quit")
    public String receptionQuit(HttpServletRequest request, HttpServletResponse response) {
        getBasePath(request, response);
       // receptionInfoService.updateEndReceptionTime(request.getParameter("id"));
        return "reception/inquiry_query";
    }

    public void getBasePath(HttpServletRequest request, HttpServletResponse response) {
        String basePath = WebUtils.getBasePath(request, response);
        request.getSession().setAttribute("basePath", basePath);
    }

}

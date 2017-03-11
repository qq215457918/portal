package com.portal.action.reception;

import com.portal.bean.Criteria;
import com.portal.bean.result.OrderInfoForm;
import com.portal.common.util.JsonUtils;
import com.portal.common.util.WebUtils;
import com.portal.service.CustomerCultureInfoService;
import com.portal.service.CustomerInfoService;
import com.portal.service.GoodsInfoService;
import com.portal.service.OrderInfoService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * 定金管理
 */
@Controller
@RequestMapping("/deposit")
public class DepositAction {

    @Autowired
    GoodsInfoService goodsInfoService;

    @Autowired
    private OrderInfoService orderInfoService;

    @Autowired
    CustomerCultureInfoService customerCultureInfoService;

    @Autowired
    CustomerInfoService customerInfoService;

    /**
     * 定金页面初始化
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/init")
    public ModelAndView init(HttpServletRequest request, HttpServletResponse response) {
        getBasePath(request, response);
        WebUtils.setAttributeToSession(request);
        ModelAndView model = new ModelAndView();
        model.addObject("cId", request.getParameter("cId"));
        model.setViewName("reception/order_deposit");
        return model;
    }

    /**
     * 查询定金翻页信息
     * @param request
     * @param response
     */
    @RequestMapping(value = "/query")
    public void query(HttpServletRequest request, HttpServletResponse response) {
        getBasePath(request, response);
        Criteria criteria = new Criteria();
        criteria.setMysqlLength(Integer.valueOf(request.getParameter("iDisplayLength")));
        criteria.setMysqlOffset(Integer.valueOf(request.getParameter("iDisplayStart")));
        criteria.put("payType", "1");
        criteria.put("orderType", "1");
        criteria.put("deleteFlag", "0");
        criteria.put("customerId", (String) request.getSession().getAttribute("cId"));
        criteria.put("goodsName", request.getParameter("goodsName"));
        criteria.put("goodCode", request.getParameter("goodCode"));
        List<OrderInfoForm> resultList = orderInfoService.getDepositInfo(criteria);
        int count = orderInfoService.countByExample(criteria);
        JsonUtils.resultJson(resultList, count, response, request);
    }

    @RequestMapping(value = "/cancel", method = RequestMethod.POST)
    public void cancelDeposit(HttpServletRequest request, HttpServletResponse response) {
        getBasePath(request, response);
        boolean result = orderInfoService.updateCancelDeposit(request.getParameter("orderId"));
        JsonUtils.outJsonString(String.valueOf(result), response);
    }

    @RequestMapping(value = "/pay", method = RequestMethod.POST)
    public void payDeposit(HttpServletRequest request, HttpServletResponse response) {
        getBasePath(request, response);
        boolean result = orderInfoService.updatePayDeposit(request.getParameter("orderId"));
        JsonUtils.outJsonString(String.valueOf(result), response);
    }

    public void getBasePath(HttpServletRequest request, HttpServletResponse response) {
        String basePath = WebUtils.getBasePath(request, response);
        request.getSession().setAttribute("basePath", basePath);
    }
}

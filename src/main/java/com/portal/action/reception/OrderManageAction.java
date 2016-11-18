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
 * 订单管理模块
 */
@Controller
@RequestMapping("/order/manage")
public class OrderManageAction {

    @Autowired
    GoodsInfoService goodsInfoService;

    @Autowired
    private OrderInfoService orderInfoService;

    @Autowired
    CustomerCultureInfoService customerCultureInfoService;

    @Autowired
    CustomerInfoService customerInfoService;

    /**
     * 购买商品页面初始化
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/init")
    public ModelAndView init(HttpServletRequest request, HttpServletResponse response) {
        getBasePath(request, response);
        WebUtils.setAttributeToSession(request);
        ModelAndView model = new ModelAndView();
        model.setViewName("reception/order_manage");
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

        criteria.put("status", "4");
        criteria.put("payType", "0");
        //criteria.put("orderType", "1");
        criteria.put("deleteFlag", "0");
        criteria.put("goodsName", request.getParameter("goodsName"));
        criteria.put("staffName", request.getParameter("staffName"));
        //criteria.put("typeList", request.getParameter("typeList").split(","));
        List<OrderInfoForm> resultList = orderInfoService.getOrderInfo(criteria);
        int count = orderInfoService.countByExample(criteria);
        JsonUtils.resultJson(resultList, count, response, request);
    }

    /**
     * 退货业务
     * @param request
     * @param response
     */
    @RequestMapping(value = "/return", method = RequestMethod.POST)
    public void cancelDeposit(HttpServletRequest request, HttpServletResponse response) {
        getBasePath(request, response);
        boolean result = orderInfoService.updateOrderReturn(request.getParameter("orderId"));
        JsonUtils.outJsonString(String.valueOf(result), response);
    }

    /**
     * 换货业务
     * @param request
     * @param response
     */
    @RequestMapping(value = "/replace", method = RequestMethod.POST)
    public void payDeposit(HttpServletRequest request, HttpServletResponse response) {
        getBasePath(request, response);
        boolean result = orderInfoService.updateOrderReplace(request.getParameter("orderId"));
        JsonUtils.outJsonString(String.valueOf(result), response);
    }

    public void getBasePath(HttpServletRequest request, HttpServletResponse response) {
        String basePath = WebUtils.getBasePath(request, response);
        request.getSession().setAttribute("basePath", basePath);
    }
}

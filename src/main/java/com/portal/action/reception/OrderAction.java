package com.portal.action.reception;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.portal.bean.Criteria;
import com.portal.bean.OrderInfo;
import com.portal.common.util.JsonUtils;
import com.portal.common.util.WebUtils;
import com.portal.service.OrderInfoService;

import net.sf.json.JSONObject;

/**
 * 礼物管理模块
 */
@Controller
@RequestMapping("/order")
public class OrderAction {
	
	@Autowired
	private OrderInfoService orderInfoService;

    /**
     * 购买商品页面初始化
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/init")
    public ModelAndView init(HttpServletRequest request, HttpServletResponse response) {
        getBasePath(request, response);
        ModelAndView model = new ModelAndView();
        model.setViewName("reception/purchase_goods");
        return model;
    }

    /**
     * 正常领取礼物流程
     * @param request
     * @param response
     */
    @RequestMapping(value = "/receive", method = RequestMethod.POST)
    public void insertPresentOrder(HttpServletRequest request, HttpServletResponse response) {
        getBasePath(request, response);
        JSONObject resultJson = new JSONObject();
        JsonUtils.outJsonString(resultJson.toString(), response);
    }

    public void getBasePath(HttpServletRequest request, HttpServletResponse response) {
        String basePath = WebUtils.getBasePath(request, response);
        request.getSession().setAttribute("basePath", basePath);
    }

    @RequestMapping("orderModifyIndex")
    public String orderModifyIndex(HttpServletRequest request, HttpServletResponse response){
    	return "reception/order_modify";
    }
    
    /**
     * @Title: orderModifyList 
     * @Description: 查询修改订单列表
     * @param request
     * @param response
     * @return 
     * @return String
     * @throws
     */
    @RequestMapping("orderModifyList")
    public void orderModifyList(HttpServletRequest request, HttpServletResponse response){
    	Criteria criteria = new Criteria();
		criteria.setMysqlLength(Integer.valueOf(request.getParameter("iDisplayLength")));
		criteria.setMysqlOffset(Integer.valueOf(request.getParameter("iDisplayStart")));
		
		criteria.put("goodsName", request.getParameter("goodsName"));
		criteria.put("customerName", request.getParameter("customerName"));
		criteria.put("orderType", request.getParameter("orderType"));
		
		List<OrderInfo> resultList = orderInfoService.selectOrderModifyList(criteria);
		
		int count = orderInfoService.countOrderModifyList(criteria);
		
		JsonUtils.resultJson(resultList, count, response, request);
    }
    
    /**
     * @Title: orderModifyList 
     * @Description: 修改订单
     * @param request
     * @param response
     * @return 
     * @return String
     * @throws
     */
    @RequestMapping("orderModifyInfo") 
    public String orderModifyInfo(HttpServletRequest request, HttpServletResponse response){
    	OrderInfo orderInfo = new OrderInfo();
    	orderInfo.setId(request.getParameter("orderId"));
    	Long payPrice = null == request.getParameter("payPrice") || "".equals(request.getParameter("payPrice"))?0:Long.valueOf(request.getParameter("payPrice"));
    	orderInfo.setPayPrice(payPrice);
    	orderInfo.setOrderType(request.getParameter("orderType"));
    	orderInfo.setCustomerPhone(request.getParameter("customerPhone"));
    	orderInfoService.updateByPrimaryKeySelective(orderInfo);
    	
    	return "redirect:orderModifyIndex"; 
    }
}

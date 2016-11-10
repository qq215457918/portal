package com.portal.action.reception;

import com.portal.bean.Criteria;
import com.portal.bean.OrderInfo;
import com.portal.bean.result.GoodsInfoForm;
import com.portal.common.util.JsonUtils;
import com.portal.common.util.WebUtils;
import com.portal.service.CustomerCultureInfoService;
import com.portal.service.CustomerInfoService;
import com.portal.service.GoodsInfoService;
import com.portal.service.OrderInfoService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * 订单管理模块
 */
@Controller
@RequestMapping("/order")
public class OrderAction {

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
        ModelAndView model = new ModelAndView();
        model.setViewName("reception/purchase_goods");
        model.addObject("submitFlag", request.getParameter("submitFlag"));
        return model;
    }

    /**
     * 1 所有商品信息查询
     * 2 点击商品信息，放在session里面
     * 3 点击配送弹出配送商品，选择商品后进入到购物车中
     * @param request
     * @param response
     */
    @RequestMapping(value = "/receive", method = RequestMethod.POST)
    public void insertPresentOrder(HttpServletRequest request, HttpServletResponse response) {
        getBasePath(request, response);
        String type = request.getParameter("type");
        List<GoodsInfoForm> goodsFormList = goodsInfoService.selectGoodsInfo(request, Integer.parseInt(type));
        int count = goodsInfoService.getGoodsCount(request, Integer.parseInt(type));
        JsonUtils.resultJson(goodsFormList, count, response, request);
    }

    //是否有交管所信息
    @RequestMapping(value = "/hasCustomer", method = RequestMethod.POST)
    public void hasCustomer(HttpServletRequest request, HttpServletResponse response) {
        getBasePath(request, response);
        JSONObject resultJson = new JSONObject();
        resultJson.put("result", customerCultureInfoService.getCultureInfo(request.getParameter("cId")));
        JsonUtils.outJsonString(resultJson.toString(), response);
    }

    //在购物车页面进行检查
    //检查商品数量 and 商品是否被删除
    @RequestMapping(value = "/check", method = RequestMethod.POST)
    public void checkGoodsInfo(HttpServletRequest request, HttpServletResponse response) {
        getBasePath(request, response);
        JSONObject resultJson = new JSONObject();
        resultJson.put("result", goodsInfoService.checkGoodsInfo(request));
        JsonUtils.outJsonString(resultJson.toString(), response);
    }

    /**
     * 进入结算页面
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/account")
    public ModelAndView account(HttpServletRequest request, HttpServletResponse response) {
        getBasePath(request, response);
        ModelAndView model = new ModelAndView();
        model.setViewName("reception/shopping_cart");
        //model.addObject("goodInfo", JSONObject.fromObject(goodInfo.substring(1, goodInfo.length() - 1)));
        model.addObject("goodInfo", JSONArray.fromObject(request.getParameter("goodInfo")));
        return model;
    }

    /**
     * 提交订单页面
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/submit")
    public String insertOrder(HttpServletRequest request, HttpServletResponse response) {
        getBasePath(request, response);
        orderInfoService.insertOrder(request);
        return "redirect:/order/init?submitFlag=true";
    }

    public void getBasePath(HttpServletRequest request, HttpServletResponse response) {
        String basePath = WebUtils.getBasePath(request, response);
        request.getSession().setAttribute("basePath", basePath);
    }

    @RequestMapping("orderModifyIndex")
    public String orderModifyIndex(HttpServletRequest request, HttpServletResponse response) {
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
    public void orderModifyList(HttpServletRequest request, HttpServletResponse response) {
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
    public String orderModifyInfo(HttpServletRequest request, HttpServletResponse response) {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setId(request.getParameter("orderId"));
        Long payPrice = null == request.getParameter("payPrice") || "".equals(request.getParameter("payPrice"))
                ? 0 : Long.valueOf(request.getParameter("payPrice"));
        orderInfo.setPayPrice(payPrice);
        orderInfo.setOrderType(request.getParameter("orderType"));
        orderInfo.setCustomerPhone(request.getParameter("customerPhone"));
        orderInfoService.updateByPrimaryKeySelective(orderInfo);

        return "redirect:orderModifyIndex";
    }
}

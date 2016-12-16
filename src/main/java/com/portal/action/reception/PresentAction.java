package com.portal.action.reception;

import com.portal.bean.Criteria;
import com.portal.bean.GoodsInfo;
import com.portal.bean.OrderInfo;
import com.portal.bean.result.OrderInfoFormNew;
import com.portal.common.util.JsonUtils;
import com.portal.common.util.WebUtils;
import com.portal.service.GoodsInfoService;
import com.portal.service.OrderInfoService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * 礼物管理模块
 */
@Controller
@RequestMapping("/present")
public class PresentAction {

    @Autowired
    GoodsInfoService goodsInfoService;
    @Autowired
    OrderInfoService orderInfoService;

    /**
     * 获取礼品信息
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/list")
    public ModelAndView getPresentList(HttpServletRequest request, HttpServletResponse response) {
        getBasePath(request, response);
        WebUtils.setAttributeToSession(request);
        ModelAndView model = new ModelAndView();
        List<GoodsInfo> goodsInfoList = goodsInfoService.selectPresentInfo(request);
        model.setViewName("reception/present_info");
        model.addObject("cId", request.getParameter("cId"));
        model.addObject("goodsInfoList", goodsInfoList);
        return model;
    }

    /**
     * 特殊礼物流程，需要审批
      * @param request
     * @param response
     */
    @RequestMapping(value = "/review", method = RequestMethod.POST)
    public void reviewPresent(HttpServletRequest request, HttpServletResponse response) {
        getBasePath(request, response);
        JSONObject resultJson = new JSONObject();
        resultJson.put("result", orderInfoService.insertPresentOrder(request, 1));
        JsonUtils.outJsonString(resultJson.toString(), response);
    }

    /**
     * 查询礼品领取记录
     * @param request
     * @param response
     */
    @RequestMapping(value = "/record", method = RequestMethod.POST)
    public void todayPresent(HttpServletRequest request, HttpServletResponse response) {
        getBasePath(request, response);
        JSONObject resultJson = new JSONObject();
        resultJson.put("result",
                orderInfoService.selectTodayPresentList(request.getSession().getAttribute("cId").toString()));
        JsonUtils.outJsonString(resultJson.toString(), response);
    }

    @RequestMapping(value = "/query")
    public String checkList(HttpServletRequest request, HttpServletResponse response) {
        getBasePath(request, response);
        WebUtils.setAttributeToSession(request);
        return "myjob/present_check";
    }

    /**
     * 查询需要确认的礼品单
     * order_type = 4 赠品  6 VIP赠品
     * finance_flag = 0
     * 审批完毕审批finance_flag为 1 
     * @param request
     * @param response
     */
    @RequestMapping(value = "/check", method = RequestMethod.POST)
    public void check(HttpServletRequest request, HttpServletResponse response) {
        Criteria criteria = new Criteria();
        criteria.setMysqlLength(Integer.valueOf(request.getParameter("iDisplayLength")));
        criteria.setMysqlOffset(Integer.valueOf(request.getParameter("iDisplayStart")));

        criteria.put("deleteFlag", "0");
        criteria.put("financeFlag", "0");
        criteria.put("repurchaseList", "true");//5待审批  7回购待确认
        criteria.setOrderByClause("create_date");

        List<OrderInfoFormNew> resultList = orderInfoService.updateCheckPresentList(criteria);
        int count = resultList.size();
        JsonUtils.resultJson(resultList, count, response, request);
    }

    /**
     * 审批确认 修改 finance_flag 0 -》 1
     * @param request
     * @param response
     */
    @RequestMapping(value = "/confirm", method = RequestMethod.POST)
    public void confirm(HttpServletRequest request, HttpServletResponse response) {
        getBasePath(request, response);
        JSONObject resultJson = new JSONObject();
        OrderInfo record = new OrderInfo();
        record.setId(request.getParameter("iDisplayLength"));
        record.setFinanceFlag("1");
        resultJson.put("result", orderInfoService.updateByPrimaryKey(record) > 0 ? true : false);
        //  orderInfoService.selectTodayPresentList(request.getSession().getAttribute("cId").toString()));
        JsonUtils.outJsonString(resultJson.toString(), response);
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
        resultJson.put("result", orderInfoService.insertPresentOrder(request, 0));
        JsonUtils.outJsonString(resultJson.toString(), response);
    }

    public void getBasePath(HttpServletRequest request, HttpServletResponse response) {
        String basePath = WebUtils.getBasePath(request, response);
        request.getSession().setAttribute("basePath", basePath);
    }

}

package com.portal.action.reception;

import com.portal.bean.Criteria;
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

/**
 * 礼物管理模块
 */
@Controller
@RequestMapping("/exchange")
public class ExchangeAction {

    @Autowired
    GoodsInfoService goodsInfoService;
    @Autowired
    OrderInfoService orderInfoService;

    /**
     * 查询礼品领取记录
     * @param request
     * @param response
     */
    @RequestMapping(value = "/record", method = RequestMethod.POST)
    public void todayexchange(HttpServletRequest request, HttpServletResponse response) {
        getBasePath(request, response);
        JSONObject resultJson = new JSONObject();
        resultJson.put("result",
                orderInfoService.selectExchangeList(request.getSession().getAttribute("cId").toString()));
        JsonUtils.outJsonString(resultJson.toString(), response);
    }

    /**
     * 提交审核业务
      * @param request
     * @param response
     */
    @RequestMapping(value = "/review", method = RequestMethod.POST)
    public void reviewPresent(HttpServletRequest request, HttpServletResponse response) {
        getBasePath(request, response);
        JSONObject resultJson = new JSONObject();
        resultJson.put("result",
                orderInfoService.insertExchangeOrder(request));
        JsonUtils.outJsonString(resultJson.toString(), response);
    }

    /**
     * 查询需要确认的礼品单
     * order_type = 8
     * finance_flag = 0
     * status 5 待审批
     * @param request
     * @param response
     */
    @RequestMapping(value = "check", method = RequestMethod.POST)
    public void exchangeCheck(HttpServletRequest request, HttpServletResponse response) {
        Criteria criteria = new Criteria();
        criteria.setMysqlLength(Integer.valueOf(request.getParameter("iDisplayLength")));
        criteria.setMysqlOffset(Integer.valueOf(request.getParameter("iDisplayStart")));

        criteria.put("deleteFlag", "0");
        criteria.put("status", "5");
        criteria.put("orderType", "8");//4赠品  6VIP赠品
        criteria.setOrderByClause("create_date desc");

        List<OrderInfoFormNew> resultList = orderInfoService.updateCheckPresentList(criteria);
        int count = resultList.size();
        JsonUtils.resultJson(resultList, count, response, request);
    }

    /**
     * 审批确认 修改 status 5 -》 0
     * @param request
     * @param response
     */
    @RequestMapping(value = "confirm", method = RequestMethod.POST)
    public void confirm(HttpServletRequest request, HttpServletResponse response) {
        getBasePath(request, response);
        JSONObject resultJson = new JSONObject();
        OrderInfo record = new OrderInfo();
        Criteria criteria = new Criteria();
        criteria.put("orderNumber", request.getParameter("orderNum"));
        record.setStatus("0");
        resultJson.put("result",
                orderInfoService.updateByExampleSelective(record, criteria) > 0 ? true : false);
        JsonUtils.outJsonString(resultJson.toString(), response);
    }

    public void getBasePath(HttpServletRequest request, HttpServletResponse response) {
        String basePath = WebUtils.getBasePath(request, response);
        request.getSession().setAttribute("basePath", basePath);
    }

}

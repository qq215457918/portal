
package com.portal.action.reception;

import com.portal.bean.Criteria;
import com.portal.bean.result.OrderInfoFormNew;
import com.portal.common.util.JsonUtils;
import com.portal.common.util.WebUtils;
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

@Controller
@RequestMapping("/repurchase")
public class RepurchaseAction {

    @Autowired
    OrderInfoService orderInfoService;

    /**
     * 回购页面初始化
     * @return
     */
    @RequestMapping(value = "init", method = RequestMethod.GET)
    public ModelAndView init(HttpServletRequest request, HttpServletResponse response) {
        WebUtils.setAttributeToSession(request);
        getBasePath(request, response);
        ModelAndView model = new ModelAndView();
        model.addObject("cId", request.getParameter("cId"));
        model.setViewName("reception/repurchase_manage");
        return model;
    }

    /**
     * 获取客户的回购商品订单信息
     * 修改订单回购类型为订单详情进行回购
     * order_type = 5 回购
     * order_detail_info count 为负数
     * @param request
     * @param response
     */
    @RequestMapping(value = "list", method = RequestMethod.POST)
    public void getList(HttpServletRequest request, HttpServletResponse response) {
        Criteria criteria = new Criteria();
        criteria.setMysqlLength(Integer.valueOf(request.getParameter("iDisplayLength")));
        criteria.setMysqlOffset(Integer.valueOf(request.getParameter("iDisplayStart")));

        criteria.put("deleteFlag", "0");
        criteria.put("orderType", "5");
        criteria.put("customerId", request.getParameter("customerId"));
        criteria.setOrderByClause("create_date");
        //criteria.put("repurchaseFlag", true); //5待审批 6回购待确认

        List<OrderInfoFormNew> resultList = orderInfoService.getOrderInfoNew(criteria);
        int count = resultList.size();
        JsonUtils.resultJson(resultList, count, response, request);
    }

    /**
     * 为这个用户的某个商品申请回购
     * 应该在订单管理页面进行申请一条回购记录，并等待审核。
     * 特殊审批的时候需要加字段  status=5 待审批  如果已经审批，则status=0
     * @param request
     * @param response
     */
    @RequestMapping(value = "apply", method = RequestMethod.GET)
    public void applyRepurchase(HttpServletRequest request, HttpServletResponse response) {

    }

    /**
     * 领导审核回购单
     * 
     * @param request
     * @param response
     */
    @RequestMapping(value = "audit", method = RequestMethod.GET)
    public void audit(HttpServletRequest request, HttpServletResponse response) {

    }

    /**
     * 在顾客登门时，业务员确认回购单
     * status 6待确认
     * @param request
     * @param response
     */
    @RequestMapping(value = "check", method = RequestMethod.POST)
    public void check(HttpServletRequest request, HttpServletResponse response) {
        getBasePath(request, response);
        JSONObject resultJson = new JSONObject();
        resultJson.put("result", orderInfoService.updateRepurchaseOrder(request));
        JsonUtils.outJsonString(resultJson.toString(), response);
    }

    /**
     * 回购页面初始化
     * @return
     */
    @RequestMapping(value = "job", method = RequestMethod.GET)
    public ModelAndView job(HttpServletRequest request, HttpServletResponse response) {
        WebUtils.setAttributeToSession(request);
        getBasePath(request, response);
        ModelAndView model = new ModelAndView();
        model.setViewName("myjob/repurchase_query");
        return model;
    }

    /**
     * 可回购商品查询
     * 查询商品信息
     * @param request
     * @param response
     */
    @RequestMapping(value = "query", method = RequestMethod.GET)
    public void query(HttpServletRequest request, HttpServletResponse response) {
        getBasePath(request, response);
        Criteria criteria = new Criteria();
        criteria.setMysqlLength(Integer.valueOf(request.getParameter("iDisplayLength")));
        criteria.setMysqlOffset(Integer.valueOf(request.getParameter("iDisplayStart")));
        criteria.put("status", "4");//已完成订单
        criteria.put("payType", "0");//全款支付
        //criteria.put("orderType", "1");
        criteria.put("repurchased", true);//回购商品查询 order_type in（'1','5'）
        criteria.put("deleteFlag", "0");
        criteria.put("goodsName", request.getParameter("goodsName"));
        criteria.put("staffName", request.getParameter("staffName"));
        //criteria.put("typeList", request.getParameter("typeList").split(","));
        //List<OrderInfoFormNew> getOrderInfoNew
        List<OrderInfoFormNew> resultList = orderInfoService.getOrderInfoNew(criteria);
        int count = resultList.size();
        JsonUtils.resultJson(resultList, count, response, request);
    }

    /**
     * 在顾客登门时，业务员确认回购单
     * status 6待确认
     * @param request
     * @param response
     */
    @RequestMapping(value = "normal", method = RequestMethod.POST)
    public void normal(HttpServletRequest request, HttpServletResponse response) {
        getBasePath(request, response);
        JSONObject resultJson = new JSONObject();
        resultJson.put("result", orderInfoService.updateRepurchaseOrder(request));
        JsonUtils.outJsonString(resultJson.toString(), response);
    }

    public void getBasePath(HttpServletRequest request, HttpServletResponse response) {
        String basePath = WebUtils.getBasePath(request, response);
        request.getSession().setAttribute("basePath", basePath);
    }

}

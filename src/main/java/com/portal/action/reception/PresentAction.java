package com.portal.action.reception;

import com.portal.bean.GoodsInfo;
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
        resultJson.put("result", orderInfoService.selectTodayPresentList(request.getParameter("customerId")));
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

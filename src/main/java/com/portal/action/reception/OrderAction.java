package com.portal.action.reception;

import com.portal.common.util.JsonUtils;
import com.portal.common.util.WebUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * 礼物管理模块
 */
@Controller
@RequestMapping("/order")
public class OrderAction {

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

}

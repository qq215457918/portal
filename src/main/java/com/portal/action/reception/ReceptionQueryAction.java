package com.portal.action.reception;

import com.portal.common.util.JsonUtils;
import com.portal.common.util.WebUtils;
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
 * myjob 模块
 * 接待模块查询业务
 */
@Controller
@RequestMapping("/reception")
public class ReceptionQueryAction {
    private final Logger logger = LoggerFactory.getLogger(ReceptionQueryAction.class);

    @Autowired
    protected ReceptionInfoService receptionInfoService;

    /**
     * 初始化进入到查询页面
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "init")
    public ModelAndView receptionING(HttpServletRequest request, HttpServletResponse response) {
        WebUtils.setAttributeToSession(request);
        getBasePath(request, response);
        ModelAndView model = new ModelAndView();
        model.addObject("cId", request.getParameter("cId"));
        model.setViewName("reception/inquiry_record");
        return model;
    }

    /**
     * 异步查询
     * @param request
     * @param response
     */
    @RequestMapping("/query")
    public void queryReception(HttpServletRequest request, HttpServletResponse response) {
        getBasePath(request, response);
        // 向前端输出
        JsonUtils.outJsonString(receptionInfoService.receptionING(request, response).toString(), response);
    }

    public void getBasePath(HttpServletRequest request, HttpServletResponse response) {
        String basePath = WebUtils.getBasePath(request, response);
        request.getSession().setAttribute("basePath", basePath);
    }

}

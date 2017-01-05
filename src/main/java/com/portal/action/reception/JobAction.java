
package com.portal.action.reception;

import com.portal.common.util.WebUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/job")
public class JobAction {

    /**
     * 进入到myjob页面
     * @param request
     * @param response
     * @return
     */
    @RequiresPermissions("job:button")
    @RequestMapping(value = "init")
    public ModelAndView init(HttpServletRequest request, HttpServletResponse response) {
        WebUtils.setAttributeToSession(request);
        getBasePath(request, response);
        ModelAndView model = new ModelAndView();
        model.setViewName("myjob/main");
        return model;
    }

    @RequiresPermissions("job:reception")
    @RequestMapping(value = "reception")
    public ModelAndView reception(HttpServletRequest request, HttpServletResponse response) {
        WebUtils.setAttributeToSession(request);
        getBasePath(request, response);
        ModelAndView model = new ModelAndView();
        model.setViewName("myjob/inquiry_record");
        return model;
    }

    @RequiresPermissions("job:present")
    @RequestMapping(value = "present")
    public String checkList(HttpServletRequest request, HttpServletResponse response) {
        getBasePath(request, response);
        WebUtils.setAttributeToSession(request);
        return "myjob/present_check";
    }

    /**
     * job:repurchasec= job:repurchase check
     * 进入回购审批页面
     * 经理级别使用
     * @param request
     * @param response
     */
    @RequiresPermissions("job:repurchasec")
    @RequestMapping(value = "repurchasec", method = RequestMethod.GET)
    public String manage(HttpServletRequest request, HttpServletResponse response) {
        WebUtils.setAttributeToSession(request);
        getBasePath(request, response);
        return "myjob/repurchase_check";
    }

    /**
     * job:repurchaseq= job:repurchase query
     * 回购查询页面初始化
     * 普通员工使用
     * @return
     */
    @RequiresPermissions("job:repurchaseq")
    @RequestMapping(value = "repurchaseq", method = RequestMethod.GET)
    public ModelAndView repurchase(HttpServletRequest request, HttpServletResponse response) {
        WebUtils.setAttributeToSession(request);
        getBasePath(request, response);
        ModelAndView model = new ModelAndView();
        model.setViewName("myjob/repurchase_query");
        return model;
    }

    public void getBasePath(HttpServletRequest request, HttpServletResponse response) {
        String basePath = WebUtils.getBasePath(request, response);
        request.getSession().setAttribute("basePath", basePath);
    }

}

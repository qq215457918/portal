package com.portal.action.reception;

import com.portal.bean.Criteria;
import com.portal.common.util.WebUtils;
import com.portal.service.CultureInfoService;
import com.portal.service.CustomerCultureInfoService;
import com.portal.service.CustomerInfoService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 客户基本信息管理
 */
@Controller
@RequestMapping("/culture")
public class CultureAction {

    @Autowired
    protected CustomerInfoService customerInfoService;

    @Autowired
    protected CustomerCultureInfoService cultureCultureInfoService;

    @Autowired
    protected CultureInfoService cultureInfoService;

    /**
     * 修改文交所信息
     * @param request
     * @param response
     */
    @RequestMapping("list")
    public ModelAndView modifyExchange(HttpServletRequest request, HttpServletResponse response) {
        getBasePath(request, response);

        ModelAndView model = new ModelAndView();
        //model.addObject("customerInfo", customerInfoService.selectByPrimaryKey(request.getParameter("cId")));
        // model.addObject("cultureInfo", cultureInfoService.selectByPrimaryKey(request.getParameter("cId")));
        Criteria example = new Criteria();
        example.put("deleteFlag", "0");
        example.setOrderByClause("name desc");
        model.addObject("cultureInfo", cultureInfoService.selectByExample(example));
        model.setViewName("reception/culture_modify");
        return model;
    }

    /**
     * 保存基础信息
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "save")
    public String saveExchange(HttpServletRequest request, HttpServletResponse response) {
        getBasePath(request, response);
        cultureCultureInfoService.updateCulture(request);
        return "redirect:/visit/second?phone=" + request.getParameter("phone") + "&id="
                + request.getParameter("cid");
    }

    public void getBasePath(HttpServletRequest request, HttpServletResponse response) {
        String basePath = WebUtils.getBasePath(request, response);
        request.getSession().setAttribute("basePath", basePath);
    }
}

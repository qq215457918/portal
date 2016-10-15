
package com.portal.action.reception;

import com.portal.bean.result.CustomerSimpleInfoForm;
import com.portal.service.CustomerInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 接待模块查询业务
 */
@Controller
//@RequestMapping("/reception")
public class ReceptionQueryAction {
    private final Logger logger = LoggerFactory.getLogger(ReceptionQueryAction.class);

    @Autowired
    CustomerInfoService customerInfoService;

    @RequestMapping(value = "/frist")
    public ModelAndView fristQuery() {
        String phone = "15041298725";
        CustomerSimpleInfoForm from = customerInfoService.getFristQueryInfo(phone);
        ModelAndView model = new ModelAndView();
        model.setViewName("reception/query_frist");
        model.addObject("from", from);
        return model;
    }

    @RequestMapping(value = "/second")
    public ModelAndView findByName() {
        String cid = "1";
        CustomerSimpleInfoForm from = customerInfoService.getFristQueryInfo(cid);
        ModelAndView model = new ModelAndView();
        model.setViewName("reception/query_frist");
        model.addObject("from", from);
        return model;
    }

}

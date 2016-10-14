
package com.portal.action.reception;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 接待模块查询业务
 */
@Controller
@RequestMapping("/reception")
public class ReceptionQueryAction {

    @RequestMapping(value = "/frist")
    public String costomerInfoIndex() {
        return "reception/query_frist.jsp";
    }

}

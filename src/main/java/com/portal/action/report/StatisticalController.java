package com.portal.action.report;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.portal.bean.Criteria;

/**
 * @ClassName: StatisticalController 
 * @Description: 统计系统控制类
 * @author Xia ZhengWei
 * @date 2016年10月18日 下午11:15:22
 */

@Controller
@RequestMapping("/statistical")
public class StatisticalController {

    Logger logger = LoggerFactory.getLogger(this.getClass());
    
    
    // 公共查询条件类
    Criteria criteria = new Criteria();
    
}

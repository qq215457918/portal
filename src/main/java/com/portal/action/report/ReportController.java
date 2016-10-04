package com.portal.action.report;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.portal.bean.Criteria;
import com.portal.common.util.DateUtil;
import com.portal.common.util.JsonUtils;
import com.portal.common.util.WebUtils;
import com.portal.service.VisitReportInfoService;

import net.sf.json.JSONObject;

/**
 * @ClassName: ReportController 
 * @Description: 统计报表控制类
 * @author Xia ZhengWei
 * @date 2016年9月21日 下午11:16:28
 */

@Controller
@RequestMapping("/report")
public class ReportController {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	// 登门统计Service
	@Autowired
	private VisitReportInfoService reportService;
	
	// 公共查询条件类
	Criteria criteria = new Criteria();
	
	
	//TODO - 开发顺序：
	// 1.查询两个地区的接待客户数量（默认查询上一周的数据）
    // 2.点击地区进入该地区详细的接待情况
	// 1.进入页面先显示接待统计(默认上一周) 
	// 2.然后是每日登门统计
	// 3.今天机构业绩统计
	// 4.然后是部门业绩 最后是业务员业绩
	// 5.个人业绩排名
	
	/**
	 * @Title: toReceiveStatistics 
	 * @Description: 进入接待统计页面
	 * @param request
	 * @param response
	 * @return String
	 * @throws
	 */
	@RequestMapping(value = "/toReceiveStatistics")
	public String toReceiveStatistics(HttpServletRequest request, HttpServletResponse response) {
	    //获取项目基础路径
        String basePath = WebUtils.getBasePath(request, response);
        //供页面和后台引用项目路径使用
        request.getSession().setAttribute("basePath", basePath); 
        
        // 初始化页面输入框中的日期值（默认上一周的时间）
        request.setAttribute("startReportDate", DateUtil.formatDate(DateUtil.getLastWeekMonday(new Date()), "yyyy-MM-dd"));
        request.setAttribute("endReportDate", DateUtil.formatDate(DateUtil.getLastWeekSunday(new Date()), "yyyy-MM-dd"));
        
        return "report/receive_statistics";
	}
	
	/**
	 * @Title: ajaxStatistics 
	 * @Description: 异步获取接待客户统计数据
	 * @param request
	 * @param response 
	 * @return void
	 * @throws
	 */
	@RequestMapping("/ajaxStatistics")
    public void ajaxStatistics(HttpServletRequest request, HttpServletResponse response) {
	    // 异步获取数据
	    JSONObject results = reportService.ajaxStatistics(request, response);
	    // 向前端输出
	    JsonUtils.outJsonString(results.toString(), response);
    }
	
	/**
	 * @Title: toReceiveAreaList 
	 * @Description: 进入指定地区下的接待客户统计列表
	 * @param request
	 * @param response
	 * @return String
	 * @throws
	 */
	@RequestMapping("/toReceiveAreaList")
	public String toReceiveAreaList(HttpServletRequest request, HttpServletResponse response) {
	    // 将上一个图表页面选中的日期传递到列表页中显示
	    request.setAttribute("receiverArea", request.getParameter("receiverArea"));
	    request.setAttribute("startReportDate", request.getParameter("startReportDate"));
	    request.setAttribute("endReportDate", request.getParameter("endReportDate"));
	    return "report/receive_area_list";
	}
	
	/**
	 * @Title: ajaxReceiveAreaList 
	 * @Description: 异步获取指定地区下的接待客户统计数据 
	 * @param request
	 * @param response 
	 * @return void
	 * @throws
	 */
	@RequestMapping("/ajaxReceiveAreaList")
    public void ajaxReceiveAreaList(HttpServletRequest request, HttpServletResponse response) {
        // 异步获取数据
        JSONObject results = reportService.ajaxReceiveAreaList(request, response);
        // 向前端输出
        JsonUtils.outJsonString(results.toString(), response);
    }
	

}

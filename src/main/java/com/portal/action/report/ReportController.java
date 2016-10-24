package com.portal.action.report;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.portal.bean.Criteria;
import com.portal.bean.GroupInfo;
import com.portal.common.exception.SystemException;
import com.portal.common.util.DateUtil;
import com.portal.common.util.JsonUtils;
import com.portal.common.util.StringUtil;
import com.portal.common.util.WebUtils;
import com.portal.service.ButtPerforDetailInfoService;
import com.portal.service.CustomerInfoService;
import com.portal.service.DeptPerformanceInfoService;
import com.portal.service.GroupInfoService;
import com.portal.service.OrderDetailInfoService;
import com.portal.service.ReceptionInfoService;
import com.portal.service.StorehouseOperateInfoService;
import com.portal.service.VisitEverydayInfoService;
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
	
	// 接待统计Service
	@Autowired
	private VisitReportInfoService reportService;
	
	// 每日登门Service
	@Autowired
	private VisitEverydayInfoService visitEveryService;
	
	// 部门业绩Service
	@Autowired
	private DeptPerformanceInfoService deptPerforService;
	
	// 组织机构Service
	@Autowired
	private GroupInfoService groupService;
	
	// 订单详细Service
    @Autowired
    private OrderDetailInfoService orderDetailService;
	
	// 客户Service
    @Autowired
    private CustomerInfoService customerService;
    
    // 日常接待Service
    @Autowired
    private ReceptionInfoService receptionService;
    
    // 仓库操作Service
    @Autowired
    private StorehouseOperateInfoService storehouseService;
    
    // 展厅客服对接业绩Service
    @Autowired
    private ButtPerforDetailInfoService buttPerforService;
	
	// 公共查询条件类
	Criteria criteria = new Criteria();
	
	// ------------------------- 接待统计 入口：toReceiveStatistics ---------------------------------
	
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
	
	// ------------------------- 每日登门统计 入口：toVisitEveryDay ---------------------------------
	
	/**
	 * @Title: toVisitEveryDay 
	 * @Description: 进入每日登门统计页面
	 * @param request
	 * @param response
	 * @return String
	 * @throws
	 */
	@RequestMapping("/toVisitEveryDay")
	public String toVisitEveryDay(HttpServletRequest request, HttpServletResponse response) {
	    // 初始化页面输入框中的日期值（默认上一周的时间）
        request.setAttribute("startVisitDate", DateUtil.formatDate(DateUtil.getLastWeekMonday(new Date()), "yyyy-MM-dd"));
        // request.setAttribute("endVisitDate", DateUtil.formatDate(DateUtil.getLastWeekSunday(new Date()), "yyyy-MM-dd"));
	    return "report/visit_everyday";
	}
	
	/**
	 * @Title: ajaxVisitEveryDay 
	 * @Description: 异步获取每日登门统计数据
	 * @param request
	 * @param response 
	 * @return void
	 * @throws
	 */
	@RequestMapping("/ajaxVisitEveryDay")
	public void ajaxVisitEveryDay(HttpServletRequest request, HttpServletResponse response) {
	    // 异步获取数据
        JSONObject results = visitEveryService.ajaxVisitData(request, response);
        // 向前端输出
        JsonUtils.outJsonString(results.toString(), response);
	}
	
	/**
	 * @Title: toVisitEveryDayList 
	 * @Description: 进入指定日期的登门情况列表页
	 * @param request
	 * @param response
	 * @return String
	 * @throws
	 */
	@RequestMapping("/toVisitEveryDayList")
    public String toVisitEveryDayList(HttpServletRequest request, HttpServletResponse response) {
        // 将上一个图表页面选中的日期传递到列表页中显示
        request.setAttribute("area", request.getParameter("area"));
        request.setAttribute("visitDate", request.getParameter("visitDate"));
        return "report/visit_everyday_list";
    }
	
	/**
	 * @Title: ajaxVisitEveryDayList 
	 * @Description: 异步获取指定日期的登门情况数据
	 * @param request
	 * @param response 
	 * @return void
	 * @throws
	 */
	@RequestMapping("/ajaxVisitEveryDayList")
    public void ajaxVisitEveryDayList(HttpServletRequest request, HttpServletResponse response) {
        // 异步获取数据
        JSONObject results = visitEveryService.ajaxVisitEveryDayList(request, response);
        // 向前端输出
        JsonUtils.outJsonString(results.toString(), response);
    }
	
	// ------------------------- 机构业绩统计 入口：toVisitEveryDay ---------------------------------
	
	/**
	 * @Title: toOrganiPerformance 
	 * @Description: 进入机构业绩统计页面
	 * @param request
	 * @param response
	 * @return String
	 * @throws
	 */
	@RequestMapping("/toOrganiPerformance")
    public String toOrganiPerformance(HttpServletRequest request, HttpServletResponse response) {
        // 初始化页面输入框中的日期值（默认上一周的时间）
        request.setAttribute("startReportDate", DateUtil.formatDate(DateUtil.getLastWeekMonday(new Date()), "yyyy-MM-dd"));
        request.setAttribute("endReportDate", DateUtil.formatDate(DateUtil.getLastWeekSunday(new Date()), "yyyy-MM-dd"));
        return "report/organi_performance";
    }
	
	/**
     * @Title: ajaxOrganiPerforList 
     * @Description: 异步获取机构业绩 
     * @param request
     * @param response 
     * @return void
     * @throws
     */
    @RequestMapping("/ajaxOrganiPerforList")
    public void ajaxOrganiPerforList(HttpServletRequest request, HttpServletResponse response) {
        // 异步获取数据
        JSONObject results = deptPerforService.ajaxOrganiPerformance(request);
        // 向前端输出
        JsonUtils.outJsonString(results.toString(), response);
    }
    
    /**
     * @Title: toDeptPerformance 
     * @Description: 进入部门业绩统计页面
     * @param request
     * @param response
     * @return String
     * @throws
     */
    @RequestMapping("/toDeptPerformance")
    public String toDeptPerformance(HttpServletRequest request, HttpServletResponse response) {
        // 初始化页面输入框中的日期值（默认上一周的时间）
        request.setAttribute("startReportDate", request.getParameter("startReportDate"));
        request.setAttribute("endReportDate", request.getParameter("endReportDate"));
        String organiName = request.getParameter("organiName");
        // 根据机构名称查询对应的ID, 存储到Request域
        if(StringUtil.isNotBlank(organiName)) {
            criteria.put("name", organiName);
            List<GroupInfo> list = groupService.selectByExample(criteria);
            if(list != null && list.size() > 0) {
                request.setAttribute("organiId", list.get(0).getId());
            }else {
                // 进入404页面
                throw new SystemException("参数错误");
            }
        }else {
            // 进入404页面
            throw new SystemException("参数错误");
        }
        return "report/dept_performance";
    }
    
    /**
     * @Title: ajaxDeptPerformance 
     * @Description: 异步获取部门业绩数据
     * @param request
     * @param response 
     * @return void
     * @throws
     */
    @RequestMapping("/ajaxDeptPerformance")
    public void ajaxDeptPerformance(HttpServletRequest request, HttpServletResponse response) {
        // 异步获取数据
        JSONObject results = deptPerforService.ajaxPerformanceData(request, response);
        // 向前端输出
        JsonUtils.outJsonString(results.toString(), response);
    }
    
    /**
     * @Title: toIndividualRanking 
     * @Description: 进入个人业绩排名
     * @param request
     * @param response
     * @return String
     * @throws
     */
    @RequestMapping("/toIndividualRanking")
    public String toIndividualRanking(HttpServletRequest request, HttpServletResponse response) {
        // 初始化页面输入框中的日期值（默认上一周的时间）
        request.setAttribute("startReportDate", DateUtil.formatDate(DateUtil.getLastWeekMonday(new Date()), "yyyy-MM-dd"));
        request.setAttribute("endReportDate", DateUtil.formatDate(DateUtil.getLastWeekSunday(new Date()), "yyyy-MM-dd"));
        return "report/individual_ranking";
    }
    
    /**
     * @Title: ajaxIndividualRanking 
     * @Description: 异步获取个人业绩排名数据
     * @param request
     * @param response 
     * @return void
     * @throws
     */
    @RequestMapping("/ajaxIndividualRanking")
    public void ajaxIndividualRanking(HttpServletRequest request, HttpServletResponse response) {
        // 异步获取数据
        JSONObject results = deptPerforService.ajaxIndividualRanking(request, response);
        // 向前端输出
        JsonUtils.outJsonString(results.toString(), response);
    }
    
// ------------------------- 筛选客户类型统计 入口：toFiltrateCustomers ---------------------------------
    
    // 进入筛选客户类型页面
    @RequestMapping("/toFiltrateCustomers")
    public String toFiltrateCustomers(HttpServletRequest request, HttpServletResponse response) {
        // 初始化页面输入框中的日期值（默认上一周的时间）
        request.setAttribute("startDate", DateUtil.formatDate(DateUtil.getLastWeekMonday(new Date()), "yyyy-MM-dd"));
        request.setAttribute("endDate", DateUtil.formatDate(DateUtil.getLastWeekSunday(new Date()), "yyyy-MM-dd"));
        return "report/filtrate_customer";
    }
    
    /**
     * @Title: ajaxFiltrateCustomers
     * @Description: 异步获取筛选客户类型数据
     * @param request
     * @param response
     * @return void
     * @throws
     */
    @RequestMapping("/ajaxFiltrateCustomers")
    public void ajaxFiltrateCustomers(HttpServletRequest request, HttpServletResponse response) {
        // 异步获取数据
        JSONObject results = customerService.ajaxFiltrateCustomers(request);
        // 向前端输出
        JsonUtils.outJsonString(results.toString(), response);
    }
    
    // ------------------------- 市场业务报表2--登门出单统计 入口：toVisitEveryDay ---------------------------------
    
    /**
     * @Title: toVisitTable 
     * @Description: 进入登门出单统计表
     * @param request
     * @param response
     * @return String
     * @throws
     */
    @RequestMapping("/toVisitAndOutOrder")
    public String toVisitAndOutOrder(HttpServletRequest request, HttpServletResponse response) {
        // 初始化页面输入框中的日期值（默认上一周的时间）
        request.setAttribute("startDate", DateUtil.formatDate(DateUtil.getLastWeekMonday(new Date()), "yyyy-MM-dd"));
        request.setAttribute("endDate", DateUtil.formatDate(DateUtil.getLastWeekSunday(new Date()), "yyyy-MM-dd"));
        return "report/visit_out_order";
    }
    
    /**
     * @Title: ajaxVisitAndOutOrder 
     * @Description: 异步获取登门出单统计数据
     * @param request
     * @param response 
     * @return void
     */
    @RequestMapping("/ajaxVisitAndOutOrder")
    public void ajaxVisitAndOutOrder(HttpServletRequest request, HttpServletResponse response) {
        // 异步获取数据
        JSONObject results = receptionService.ajaxVisitAndOutOrder(request);
        // 向前端输出
        JsonUtils.outJsonString(results.toString(), response);
    }
    
    /**
     * @Title: toOutOrderDetail 
     * @Description: 进入订单详细列表页
     * @param request
     * @param response
     * @return String
     * @author Xia ZhengWei
     * @date 2016年10月17日 下午10:52:27 
     * @version V1.0
     */
    @RequestMapping("/toOutOrderDetail")
    public String toOutOrderDetail(HttpServletRequest request, HttpServletResponse response) {
        // 获取url地址中参数
        request.setAttribute("startDate", request.getParameter("startDate"));
        request.setAttribute("endDate", request.getParameter("endDate"));
        request.setAttribute("customerId", request.getParameter("customerId"));
        return "report/out_order_detail";
    }
    
    /**
     * @Title: ajaxOutOrderDetail 
     * @Description: 异步获取订单详细列表
     * @param request
     * @param response 
     * @return void
     * @author Xia ZhengWei
     * @date 2016年10月17日 下午11:39:24 
     * @version V1.0
     */
    @RequestMapping("/ajaxOutOrderDetail")
    public void ajaxOutOrderDetail(HttpServletRequest request, HttpServletResponse response) {
        // 异步获取数据
        JSONObject results = orderDetailService.ajaxOutOrderDetail(request);
        // 向前端输出
        JsonUtils.outJsonString(results.toString(), response);
    }
    
    // TODO - 业务员统计报表-有待与客户确定一些内容
    // ------------------------- 业务员统计 入口：toSalesmanStatement ---------------------------------
    /*# 获取订单表中对应客户的数量
    select count(1) from (
        select c.id from customer_info c left join order_info o on c.id = o.customer_id 
        where c.type = '1'
        and o.create_date > '2016-10-09'
        and o.create_date <= '2016-10-15 23:59:59'
        and o.receiver_staff_id = '1'
        group by c.id
    ) a*/
    @RequestMapping("/toSalesmanStatement")
    public String toSalesmanStatement(HttpServletRequest request, HttpServletResponse response) {
        // 初始化页面输入框中的日期值（默认上一周的时间）
        request.setAttribute("startDate", DateUtil.formatDate(DateUtil.getLastWeekMonday(new Date()), "yyyy-MM-dd"));
        request.setAttribute("endDate", DateUtil.formatDate(DateUtil.getLastWeekSunday(new Date()), "yyyy-MM-dd"));
        return "report/visit_out_order";
    }
    
    // ------------------------- 展厅客服对接业绩详细 入口：toButtPerforDetail ---------------------------------
    
    /**
     * @Title: toButtPerforDetail 
     * @Description: 进入展厅客服对接业绩详细表 
     * @param request
     * @param response
     * @return String
     * @author Xia ZhengWei
     * @date 2016年10月24日 下午10:14:58 
     * @version V1.0
     */
    @RequestMapping("/toButtPerforDetail")
    public String toButtPerforDetail(HttpServletRequest request, HttpServletResponse response) {
        // 初始化页面输入框中的日期值（默认上一周的时间）
        request.setAttribute("startReportDate", DateUtil.formatDate(DateUtil.getLastWeekMonday(new Date()), "yyyy-MM-dd"));
        request.setAttribute("endReportDate", DateUtil.formatDate(DateUtil.getLastWeekSunday(new Date()), "yyyy-MM-dd"));
        return "report/butt_perfor_detail";
    }
    
    /**
     * @Title: ajaxButtPerforDetail 
     * @Description: 异步获取展厅客服对接业绩详细数据
     * @param request
     * @param response 
     * @return void
     * @author Xia ZhengWei
     * @date 2016年10月24日 下午10:17:04 
     * @version V1.0
     */
    @RequestMapping("/ajaxButtPerforDetail")
    public void ajaxButtPerforDetail(HttpServletRequest request, HttpServletResponse response) {
        // 异步获取数据
        JSONObject results = buttPerforService.ajaxButtPerforDetail(request);
        // 向前端输出
        JsonUtils.outJsonString(results.toString(), response);
    }
    
    // ------------------------- 出库明细统计 入口：toOutwarehouseDetail ---------------------------------
    
    /**
     * @Title: toOutwarehouseDetail 
     * @Description: 进入出库明细统计页面
     * @param request
     * @param response
     * @return String
     * @author Xia ZhengWei
     * @date 2016年10月20日 下午10:22:40 
     * @version V1.0
     */
    @RequestMapping("/toOutwarehouseDetail")
    public String toOutwarehouseDetail(HttpServletRequest request, HttpServletResponse response) {
        // 初始化页面输入框中的日期值（默认上一周的时间）
        request.setAttribute("startDate", DateUtil.formatDate(DateUtil.getLastWeekMonday(new Date()), "yyyy-MM-dd"));
        request.setAttribute("endDate", DateUtil.formatDate(DateUtil.getLastWeekSunday(new Date()), "yyyy-MM-dd"));
        return "report/out_warehouse_detail";
    }
    
    /**
     * @Title: ajaxOutwarehouseDetail 
     * @Description: 异步获取出库明细统计数据
     * @param request
     * @param response 
     * @return void
     * @author Xia ZhengWei
     * @date 2016年10月20日 下午10:26:28 
     * @version V1.0
     */
    @RequestMapping("/ajaxOutwarehouseDetail")
    public void ajaxOutwarehouseDetail(HttpServletRequest request, HttpServletResponse response) {
        // 异步获取数据
        JSONObject results = storehouseService.ajaxOutwarehouseDetail(request);
        // 向前端输出
        JsonUtils.outJsonString(results.toString(), response);
    }

}

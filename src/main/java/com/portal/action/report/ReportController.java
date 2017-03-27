package com.portal.action.report;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.portal.bean.Criteria;
import com.portal.bean.GroupInfo;
import com.portal.bean.result.SellDailyInfoForm;
import com.portal.common.exception.DBException;
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
import com.portal.service.OrderInfoService;
import com.portal.service.ReceptionInfoService;
import com.portal.service.SellDailyInfoService;
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
    
    // 订单Service
    @Autowired
    private OrderInfoService orderService;
    
    // 销售日报表Service
    @Autowired
    private SellDailyInfoService sellDailyService;
	
	// 公共查询条件类
	Criteria criteria = new Criteria();
	
	// ------------------------- 接待统计 入口：toReceiveStatistics (与下面的接待统计toSalesmanStatement重复)---------------------------------
	
	/**
	 * @Title: toReceiveStatistics 
	 * @Description: 进入接待统计页面
	 * @param request
	 * @param response
	 * @return String
	 * @throws
	@RequestMapping(value = "/toReceiveStatistics")
	public String toReceiveStatistics(HttpServletRequest request, HttpServletResponse response) {
        // 保存活动导航标识
        WebUtils.setAttributeToSession(request);
        // 初始化页面输入框中的日期值（默认上一周的时间）
        request.setAttribute("startReportDate", DateUtil.formatDate(DateUtil.getLastWeekMonday(new Date()), "yyyy-MM-dd"));
        request.setAttribute("endReportDate", DateUtil.formatDate(DateUtil.getLastWeekSunday(new Date()), "yyyy-MM-dd"));
        
        return "report/receive_statistics";
	}*/
	
	/**
	 * @Title: ajaxStatistics 
	 * @Description: 异步获取接待客户统计数据
	 * @param request
	 * @param response 
	 * @return void
	 * @throws
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
	@RequiresPermissions("visitEveryDay:view")
	@RequestMapping("/toVisitEveryDay")
	public String toVisitEveryDay(HttpServletRequest request, HttpServletResponse response) {
	    // 保存活动导航标识
        WebUtils.setAttributeToSession(request);
	    // 初始化页面输入框中的日期值（默认上一周的时间）
        request.setAttribute("startVisitDate", DateUtil.formatDate(DateUtil.getLastWeekMonday(new Date()), "yyyy-MM-dd"));
        request.setAttribute("endVisitDate", DateUtil.formatDate(DateUtil.getLastWeekSunday(new Date()), "yyyy-MM-dd"));
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
	@RequiresPermissions("organiPerformance:view")
	@RequestMapping("/toOrganiPerformance")
    public String toOrganiPerformance(HttpServletRequest request, HttpServletResponse response) {
	    // 保存活动导航标识
        WebUtils.setAttributeToSession(request);
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
    
    // ------------------------- 进入个人业绩排名 入口：toIndividualRanking ---------------------------------
    
    /**
     * @Title: toIndividualRanking 
     * @Description: 进入个人业绩排名
     * @param request
     * @param response
     * @return String
     * @throws
     */
    @RequiresPermissions("individualRanking:view")
    @RequestMapping("/toIndividualRanking")
    public String toIndividualRanking(HttpServletRequest request, HttpServletResponse response) {
        // 保存活动导航标识
        WebUtils.setAttributeToSession(request);
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
    @RequiresPermissions("filtrateCustomers:view")
    @RequestMapping("/toFiltrateCustomers")
    public String toFiltrateCustomers(HttpServletRequest request, HttpServletResponse response) {
        // 保存活动导航标识
        WebUtils.setAttributeToSession(request);
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
     */
    @RequiresPermissions("visitAndOutOrder:view")
    @RequestMapping("/toVisitAndOutOrder")
    public String toVisitAndOutOrder(HttpServletRequest request, HttpServletResponse response) {
        // 保存活动导航标识
        WebUtils.setAttributeToSession(request);
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
    
    // ------------------------- 接待客户及业绩统计 入口：toSalesmanStatement ---------------------------------
    
    /**
     * @Title: toSalesmanStatement 
     * @Description: 进入接待客户及业绩统计报表页
     * @param request
     * @param response
     * @return String
     * @author Xia ZhengWei
     * @date 2016年10月26日 下午11:53:03 
     * @version V1.0
     */
    @RequiresPermissions("salesmanStatement:view")
    @RequestMapping("/toSalesmanStatement")
    public String toSalesmanStatement(HttpServletRequest request, HttpServletResponse response) {
        // 保存活动导航标识
        WebUtils.setAttributeToSession(request);
        // 初始化页面输入框中的日期值（默认上一周的时间）
        request.setAttribute("startDate", DateUtil.formatDate(DateUtil.getLastWeekMonday(new Date()), "yyyy-MM-dd"));
        request.setAttribute("endDate", DateUtil.formatDate(DateUtil.getLastWeekSunday(new Date()), "yyyy-MM-dd"));
        return "report/salesman_statement";
    }
    
    /**
     * @Title: ajaxSalesmanStatement 
     * @Description: 异步获取接待统计数据
     * @param request
     * @param response 
     * @return void
     * @author Xia ZhengWei
     * @date 2016年10月27日 下午9:22:38 
     * @version V1.0
     */
    @RequestMapping("/ajaxSalesmanStatement")
    public void ajaxSalesmanStatement(HttpServletRequest request, HttpServletResponse response) {
        // 异步获取数据
        JSONObject results = reportService.ajaxSalesmanStatement(request);
        // 向前端输出
        JsonUtils.outJsonString(results.toString(), response);
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
    @RequiresPermissions("buttPerforDetail:view")
    @RequestMapping("/toButtPerforDetail")
    public String toButtPerforDetail(HttpServletRequest request, HttpServletResponse response) {
        // 保存活动导航标识
        WebUtils.setAttributeToSession(request);
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
    @RequiresPermissions("outwarehouseDetail:view")
    @RequestMapping("/toOutwarehouseDetail")
    public String toOutwarehouseDetail(HttpServletRequest request, HttpServletResponse response) {
        // 保存活动导航标识
        WebUtils.setAttributeToSession(request);
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
    
    // ------------------------- 销售日报表 入口：toSellDaily ---------------------------------
    
    /**
     * @Title: toSellDaily 
     * @Description: 进入销售日报表页面
     * @param request
     * @param response
     * @return String
     * @author Xia ZhengWei
     * @date 2016年11月26日 下午2:30:42 
     * @version V1.0
     */
    @RequiresPermissions("sellDaily:view")
    @RequestMapping("/toSellDaily")
    public String toSellDaily(HttpServletRequest request, HttpServletResponse response) {
        // 保存活动导航标识
        WebUtils.setAttributeToSession(request);
        // 进入页面默认当前日期
        request.setAttribute("startDate", DateUtil.formatDate(new Date(), "yyyy-MM-dd"));
        return "report/sell_daily";
    }
    
    /**
     * @Title: ajaxSellDaily 
     * @Description: 异步获取销售日报表数据
     * @param request
     * @param response 
     * @return void
     * @author Xia ZhengWei
     * @date 2016年11月26日 下午4:05:47 
     * @version V1.0
     */
    @RequestMapping("/ajaxSellDaily")
    public void ajaxSellDaily(HttpServletRequest request, HttpServletResponse response) {
        // 异步获取销售日报表数据
        JSONObject results = orderService.getSellDaily(request);
        // 向前端输出
        JsonUtils.outJsonString(results.toString(), response);
    }
    
    /**
     * @Title: saveSellDaily 
     * @Description: 保存销售日报表数据
     * @param sellDaily
     * @param request
     * @param response 
     * @return void
     * @author Xia ZhengWei
     * @date 2016年10月25日 下午5:17:07 
     * @version V1.0
     */
    @RequestMapping("/saveSellDaily")
    public void saveSellDaily(SellDailyInfoForm sellDaily, HttpServletRequest request, HttpServletResponse response) {
        JSONObject results = JsonUtils.setSuccess();
        // 异步获取销售日报表数据
        try {
            results = sellDailyService.saveSellDaily(sellDaily, results, request);
        } catch (DBException e) {
            e.printStackTrace();
            results = JsonUtils.setError();
            results.put("text", "操作失败，请刷新后重试");
        }
        // 向前端输出
        JsonUtils.outJsonString(results.toString(), response);
    }
    
    // ------------------------- 客户统计 入口：toCustomerStatistics ---------------------------------
    
    /**
     * @Title: toCustomerStatistics 
     * @Description: 进入客户统计客户页面
     * @param request
     * @param response
     * @return String
     * @author Xia ZhengWei
     * @date 2016年10月29日 下午7:30:21 
     * @version V1.0
     */
    @RequiresPermissions("customerStatistics:view")
    @RequestMapping(value = "/toCustomerStatistics")
    public String toCustomerStatistics(HttpServletRequest request, HttpServletResponse response) {
        // 保存活动导航标识
        WebUtils.setAttributeToSession(request);
        
        // 初始化页面输入框中的日期值（默认上一周的时间）
        request.setAttribute("startDate", DateUtil.formatDate(DateUtil.getLastWeekMonday(new Date()), "yyyy-MM-dd"));
        request.setAttribute("endDate", DateUtil.formatDate(DateUtil.getLastWeekSunday(new Date()), "yyyy-MM-dd"));
        
        return "report/customer_statistics";
    }
    
    /**
     * @Title: ajaxCustomerStatistics 
     * @Description: 异步获取用户统计数据
     * @param request
     * @param response 
     * @return void
     * @author Xia ZhengWei
     * @date 2016年10月29日 下午7:30:40 
     * @version V1.0
     */
    @RequestMapping("/ajaxCustomerStatistics")
    public void ajaxCustomerStatistics(HttpServletRequest request, HttpServletResponse response) {
        // 异步获取数据
        JSONObject results = customerService.ajaxCustomerStatistics(request);
        // 向前端输出
        JsonUtils.outJsonString(results.toString(), response);
    }
    
    // ------------------------- 每日成交业绩统计 入口：toClinchPerforEveryDay ---------------------------------
    // 统计交订金和完成订单
    
    /**
     * @Title: toClinchPerforEveryDay 
     * @Description: 进入每日成交业绩页面
     * @param request
     * @param response
     * @return String
     * @author Xia ZhengWei
     * @date 2016年10月30日 下午6:28:31 
     * @version V1.0
     */
    @RequiresPermissions("clinchPerforEveryDay:view")
    @RequestMapping("/toClinchPerforEveryDay")
    public String toClinchPerforEveryDay(HttpServletRequest request, HttpServletResponse response) {
        // 保存活动导航标识
        WebUtils.setAttributeToSession(request);
        
        // 初始化页面输入框中的日期值（默认上一周的时间）
        request.setAttribute("startDate", DateUtil.formatDate(DateUtil.getLastWeekMonday(new Date()), "yyyy-MM-dd"));
        request.setAttribute("endDate", DateUtil.formatDate(DateUtil.getLastWeekSunday(new Date()), "yyyy-MM-dd"));
        return "report/clinch_performance";
    }
    
    /**
     * @Title: ajaxClinchPerforEveryDay 
     * @Description: 异步获取每日成交业绩数据
     * @param request
     * @param response 
     * @return void
     * @author Xia ZhengWei
     * @date 2016年10月30日 下午6:52:12 
     * @version V1.0
     */
    @RequestMapping("/ajaxClinchPerforEveryDay")
    public void ajaxClinchPerforEveryDay(HttpServletRequest request, HttpServletResponse response) {
        // 异步获取数据
        JSONObject results = orderService.ajaxClinchPerforEveryDay(request);
        // 向前端输出
        JsonUtils.outJsonString(results.toString(), response);
    }
    
    // ------------------------- 客服业绩统计 入口：toServiceStaffPerfor ---------------------------------
    
    /**
     * @Title: toServiceStaffPerfor 
     * @Description: 进入客服业绩统计页面
     * @param request
     * @param response
     * @return String
     * @author Xia ZhengWei
     * @date 2016年10月31日 下午11:01:00 
     * @version V1.0
     */
    @RequiresPermissions("serviceStaffPerfor:view")
    @RequestMapping("/toServiceStaffPerfor")
    public String toServiceStaffPerfor(HttpServletRequest request, HttpServletResponse response) {
        // 保存活动导航标识
        WebUtils.setAttributeToSession(request);
        
        // 初始化页面输入框中的日期值（默认上一周的时间）
        request.setAttribute("startDate", DateUtil.formatDate(DateUtil.getLastWeekMonday(new Date()), "yyyy-MM-dd"));
        request.setAttribute("endDate", DateUtil.formatDate(DateUtil.getLastWeekSunday(new Date()), "yyyy-MM-dd"));
        return "report/service_staff_perfors";
    }
    
    // ------------------------- 接待业绩统计 入口：toReceiveStaffPerfor ---------------------------------
    
    /**
     * @Title: toReceiveStaffPerfor 
     * @Description: 进入接待业绩统计页面
     * @param request
     * @param response
     * @return String
     * @author Xia ZhengWei
     * @date 2016年10月31日 下午11:03:26 
     * @version V1.0
     */
    @RequiresPermissions("receiveStaffPerfor:view")
    @RequestMapping("/toReceiveStaffPerfor")
    public String toReceiveStaffPerfor(HttpServletRequest request, HttpServletResponse response) {
        // 保存活动导航标识
        WebUtils.setAttributeToSession(request);
        
        // 初始化页面输入框中的日期值（默认上一周的时间）
        request.setAttribute("startDate", DateUtil.formatDate(DateUtil.getLastWeekMonday(new Date()), "yyyy-MM-dd"));
        request.setAttribute("endDate", DateUtil.formatDate(DateUtil.getLastWeekSunday(new Date()), "yyyy-MM-dd"));
        return "report/receive_staff_perfors";
    }
    
    /**
     * @Title: ajaxStaffPerfors 
     * @Description: 异步获取员工业绩统计数据
     * @param request
     * @param response 
     * @return void
     * @author Xia ZhengWei
     * @date 2016年10月31日 下午11:00:43 
     * @version V1.0
     */
    @RequestMapping("/ajaxStaffPerfors")
    public void ajaxStaffPerfors(HttpServletRequest request, HttpServletResponse response) {
        // 异步获取数据
        JSONObject results = orderService.ajaxStaffPerfors(request);
        // 向前端输出
        JsonUtils.outJsonString(results.toString(), response);
    }
    
    // ------------------------- 当日定金明细统计 入口：toCreditCardDepositDetail ---------------------------------
    
    /**
     * @Title: toCreditCardDepositDetail 
     * @Description: 当日定金明细统计
     * @param request
     * @param response
     * @return String
     * @author Xia ZhengWei
     * @date 2016年12月11日 下午8:39:02 
     * @version V1.0
     */
    @RequiresPermissions("creditCardDepositDetail:view")
    @RequestMapping("/toCreditCardDepositDetail")
    public String toCreditCardDepositDetail(HttpServletRequest request, HttpServletResponse response) {
        // 保存活动导航标识
        WebUtils.setAttributeToSession(request);
        // 初始化页面输入框中的日期值（默认当天时间）
        request.setAttribute("startDate", DateUtil.formatDate(new Date(), "yyyy-MM-dd"));
        return "report/credit_card_deposit_detail";
    }
    
    /**
     * @Title: ajaxCreditCardDepositDetail 
     * @Description: 异步获取当日定金明细数据
     * @param request
     * @param response 
     * @return void
     * @author Xia ZhengWei
     * @date 2016年12月11日 下午8:38:53 
     * @version V1.0
     */
    @RequestMapping("/ajaxCreditCardDepositDetail")
    public void ajaxCreditCardDepositDetail(HttpServletRequest request, HttpServletResponse response) {
        JSONObject results = new JSONObject();
        // 异步获取数据
        results = orderService.ajaxCreditCardDepositDetail(request, results);
        // 向前端输出
        JsonUtils.outJsonString(results.toString(), response);
    }
    
    // ------------------------- 赠品明细统计 入口：toGiftDetail ---------------------------------
    
    /**
     * @Title: toGiftDetail 
     * @Description: 进入赠品明细统计页面
     * @param request
     * @param response
     * @return String
     * @author Xia ZhengWei
     * @date 2016年12月12日 下午10:07:32 
     * @version V1.0
     */
    @RequiresPermissions("giftDetail:view")
    @RequestMapping("/toGiftDetail")
    public String toGiftDetail(HttpServletRequest request, HttpServletResponse response) {
        // 保存活动导航标识
        WebUtils.setAttributeToSession(request);
        // 初始化页面输入框中的日期值（默认当天时间）
        request.setAttribute("startDate", DateUtil.formatDate(new Date(), "yyyy-MM-dd"));
        request.setAttribute("endDate", DateUtil.formatDate(new Date(), "yyyy-MM-dd"));
        return "report/gift_detail";
    }
    
    /**
     * @Title: ajaxGiftDetail 
     * @Description: 异步获取赠品明细数据
     * @param request
     * @param response 
     * @return void
     * @author Xia ZhengWei
     * @date 2016年12月12日 下午10:10:43 
     * @version V1.0
     */
    @RequestMapping("/ajaxGiftDetail")
    public void ajaxGiftDetail(HttpServletRequest request, HttpServletResponse response) {
        JSONObject results = new JSONObject();
        // 异步获取数据
        results = orderDetailService.ajaxGiftDetail(request, results);
        // 向前端输出
        JsonUtils.outJsonString(results.toString(), response);
    }

}

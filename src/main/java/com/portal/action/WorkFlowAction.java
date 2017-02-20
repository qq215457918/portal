package com.portal.action;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.portal.bean.Criteria;
import com.portal.bean.DailyEmployeeAudit;
import com.portal.bean.DailyEmployeeAuditHistory;
import com.portal.bean.EmployeeInfo;
import com.portal.bean.OrderDetailInfo;
import com.portal.bean.OrderFundSettlement;
import com.portal.bean.OrderInfo;
import com.portal.common.util.JsonUtils;
import com.portal.service.DailyEmployeeAuditHistoryService;
import com.portal.service.DailyEmployeeAuditService;
import com.portal.service.EmployeeInfoService;
import com.portal.service.OrderDetailInfoService;
import com.portal.service.OrderFundSettlementService;
import com.portal.service.OrderInfoService;
import com.portal.service.WorkFlowService;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/workflow")
public class WorkFlowAction {
	
	@Autowired
	private WorkFlowService workFlowService;
	
	@Autowired
	private OrderInfoService orderInfoService;
	
	@Autowired
	private EmployeeInfoService employeeInfoService;
	
	@Autowired
	private OrderDetailInfoService orderDetailInfoService;
	
	@Autowired
	private OrderFundSettlementService orderFundSettlementService;
	
	@Autowired
	private DailyEmployeeAuditService dailyEmployeeAuditService;
	
	@Autowired
	private DailyEmployeeAuditHistoryService dailyEmployeeAuditHistoryService;
	
	/**
	 * @Title: flowInfoIndex 
	 * @Description: 流程部署页面
	 * @return 
	 * @return String
	 * @throws
	 */
	@RequestMapping("/flowInfoIndex")
	public String flowInfoIndex(){
		return "flow/flow_info_index";
	}
	
	/**
	 * @throws ParseException 
	 * @Title: flowInfoIndex 
	 * @Description: 审批列表
	 * @return 
	 * @return String
	 * @throws
	 */
	@RequestMapping("/achieveExamList")
	public String achieveExamList(HttpServletRequest request, HttpServletResponse response) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dateInfo = null==request.getParameter("dateInfo")?sdf.format(new Date()):request.getParameter("dateInfo");
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("dateInfo", dateInfo);
		Map<String, Object> result = workFlowService.selectlerkEverydayAchievenment(paramMap);
		
		Criteria criteria = new Criteria();
		criteria.put("positionType", 2); //只查业务员
		int employeeCount = employeeInfoService.countByExample(criteria);
		criteria.put("createDate", sdf.parse(dateInfo));
		int hadCommit = dailyEmployeeAuditService.countByExample(criteria);
		
//		String userId = (String) request.getSession().getAttribute("userId");
//		String defKey = request.getParameter("defKey");
//		int hadCommit = workFlowService.selectTaskCountById(userId, defKey);
		result.put("commitCount", hadCommit + "/" + employeeCount);
		
		request.setAttribute("id", request.getParameter("id"));
		request.setAttribute("dateInfo", dateInfo);
		request.setAttribute("result", result);
		
		return "flow/achieve_exam_list";
	}
	
	/**
	 * @Title: insertFlowZip 
	 * @Description: 通过zip文件部署流程
	 * @param request
	 * @param response
	 * @param flowFile
	 * @return 
	 * @return String
	 * @throws
	 */
	@RequestMapping("/insertFlowZip")
	public String insertFlowZip(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="flowFile", required=false) MultipartFile flowFile,
			@RequestParam(value="fileName", required=false) String fileName) {
		
		workFlowService.insertFlowZip(flowFile, fileName);
		
		return "redirect:flowInfoIndex";
	}
	
	/**
	 * @Title: selectFlowDepInfo 
	 * @Description: 查询流程部署列表
	 * @param request
	 * @param response
	 * @return 
	 * @return String
	 * @throws
	 */
	@RequestMapping("/selectFlowDepInfo")
	public void selectFlowDepInfo(HttpServletRequest request, HttpServletResponse response){
		// 获取流程部署列表（act_re_deployment表）
		List<Deployment> depList = workFlowService.selectDeployList();
		// 直接转报错 ，转换为 拼接json对象
		JSONArray result = new JSONArray();
		for(Deployment deployment : depList){
			JSONObject temp = new JSONObject();
			temp.put("depId", deployment.getId());
			temp.put("name", deployment.getName());
			temp.put("deploymentTime", deployment.getDeploymentTime());
			result.add(temp);
		}
		
		try {
			JSONObject resultJson =  new JSONObject();
			resultJson.put("sEcho", request.getParameter("sEcho"));
			resultJson.put("iTotalRecords", 0);
			resultJson.put("iTotalDisplayRecords", 0);
			resultJson.put("aaData", result.toString());
			response.setContentType("text/json;charset=UTF-8");
			response.getWriter().print(JSONObject.fromObject(resultJson).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @Title: selectFlowPdInfo 
	 * @Description: 查询流程定义列表
	 * @param request
	 * @param response
	 * @return 
	 * @return String
	 * @throws
	 */
	@RequestMapping("/selectFlowPdInfo")
	public void selectFlowPdInfo(HttpServletRequest request, HttpServletResponse response){
		// 获取流程定义列表（act_re_procdef表）
		List<ProcessDefinition> pdList = workFlowService.selectProcessDefinitionList();
		// 直接转报错 ，转换为 拼接json对象
		JSONArray result = new JSONArray();
		for(ProcessDefinition pd : pdList){
			JSONObject temp = new JSONObject();
			temp.put("pdId", pd.getId());
			temp.put("name", pd.getName());
			temp.put("key", pd.getKey());
			temp.put("version", pd.getVersion());
			temp.put("resourceName", pd.getResourceName());
			temp.put("diagramResourceName", pd.getDiagramResourceName());
			temp.put("deploymentId", pd.getDeploymentId());
			result.add(temp);
		}
		try {
			JSONObject resultJson =  new JSONObject();
			resultJson.put("sEcho", request.getParameter("sEcho"));
			resultJson.put("iTotalRecords", 0);
			resultJson.put("iTotalDisplayRecords", 0);
			resultJson.put("aaData", result.toString());
			response.setContentType("text/json;charset=UTF-8");
			response.getWriter().print(JSONObject.fromObject(resultJson).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @throws IOException 
	 * @Title: selectFlowImage 
	 * @Description: 查看流程图
	 * @param request
	 * @param response
	 * @return 
	 * @return String
	 * @throws
	 */
	@RequestMapping("/selectFlowImage")
	public String selectFlowImage(HttpServletRequest request, HttpServletResponse response) throws IOException{
		// 获取流程id
		String deploymentId = request.getParameter("deploymentId");
		// 获取图片名
		String imageName = request.getParameter("imageName");
		
		InputStream in = workFlowService.selectFlowImage(deploymentId, imageName);
		
		OutputStream out = response.getOutputStream();
		
		for(int b=-1; (b=in.read())!=-1;){
			out.write(b);
		}
		out.close();
		in.close();
		
		return null;
	}
	
	/**
	 * @Title: delDeployment 
	 * @Description: 删除部署信息
	 * @param request
	 * @param response
	 * @return String
	 * @throws
	 */
	@RequestMapping("delDeployment")
	public void delDeployment(HttpServletRequest request, HttpServletResponse response){
		// 获取流程id
		String deploymentId = request.getParameter("deploymentId");
		// 使用部署对象id，删除流程定义
		workFlowService.delProcessDefinitionById(deploymentId);
	}
	
	/**
	 * @Title: selectTaskListById 
	 * @Description: 通过用户id 获取正在执行的任务列表
	 * @param request
	 * @param response
	 * @return String
	 * @throws
	 */
	@RequestMapping("/selectTaskListById")
	public void selectTaskListById(HttpServletRequest request, HttpServletResponse response){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//获取当前用户id
		String userId = (String) request.getSession().getAttribute("userId");
		
//		String defKey = request.getParameter("defKey");
//		List<Task> list = workFlowService.selectTaskListById(userId, defKey);
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		
		String dateInfo = null==request.getParameter("dateInfo")?sdf.format(new Date()):request.getParameter("dateInfo");
		paramMap.put("dateInfo", null==request.getParameter("dateInfo")?sdf.format(new Date()):request.getParameter("dateInfo"));
		
//		paramMap.put("userList", list);
		paramMap.put("dateInfo", dateInfo);
		paramMap.put("auditor", userId);
		List<Map<String, Object>> result = workFlowService.selectClerkDayList(paramMap);
		
		try {
			JSONObject resultJson =  new JSONObject();
			resultJson.put("sEcho", request.getParameter("sEcho"));
			resultJson.put("iTotalRecords", 0);
			resultJson.put("iTotalDisplayRecords", 0);
			resultJson.put("aaData", JSONArray.fromObject(result).toString());
			response.setContentType("text/json;charset=UTF-8");
			response.getWriter().print(JSONObject.fromObject(resultJson).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @Title: clerkEverydayAchievenment 
	 * @Description: 员工每日业绩查看
	 * @param request
	 * @param response
	 * @return String
	 * @throws
	 */
	@RequestMapping("clerkEverydayAchievenment")
	public String clerkEverydayAchievenment(HttpServletRequest request, HttpServletResponse response){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		String employeeId = request.getParameter("employeeId");
		
		String userId = null==employeeId?(String)request.getSession().getAttribute("userId"):employeeId;
		String dateInfo = null==request.getParameter("dateInfo")?sdf.format(new Date()):request.getParameter("dateInfo");
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("dateInfo", dateInfo);
		paramMap.put("userId", userId);
		Map<String, Object> result = workFlowService.selectlerkEverydayAchievenment(paramMap);
		
		Criteria criteria = new Criteria();
		criteria.put("createDate", dateInfo);
		criteria.put("employeeId", userId);
		request.setAttribute("hasCommitDaily", dailyEmployeeAuditService.countByExample(criteria));		
		request.setAttribute("id", request.getParameter("id"));
		request.setAttribute("receiverStaffId", userId);
		request.setAttribute("dateInfo", dateInfo);
		request.setAttribute("result", result);
		request.setAttribute("employeeId", employeeId);
		
		return "flow/clerk_everyday_achievement";
	}
	
	/**
	 * @throws ParseException 
	 * @Title: toAchieveExam 
	 * @Description: 开始审批流程
	 * @param request
	 * @param response
	 * @throws
	 */
	@RequestMapping("toAchieveExam")
	public void toAchieveExam(HttpServletRequest request, HttpServletResponse response) throws ParseException{
		String userId = (String)request.getSession().getAttribute("userId");
		String dateInfo = request.getParameter("dateInfo");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		String auditorId = workFlowService.getAuditorId(userId);
		
		DailyEmployeeAudit dailyEmployeeAudit = new DailyEmployeeAudit();
		dailyEmployeeAudit.setCreateDate(null==dateInfo?new Date():sdf.parse(dateInfo));
		dailyEmployeeAudit.setEmployeeId(userId);
		dailyEmployeeAudit.setAuditorId(auditorId);
		dailyEmployeeAuditService.insertSelective(dailyEmployeeAudit);
		// 暂时不使用activity 20170104
//		Map<String, Object> paramMap = new HashMap<String, Object>();
//		paramMap.put("userId", userId);
//		paramMap.put("dateInfo", sdf.format(new Date()));
//		paramMap.put("orderInfo", request.getParameter("orderInfo"));
//		ProcessInstance pi = workFlowService.startProcess("leaderApprove", sdf.format(new Date())+userId, paramMap);
//		request.setAttribute("piId", pi.getProcessInstanceId());
//		achieveExam(request, response);
	}
	
	/**
	 * @Title: achieveExam 
	 * @Description: 审批流程
	 * @param request
	 * @param response
	 * @throws
	 */
	@RequestMapping("achieveExam")
	public void achieveExam(HttpServletRequest request, HttpServletResponse response){
//		String userId = ((EmployeeInfo)request.getSession().getAttribute("user")).getId();
		String userId = "1";
		String processInstanceId = request.getParameter("processInstanceId");
		if(StringUtils.isBlank(processInstanceId)){
			processInstanceId = (String) request.getAttribute("piId");
		}
		
		Task task = workFlowService.findTaskByProcInstId(processInstanceId);
		
		Map<String, String> pm = new HashMap<String, String>();
		pm.put("taskId", task.getId());
		pm.put("outcome", "提交");
		pm.put("comment", "提交审核");
		pm.put("userId", userId);
				
		workFlowService.saveSubmitTask(pm);
	}
	
	/**
	 * @Title: selectHistoryList 
	 * @Description: 获取审批历史
	 * @param request
	 * @param response 
	 * @return void
	 * @throws
	 */
	@RequestMapping("selectHistoryList")
	public void selectHistoryList(HttpServletRequest request, HttpServletResponse response){
		String taskId = request.getParameter("taskId");
		
		List<Comment> resultList = workFlowService.findCommentByTaskId(taskId);
		
		try {
			response.getWriter().print(JSONArray.fromObject(resultList).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @Title: commitExam 
	 * @Description: 审核
	 * @param request
	 * @param response 
	 * @return void
	 * @throws
	 */
	@RequestMapping("commitExam")
	public String commitExam(HttpServletRequest request, HttpServletResponse response){
		String employeeIds = request.getParameter("employeeIds");
		String suggestion = request.getParameter("suggestion");
		String examDate = request.getParameter("examDate");
		String examMessage = request.getParameter("examMessage");
		String userId = (String)request.getSession().getAttribute("userId");
		if(StringUtils.isNotBlank(employeeIds)){
			String[] employeeIdList = employeeIds.split(";");
			for(int i = 0; i < employeeIdList.length; i++){
				Criteria criteria = new Criteria();
				criteria.put("createDate", examDate);
				criteria.put("employeeId", employeeIdList[i]);
				criteria.put("deleteFlag", 0);
				// 通过日期和员工id 获取审核表的id
				List<DailyEmployeeAudit> auditList = dailyEmployeeAuditService.selectByExample(criteria);
				
				DailyEmployeeAudit dailyEmployeeAudit = new DailyEmployeeAudit();
				dailyEmployeeAudit.setId(auditList.get(0).getId());
				dailyEmployeeAudit.setStatus(suggestion);
				String auditorId = workFlowService.getAuditorId(userId);
				dailyEmployeeAudit.setAuditorId(auditorId);
				if(StringUtils.isBlank(auditorId)){
					dailyEmployeeAudit.setStatus("2");
				}
				// 更新审核表 如果查询下一个审核人为空则审核结束
				dailyEmployeeAuditService.updateByPrimaryKeySelective(dailyEmployeeAudit);
				
				DailyEmployeeAuditHistory dailyEmployeeAuditHistory = new DailyEmployeeAuditHistory();
				dailyEmployeeAuditHistory.setAuditId(auditList.get(0).getId());
				dailyEmployeeAuditHistory.setAuditorId(userId);
				dailyEmployeeAuditHistory.setAuditDate(new Date());
				dailyEmployeeAuditHistory.setMessage(examMessage);
				// 插入审核历史信息 
				dailyEmployeeAuditHistoryService.insertSelective(dailyEmployeeAuditHistory);
				
			}
		}
		
//		EmployeeInfo ei = (EmployeeInfo)request.getSession().getAttribute("user");
//		Map<String, String> paramMap = new HashMap<String, String>();
//		//获取任务ID
//		paramMap.put("taskId", taskId);
//		//获取连线的名称
//		paramMap.put("outcome", suggestion);
//		//批注信息
//		paramMap.put("comment", examMessage);
//		//订单编号
//		paramMap.put("id", id);
//		//用户id
//		paramMap.put("userId", ei.getId() + "," + ei.getName());
//		workFlowService.saveSubmitTask(paramMap);
		
		return "redirect:achieveExamList";
	}
	
	/**
	 * @Title: financeOrderList 
	 * @Description: 财务订单列表首页
	 * @return 
	 * @return String
	 * @throws
	 */
	@RequestMapping("/financeOrderList")
	public String financeOrderList(){
		return "flow/finance_order_list";
	}
	
	/**
	 * @Title: financeOrderList 
	 * @Description: 财务订单列表首页
	 * @return 
	 * @return String
	 * @throws
	 */
	@RequestMapping("/financeOrderEveryday")
	public String financeOrderEveryday(HttpServletRequest request){
		request.setAttribute("userId", request.getParameter("userId"));
		return "flow/finance_order_everyday";
	}
	
	/**
	 * @Title: storeOrderinfo 
	 * @Description: 仓库管理信息
	 * @return 
	 * @return String
	 * @throws
	 */
	@RequestMapping("/storeOrderinfo")
	public String storeOrderinfo(HttpServletRequest request){
		return "flow/store_order_info";
	}
	
	/**
	 * @Title: selectFinanceOrder 
	 * @Description: 财务订单列表
	 * @param request
	 * @param response 
	 * @return void
	 * @throws
	 */
	@RequestMapping("selectFinanceOrder")
	public void selectFinanceOrder(HttpServletRequest request, HttpServletResponse response){
		Criteria criteria = new Criteria();
		EmployeeInfo empInfo = (EmployeeInfo) request.getSession().getAttribute("userInfo");
		
		criteria.put("id", request.getParameter("orderId"));
		criteria.put("orderNumber", request.getParameter("orderNumber"));
		criteria.put("financeOrder", "1");
		criteria.put("createDate", request.getParameter("createDate"));
		criteria.put("deleteFlag", 0);
		criteria.put("areaFlag", empInfo.getOrganizationId());
		criteria.setMysqlLength(Integer.valueOf(request.getParameter("iDisplayLength")));
		criteria.setMysqlOffset(Integer.valueOf(request.getParameter("iDisplayStart")));
		
		List<OrderInfo> resultList = orderInfoService.selectByExample(criteria);
		
		int count = orderInfoService.countByExample(criteria);
		
		JsonUtils.resultJson(resultList, count, response, request);
	}
	
	@RequestMapping("selectFinanceEveryDay")
	public void selectFinanceEveryDay(HttpServletRequest request, HttpServletResponse response){
		String userId = request.getParameter("userId");
		
		Criteria criteria = new Criteria();
		criteria.put("financeDate", request.getParameter("financeDate"));
		criteria.put("userId", userId);
		if(StringUtils.isNotBlank(userId)){
			criteria.put("financeFlag", "-1");
		}
		
		List<OrderInfo> resultList = orderInfoService.selectFinanceEveryDay(criteria);
		
		int count = orderInfoService.countFinanceEveryDay(criteria);
		
		JsonUtils.resultJson(resultList, count, response, request);
	}
	
	/**
	 * @Title: selectOrderInfoById 
	 * @Description: 通过id获取订单信息
	 * @param request
	 * @param response 
	 * @return void
	 * @throws
	 */
	@RequestMapping("selectOrderInfoById")
	public void selectOrderInfoById(HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("orderId", request.getParameter("orderId"));
		List<OrderDetailInfo> resultList = orderDetailInfoService.selectOrderInfoById(param);
		
		try {
			response.getWriter().print(JSONArray.fromObject(resultList).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @Title: updateOrderInfo 
	 * @Description: 更新订单信息
	 * @param request
	 * @param response 
	 * @return void
	 * @throws
	 */
	@RequestMapping("updateOrderAndInsert")
	public String updateOrderAndInsert(HttpServletRequest request, HttpServletResponse response, OrderInfo orderInfo){
		String userId = (String)request.getSession().getAttribute("userId");
		
		orderInfo.setFinanceOperatorId(userId);
		orderInfo.setFinanceDate(new Date());
		orderInfo.setFinanceFlag("1");
		if("1".equals(request.getParameter("operate"))){//进行付款
			orderInfo.setFinanceFlag("-1");
			orderInfo.setStatus("4");
		}
		orderInfo.setId(request.getParameter("orderId"));
		
		//如果订单没有配售配送商品将订单的文交所审批直接置为1
		Criteria criteria = new Criteria();
		criteria.put("countCulture", 1);
		criteria.put("orderId", request.getParameter("orderId"));
		int count = orderDetailInfoService.countByExample(criteria);
		if(0 == count){
			orderInfo.setCultureFlag("1");
		}
		
		orderInfoService.updateByPrimaryKeySelective(orderInfo);
		
		for(OrderFundSettlement ofs : orderInfo.getPaymentList()){
			ofs.setOrderNumber(orderInfo.getOrderNumber());
			ofs.setId("1");
			orderFundSettlementService.insertSelective(ofs);
		}
		
		return "redirect:financeOrderList";
	}
	
	/**
	 * @Title: updateOrderInfo 
	 * @Description: 更新订单信息
	 * @param request
	 * @param response 
	 * @return void
	 * @throws
	 */
	@RequestMapping("updateOrderInfo")
	public void updateOrderInfo(HttpServletRequest request, HttpServletResponse response, OrderInfo orderInfo){
		String userId = (String)request.getSession().getAttribute("userId");
		
		orderInfo.setFinanceOperatorId(userId);
		orderInfo.setFinanceDate(new Date());
		orderInfo.setFinanceFlag("1");
		if("1".equals(request.getParameter("operate"))){//进行付款
			orderInfo.setFinanceFlag("-1");
		}
		orderInfo.setId(request.getParameter("orderId"));
		
		orderInfoService.updateByPrimaryKeySelective(orderInfo);
		
		try {
			response.getWriter().print("success");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @Title: clerkReceiveInfo 
	 * @Description: 业务员每日接待信息
	 * @param request
	 * @param response 
	 * @return void
	 * @throws
	 */
	@RequestMapping("clerkReceiveInfo")
	public void clerkReceiveInfo(HttpServletRequest request, HttpServletResponse response){
		String receiverStaffId = request.getParameter("receiverStaffId");
		String createDate = request.getParameter("createDate");
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("mysqlLength", Integer.valueOf(request.getParameter("iDisplayLength")));
		paramMap.put("mysqlOffset", Integer.valueOf(request.getParameter("iDisplayStart")));
		paramMap.put("createDate", createDate);
		paramMap.put("receiverStaffId", receiverStaffId);
		
		List<Map<String, Object>> resultList = workFlowService.selectClerkReceiveList(paramMap);
		
		int count = workFlowService.selectClerkReceiveCount(paramMap);
		
		JsonUtils.resultJson(resultList, count, response, request);
	}
	
	/**
	 * @Title: civilizationExchangeIndex 
	 * @Description: 所交所页面初始化
	 * @param request
	 * @param response
	 * @return 
	 * @return String
	 * @throws
	 */
	@RequestMapping("civilizationExchangeIndex")
	public String civilizationExchangeIndex(HttpServletRequest request, HttpServletResponse response){
		return "flow/civilization_exchange_list";
	}
	
	/**
	 * @Title: selectCivilizationOrder 
	 * @Description: 文交所列表
	 * @param request
	 * @param response 
	 * @return void
	 * @throws
	 */
	@RequestMapping("selectCivilizationOrder")
	public void selectCivilizationOrder(HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		
		paramMap.put("orderNumber", request.getParameter("orderNumber"));
		paramMap.put("mysqlLength", Integer.valueOf(request.getParameter("iDisplayLength")));
		paramMap.put("mysqlOffset", Integer.valueOf(request.getParameter("iDisplayStart")));
		
		List<Map<String, Object>> resultList = workFlowService.selectCivilizationOrderList(paramMap);
		
		int count = workFlowService.selectCivilizationOrderCount(paramMap);
		
		JsonUtils.resultJson(resultList, count, response, request);
	}
	
	/**
	 * @Title: selectOrderDetailById 
	 * @Description: 文交所通过orderId获取订单详情
	 * @param request
	 * @param response 
	 * @return void
	 * @throws
	 */
	@RequestMapping("selectOrderDetailById")
	public void selectOrderDetailById(HttpServletRequest request, HttpServletResponse response){
		Criteria criteria = new Criteria();
		
		criteria.put("orderId", request.getParameter("orderId"));
		criteria.put("deleteFlag", 0);
		
		List<OrderDetailInfo> resultList = orderDetailInfoService.selectByExample(criteria);
		
		OrderInfo orderInfo = orderInfoService.selectByPrimaryKey(request.getParameter("orderId"));
		
		if(null != resultList && resultList.size() > 0) {
			resultList.get(0).setRemark(orderInfo.getCultureRemark());
		}
		
		try {
			response.getWriter().print(JSONArray.fromObject(resultList).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @Title: updateCivilizationInfo 
	 * @Description: 更新审核状态
	 * @param request
	 * @param response
	 * @return 
	 * @return String
	 * @throws
	 */
	@RequestMapping("updateCivilizationInfo")
	public String updateCivilizationInfo(HttpServletRequest request, HttpServletResponse response){
		String orderId = request.getParameter("orderId");
		// 1 已审核 -1审核不通过
		String cultureFlag = request.getParameter("cultureFlag");
		String cultureRemark = request.getParameter("cultureRemark");
		String userId = (String) request.getSession().getAttribute("userId");
		
		OrderInfo orderInfo = new OrderInfo();
		orderInfo.setId(orderId);
		orderInfo.setCultureFlag(cultureFlag);
		orderInfo.setCultureRemark(cultureRemark);
		orderInfo.setCultureOperatorId(userId);
		orderInfo.setCultureDate(new Date());
		orderInfoService.updateByPrimaryKeySelective(orderInfo);
		
		return "redirect:civilizationExchangeIndex";
	}
	
	/**
	 * @Title: getAccountAndPayTypeInfo 
	 * @Description: 获取支付方式列表和账户列表
	 * @param request
	 * @param response 
	 * @return void
	 * @throws
	 */
	@RequestMapping("/getAccountAndPayTypeInfo")
	public void getAccountAndPayTypeInfo(HttpServletRequest request, HttpServletResponse response){
		EmployeeInfo empInfo = (EmployeeInfo) request.getSession().getAttribute("userInfo");
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("org", empInfo.getOrganizationId());
		paramMap.put("orderId", request.getParameter("orderId"));
		Map<String, Object> result = workFlowService.getAccountAndPayTypeInfo(paramMap);
		
		try {
			response.setContentType("text/json;charset=UTF-8");
			response.getWriter().print(JSONObject.fromObject(result).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @Title: updateCivilizationInfo 
	 * @Description: 更新审核状态
	 * @param request
	 * @param response
	 * @return 
	 * @return String
	 * @throws
	 */
	@RequestMapping("examHistoryInfo")
	public void examHistoryInfo(HttpServletRequest request, HttpServletResponse response){
		String userId = (String) request.getSession().getAttribute("userId");
		String dateInfo = request.getParameter("dateInfo");
		
		Criteria criteria = new Criteria();
		criteria.put("employeeId", userId);
		criteria.put("createDate", dateInfo);
		criteria.put("deleteFlag", 0);
		List<DailyEmployeeAudit> tempList = dailyEmployeeAuditService.selectByExample(criteria);
		
		if(null != tempList && tempList.size() > 0) {
			criteria.clear();
			criteria.put("auditId", tempList.get(0).getId());
			List<DailyEmployeeAuditHistory> resultList = dailyEmployeeAuditHistoryService.selectByExample(criteria);
			int count = dailyEmployeeAuditHistoryService.countByExample(criteria);
			JsonUtils.resultJson(resultList, count, response, request);
		}else {
			List<DailyEmployeeAuditHistory> resultList = new ArrayList<DailyEmployeeAuditHistory>();
			JsonUtils.resultJson(resultList, 0, response, request);
		}
	}
	
	/**
	 * @Title: updateCivilizationInfo 
	 * @Description: 下载打印模版
	 * @param request
	 * @param response
	 * @return 
	 * @return String
	 * @throws
	 */
	@RequestMapping("downloadExcel")
	public void downloadExcel(HttpServletRequest request, HttpServletResponse response){
		String orderId = request.getParameter("orderId");
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		
		OrderInfo orderInfo = orderInfoService.selectPirntInfoById(orderId);
		
		List<Map<String, String>> result = orderFundSettlementService.getOrderFundInfo(orderId);
		
		try {
			@SuppressWarnings("deprecation")
			String path = request.getRealPath("/resources/excel/print_template.xls");
			OutputStream os = response.getOutputStream();// 取得输出流
			response.reset();// 清空输出流
			
			// 设定输出文件头
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition",
					"attachment; filename=" + orderId + ".xls");

			File file = new File(path);
			
			Workbook wb = Workbook.getWorkbook(file);
			WorkbookSettings settings = new WorkbookSettings();  
			settings.setEncoding("GB18030"); //关键代码，解决中文乱码 
			WritableWorkbook workbook = Workbook.createWorkbook(os, wb, settings);

			WritableSheet sheet = workbook.getSheet(0);

			sheet.addCell(new Label(0, 0, "          " + orderInfo.getCustomerName()==null?"":orderInfo.getCustomerName()));
			sheet.addCell(new Label(5, 0, "          " + sdf.format(orderInfo.getCreateDate())));
			sheet.addCell(new Label(0, 6, "          " + orderInfo.getReceiverStaffName()==null?"":orderInfo.getReceiverStaffName()));
			sheet.addCell(new Label(5, 6, "          " + orderInfo.getPhoneStaffName()==null?"":orderInfo.getPhoneStaffName()));
			sheet.addCell(new Label(0, 5, "              " + orderInfo.getRemarks()==null?"":orderInfo.getRemarks()));
			
			for(int i = 2, j = 0; j < result.size(); i++, j++){
				sheet.addCell(new Label(0, i, result.get(j).get("good_name")));
				sheet.addCell(new Label(1, i, String.valueOf(result.get(j).get("amount"))));
				sheet.addCell(new Label(2, i, String.valueOf(result.get(j).get("price"))));
				sheet.addCell(new Label(3, i, String.valueOf(result.get(j).get("pay_amount_actual"))));
				sheet.addCell(new Label(4, i, result.get(j).get("payment_account_name")));
				sheet.addCell(new Label(5, i, result.get(j).get("pay_type_name")));
				sheet.addCell(new Label(6, i, String.valueOf(result.get(j).get("poundage"))));
				sheet.addCell(new Label(7, i, result.get(j).get("remark")));
			}

			os.flush();
			workbook.write();
			wb.close();
			workbook.close();
			os.close();
			response.flushBuffer();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @Title: updateCivilizationInfo 
	 * @Description: 下载打印模版
	 * @param request
	 * @param response
	 * @return 
	 * @return String
	 * @throws
	 */
	@RequestMapping("downloadExcelCollect")
	public void downloadExcelCollect(HttpServletRequest request, HttpServletResponse response){
		String orderId = request.getParameter("orderId");
		
		OrderInfo orderInfo = orderInfoService.selectByPrimaryKey(orderId);
		
		List<Map<String, String>> result = orderFundSettlementService.getOrderFundInfo(orderId);
		
		try {
			@SuppressWarnings("deprecation")
			String path = request.getRealPath("/resources/excel/print_template_collect.xls");
			OutputStream os = response.getOutputStream();// 取得输出流
			response.reset();// 清空输出流
			
			// 设定输出文件头
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition",
					"attachment; filename=" + orderId + ".xls");
			
			File file = new File(path);
			
			Workbook wb = Workbook.getWorkbook(file);
			WorkbookSettings settings = new WorkbookSettings();  
			settings.setEncoding("GB18030"); //关键代码，解决中文乱码 
			WritableWorkbook workbook = Workbook.createWorkbook(os, wb, settings);
			
			WritableSheet sheet = workbook.getSheet(0);
			
			Calendar a = Calendar.getInstance();
			System.out.println();//得到年
			System.out.println();//由于月份是从0开始的所以加1
			System.out.println(a.get(Calendar.DATE));
			
			sheet.addCell(new Label(1, 7, ((EmployeeInfo)request.getSession().getAttribute("userInfo")).getName()));
			sheet.addCell(new Label(6, 7, String.valueOf(a.get(Calendar.YEAR))));
			sheet.addCell(new Label(8, 7, String.valueOf(a.get(Calendar.MONTH)+1)));
			sheet.addCell(new Label(10, 7, String.valueOf(a.get(Calendar.DATE))));
			
			for(int i = 2, j = 0; j < result.size(); i++, j++){
				sheet.addCell(new Label(0, i, result.get(j).get("good_name")));
				sheet.addCell(new Label(1, i, String.valueOf(result.get(j).get("amount"))));
				sheet.addCell(new Label(2, i, result.get(j).get("unit")));
				sheet.addCell(new Label(3, i, String.valueOf(result.get(j).get("price"))));
				double payPrice = Double.valueOf(String.valueOf(result.get(j).get("pay_amount_actual")))*100;
				DecimalFormat format = new DecimalFormat("#");
				String[] sMoney = format.format(payPrice).split("");
				for(int l = 12, k = sMoney.length - 1; k < 0; k--, l--){
					sheet.addCell(new Label(l, i, sMoney[k]));
				}
				int count = 0;
				if((9 - sMoney.length) > 0){
					count = 9 - sMoney.length;
					for(int m = 0, n = 4; m < count; m++, n++){
						sheet.addCell(new Label(n, i, "0"));
					}
				}
			}
			
			if(null != orderInfo.getPayPrice()){
				double orderPirce = orderInfo.getPayPrice()*100;
				String[] bigOrderPrice = numberToStr(orderPirce);
				sheet.addCell(new Label(0, 6, "                               "+bigOrderPrice[0] +
						"   " + bigOrderPrice[1] + "   " + bigOrderPrice[2] + "   " + bigOrderPrice[3] + 
						"   " + bigOrderPrice[4] + "   " + bigOrderPrice[5] + "   " + bigOrderPrice[6] + 
						"   " + bigOrderPrice[7] + "   " + bigOrderPrice[8]));
			}
			
			os.flush();
			workbook.write();
			wb.close();
			workbook.close();
			os.close();
			response.flushBuffer();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String[] numberToStr(double p){
		StringBuilder sb = new StringBuilder();
		String[] str = { "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖" };
		DecimalFormat format = new DecimalFormat("#");
		String[] sMoney = format.format(p).split("");
		if((9 - sMoney.length) > 0){
			for(int j = 0; j < 9 - sMoney.length; j++){
				sb.append("零");
			}
		}
		for(int i = 0; i < sMoney.length; i++){
			sb.append(str[Integer.valueOf(sMoney[i])]);
		}
		return sb.toString().split("");
	}
}

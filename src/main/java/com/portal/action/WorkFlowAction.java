package com.portal.action;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
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
import com.portal.bean.EmployeeInfo;
import com.portal.bean.OrderDetailInfo;
import com.portal.bean.OrderInfo;
import com.portal.common.util.JsonUtils;
import com.portal.service.OrderDetailInfoService;
import com.portal.service.OrderInfoService;
import com.portal.service.WorkFlowService;

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
	private OrderDetailInfoService orderDetailInfoService;
	
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
	 * @Title: flowInfoIndex 
	 * @Description: 审批列表
	 * @return 
	 * @return String
	 * @throws
	 */
	@RequestMapping("/achieveExamList")
	public String achieveExamList(){
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
		//获取当前用户id
//		String userId = request.getSession().getAttribute("user").toString();
		String userId = "1";
		String defKey = request.getParameter("defKey");
		
		List<Task> list = workFlowService.selectTaskListById(userId, defKey);
		
		JSONArray result = new JSONArray();
		JSONObject temp = new JSONObject();
		if("1".equals(request.getParameter("temp"))){
			temp.put("clerkId", "233");
			temp.put("clerkName", "萧萧");
			temp.put("name", "每日业绩审核");
			temp.put("assignee", "部门经理:小冷");
		}else {
			temp.put("clerkId", "254");
			temp.put("clerkName", "萧萧");
			temp.put("name", "每日业绩审核");
			temp.put("assignee", "副经理:小雨");
		}
		
		result.add(temp);
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
		
//		JsonUtils.resultJson(list, list.size(), response, request);
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
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
//		paramMap.put("userId", ((EmployeeInfo)request.getSession().getAttribute("user")).getId());
		paramMap.put("dateInfo", null==request.getParameter("dateInfo")?sdf.format(new Date()):request.getParameter("dateInfo"));
		paramMap.put("userId", 233);
		Map<String, Object> result = workFlowService.selectlerkEverydayAchievenment(paramMap);
		
		if(null != result && null != result.get("phoneStaffId") && !"".equals(result.get("phoneStaffId"))){
			String[] phoneStaffIds = ((String)result.get("phoneStaffId")).split(",");
			String phoneStaffName = workFlowService.selectPhoneStaffName(phoneStaffIds);
			result.put("phoneStaffName", phoneStaffName);
		}
		
		request.setAttribute("id", request.getParameter("id"));
		request.setAttribute("result", result);
		
		return "flow/clerk_everyday_achievement";
	}
	
	/**
	 * @Title: toAchieveExam 
	 * @Description: 开始审批流程
	 * @param request
	 * @param response
	 * @throws
	 */
	@RequestMapping("toAchieveExam")
	public void toAchieveExam(HttpServletRequest request, HttpServletResponse response){
//		String userId = ((EmployeeInfo)request.getSession().getAttribute("user")).getId();
		String userId = "233";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userId", userId);
		paramMap.put("dateInfo", sdf.format(new Date()));
		paramMap.put("orderInfo", request.getParameter("orderInfo"));
		ProcessInstance pi = workFlowService.startProcess("leaderApprove", sdf.format(new Date())+userId, paramMap);
		
		request.setAttribute("piId", pi.getProcessInstanceId());
		
		achieveExam(request, response);
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
		String taskId = request.getParameter("taskId");
		String id = request.getParameter("taskId");
		String examMessage = request.getParameter("taskId");
		String suggestion = request.getParameter("suggestion");
		
		EmployeeInfo ei = (EmployeeInfo)request.getSession().getAttribute("user");
		
		Map<String, String> paramMap = new HashMap<String, String>();
		
		//获取任务ID
		paramMap.put("taskId", taskId);
		//获取连线的名称
		paramMap.put("outcome", suggestion);
		//批注信息
		paramMap.put("comment", examMessage);
		//订单编号
		paramMap.put("id", id);
		//用户id
		paramMap.put("userId", ei.getId() + "," + ei.getName());
		
		workFlowService.saveSubmitTask(paramMap);
		
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
	public String storeOrderEveryday(HttpServletRequest request){
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
		
		criteria.put("id", request.getParameter("orderId"));
		criteria.put("orderNumber", request.getParameter("orderNumber"));
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
	@RequestMapping("updateOrderInfo")
	public void updateOrderInfo(HttpServletRequest request, HttpServletResponse response){
//		String userId = ((EmployeeInfo)request.getSession().getAttribute("user")).getId();
		String userId = "1";
		
		OrderInfo orderInfo = new OrderInfo();
		orderInfo.setFinanceOperatorId(userId);
		orderInfo.setFinanceDate(new Date());
		orderInfo.setFinanceFlag("1");
		if("1".equals(request.getParameter("operate"))){
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
}

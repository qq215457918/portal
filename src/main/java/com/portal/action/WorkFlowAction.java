package com.portal.action;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.portal.service.WorkFlowService;

@Controller
@RequestMapping("/workflow")
public class WorkFlowAction {
	
	@Autowired
	private WorkFlowService workFlowService;
	
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
			@RequestParam(value="flowFile", required=false) MultipartFile flowFile) {
		
		String fileName = flowFile.getName();
		
		workFlowService.insertFlowZip(flowFile, fileName);
		
		return "insert_flow_zip_list";
	}
	
	/**
	 * @Title: selectFlowInfo 
	 * @Description: 查询流程部署和定义列表
	 * @param request
	 * @param response
	 * @return 
	 * @return String
	 * @throws
	 */
	@RequestMapping("/selectFlowInfo")
	public String selectFlowInfo(HttpServletRequest request, HttpServletResponse response){
		// 获取流程部署列表（act_re_deployment表）
		List<Deployment> depList = workFlowService.selectDeployList();
		// 获取流程定义列表（act_re_procdef表）
		List<ProcessDefinition> pdList = workFlowService.selectProcessDefinitionList();
		
		request.setAttribute("depList", depList);
		request.setAttribute("pdList", pdList);
		
		return "select_flow_info";
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
	public String delDeployment(HttpServletRequest request, HttpServletResponse response){
		// 获取流程id
		String deploymentId = request.getParameter("deploymentId");
		// 使用部署对象id，删除流程定义
		workFlowService.delProcessDefinitionById(deploymentId);
		return "select_flow_info";
	}
	
	@RequestMapping("/selectTaskListById")
	public String selectTaskListById(HttpServletRequest request, HttpServletResponse response){
		//获取当前用户id
		String userId = request.getSession().getAttribute("user").toString();
		
		List<Task> list = workFlowService.selectTaskListById(userId);
		
		request.setAttribute("taskList", list);
		
		return "task_list_by_id";
	}
}

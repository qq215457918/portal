package com.portal.common;

import javax.servlet.http.HttpServletRequest;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.portal.service.EmployeeInfoService;
import com.portal.service.impl.EmployeeInfoServiceImpl;

/**
 * @ClassName: ManagerTaskHandler 
 * @Description: 任务分配
 * @author Miao Wenqiang
 * @date 2016年9月17日 下午9:06:38
 */
public class ManagerTaskHandler implements TaskListener {

	private static final long serialVersionUID = 1L;
	
	@Override
	public void notify(DelegateTask delegateTask) {
		
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		
		//获取当前用户id
//		String userId = request.getSession().getAttribute("user").toString();
		String userId = "1";
		
		WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();  
		
		webApplicationContext = RequestContextUtils.getWebApplicationContext(request);
		
		EmployeeInfoServiceImpl ee = (EmployeeInfoServiceImpl) webApplicationContext.getBean(EmployeeInfoService.class);
		
		ee.selectByPrimaryKey("1");
		// 设置办理人
		// TODO
		delegateTask.setAssignee(userId);
	}

}

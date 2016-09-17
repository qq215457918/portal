package com.portal.common;

import javax.servlet.http.HttpSession;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

/**
 * @ClassName: ManagerTaskHandler 
 * @Description: 任务分配
 * @author Miao Wenqiang
 * @date 2016年9月17日 下午9:06:38
 */
public class ManagerTaskHandler implements TaskListener {

	private static final long serialVersionUID = 1L;
	
	@Autowired  
	private HttpSession session; 

	@Override
	public void notify(DelegateTask delegateTask) {
		//获取当前用户id
		String userId = session.getAttribute("user").toString();
		
		WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();  
		webApplicationContext.getBean("userService");
		
		// 设置办理人
		// TODO
		delegateTask.setAssignee(userId	);
	}

}

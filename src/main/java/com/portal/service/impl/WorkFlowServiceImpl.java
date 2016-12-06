package com.portal.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.bpmn.behavior.UserTaskActivityBehavior;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmActivity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.task.TaskDefinition;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.portal.bean.OrderInfo;
import com.portal.dao.extra.WorkFlowDao;
import com.portal.service.WorkFlowService;

/**
 * @ClassName: WorkFlowServiceImpl 
 * @Description: 工作流基础类
 * @author Miao Wenqiang
 * @date 2016年9月17日 下午10:50:15
 */
@Service
public class WorkFlowServiceImpl implements WorkFlowService {
	/**
     * log.
     */
    private static Logger logger = LoggerFactory.getLogger(WorkFlowServiceImpl.class);
        
    @Autowired
    private RepositoryService repositoryService;
     
    @Autowired
    private RuntimeService runtimeService;
     
    @Autowired
    private TaskService taskService;
     
    @Autowired
    private HistoryService historyService;
    
    @Autowired
    private WorkFlowDao workFlowDao;
    
    /**
	 * @Title: insertFlowZip 
	 * @Description: 通过zip文件部署流程
	 * @param flowFile
	 * @param fileName 
	 * @return void
	 * @throws
	 */
    @Override
    public void insertFlowZip(MultipartFile flowFile, String fileName) {
    	try {
    		// 获取上传的zip流
			ZipInputStream zipInputStream = new ZipInputStream(flowFile.getInputStream());
			// 部署流程
			repositoryService.createDeployment()
							.name(fileName)
							.addZipInputStream(zipInputStream)
							.deploy();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    /**
	 * @Title: selectDepFlowList 
	 * @Description: 获取流程部署列表
	 * @return 
	 * @return List<Deployment>
	 * @throws
	 */
    @Override
    public List<Deployment> selectDeployList() {
    	return repositoryService.createDeploymentQuery()
    					.orderByDeploymenTime()
    					.desc()
    					.list();
    }
    
    /**
	 * @Title: selectPdFlowList 
	 * @Description: 获取流程定义列表
	 * @return 
	 * @return List<ProcessDefinition>
	 * @throws
	 */
    @Override
    public List<ProcessDefinition> selectProcessDefinitionList() {
    	return repositoryService.createProcessDefinitionQuery()
    					.orderByProcessDefinitionVersion().asc()
    					.list();
    }
    
    /**
	 * @Title: selectFlowImage 
	 * @Description: 获取流程图
	 * @param deploymentId
	 * @param imageName
	 * @return 
	 * @return InputStream
	 * @throws
	 */
    @Override
    public InputStream selectFlowImage(String deploymentId, String imageName) {
    	return repositoryService.getResourceAsStream(deploymentId, imageName);
    }
    
    /**
	 * @Title: delProcessDefinitionById 
	 * @Description: 通过id，删除部署信息
	 * @param deploymentId 
	 * @return void
	 * @throws
	 */
    @Override
    public void delProcessDefinitionById(String deploymentId) {
    	repositoryService.deleteDeployment(deploymentId, true);
    }
    
    /**
	 * @Title: startProcess 
	 * @Description: 根据流程定义key 启动流程
	 * @param processDefinitionKey 
	 * @param busKey 关联业务参数
	 * @param paramMap 流程参数 
	 * @return void
	 * @throws
	 */
    @Override
    public ProcessInstance startProcess(String processDefinitionKey, String busKey, Map<String, Object> paramMap) {
    	ProcessInstance pi = runtimeService.startProcessInstanceByKey(processDefinitionKey, busKey, paramMap);
    	String piId = pi.getId();
    	if(paramMap.entrySet().size() > 0){
    		for(Map.Entry<String, Object> entry : paramMap.entrySet()){
    			runtimeService.setVariable(piId, entry.getKey(), entry.getValue());
    		}
    	}
    	
    	return pi;
    }
    
    /**
	 * @Title: selectTaskListById 
	 * @Description: 通过用户id 获取流程列表 
	 * @param userId
	 * @return 
	 * @return List<Task>
	 * @throws
	 */
    @Override
    public List<Task> selectTaskListById(String userId, String defKey) {
    	return taskService.createTaskQuery()
    					.includeProcessVariables().includeTaskLocalVariables()
    					.taskDefinitionKey(defKey)
    					.taskAssignee(userId)
    					.orderByTaskCreateTime()
    					.desc().list();
    }
    
    /**
	 * @Title: selectTaskCountById 
	 * @Description: 获取当前人员执行任务数量
	 * @param userId
	 * @param defKey
	 * @return int
	 * @throws
	 */
    @Override
    public int selectTaskCountById(String userId, String defKey) {
    	return taskService.createTaskQuery()
				.includeProcessVariables().includeTaskLocalVariables()
				.taskDefinitionKey(defKey)
				.taskAssignee(userId)
				.list().size();
    }
    
    /**
     * @Title: findOutComeListByTaskId 
     * @Description: 获取操作列表
     * @param taskId
     * @return 
     * @return List<String>
     * @throws
     */
    @Override
	public List<String> findOutComeListByTaskId(String taskId) {
		//返回存放连线的名称集合
		List<String> list = new ArrayList<String>();
		// 使用任务ID，查询任务对象
		Task task = taskService.createTaskQuery()//
					.taskId(taskId)
					.singleResult();
		// 获取流程定义ID
		String processDefinitionId = task.getProcessDefinitionId();
		// 查询ProcessDefinitionEntiy对象
		ProcessDefinitionEntity processDefinitionEntity = (ProcessDefinitionEntity) repositoryService.getProcessDefinition(processDefinitionId);
		// 使用任务对象Task获取流程实例ID
		String processInstanceId = task.getProcessInstanceId();
		// 使用流程实例ID，查询正在执行的执行对象表，返回流程实例对象
		ProcessInstance pi = runtimeService.createProcessInstanceQuery()
					.processInstanceId(processInstanceId)
					.singleResult();
		//获取当前活动的id
		String activityId = pi.getActivityId();
		// 获取当前的活动
		ActivityImpl activityImpl = processDefinitionEntity.findActivity(activityId);
		// 获取当前活动完成之后连线的名称
		List<PvmTransition> pvmList = activityImpl.getOutgoingTransitions();
		if(pvmList!=null && pvmList.size()>0){
			for(PvmTransition pvm:pvmList){
				String name = (String) pvm.getProperty("name");
				if(StringUtils.isNotBlank(name)){
					list.add(name);
				}
				else{
					list.add("提交");
				}
			}
		}
		return list;
	}
    
    /**
     * @Title: saveSubmitTask 
     * @Description: 提交审批 
     * @param paramMap 
     * @return void
     * @throws
     */
    @Override
	public void saveSubmitTask(Map<String, String> paramMap) {
		//获取任务ID
		String taskId = paramMap.get("taskId");
		//获取连线的名称
		String outcome = paramMap.get("outcome");
		//批注信息
		String message = paramMap.get("comment");
		//订单编号
		String id = paramMap.get("id");
		//用户id
		String userId = paramMap.get("userId");
		
		//使用任务ID，查询任务对象，获取流程流程实例ID
		Task task = taskService.createTaskQuery()//
						.taskId(taskId)//使用任务ID查询
						.singleResult();
		//获取流程实例ID
		String processInstanceId = task.getProcessInstanceId();

		// 添加当前任务审核人
		Authentication.setAuthenticatedUserId(userId);
		taskService.addComment(taskId, processInstanceId, message);

		Map<String, Object> variables = new HashMap<String,Object>();
		if(outcome!=null && !outcome.equals("提交")){
			variables.put("outcome", outcome);
		}

		// 使用任务ID，完成当前人的个人任务，同时流程变量
		taskService.complete(taskId, variables);
		// 当任务完成之后，需要指定下一个任务的办理人
//		Task tssk = taskService.createTaskQuery().processDefinitionKey(task.getProcessDefinitionId()).singleResult();
//		//TODO
//		String leaderId = userService.getLeaderId();
//		taskService.claim(tssk.getId(), leaderId);
		
		//查询流程是否完成
		ProcessInstance pi = runtimeService.createProcessInstanceQuery()//
						.processInstanceId(processInstanceId)//使用流程实例ID查询
						.singleResult();
		//流程结束了
		if(pi==null){
			//更新订单状态
			//TODO
		}
	}
    
    /**
     * @Title: findCommentByIdAndKey 
     * @Description: 根据订单id 查询历史审批信息
     * @param id
     * @return 
     * @return List<Comment>
     * @throws
     */
    @Override
	public List<Comment> findCommentByIdAndKey(String id, String depKey) {
		//组织流程表中的字段中的值
		String objId = depKey+"."+id;
		
		// 使用历史的流程实例查询，返回历史的流程实例对象，获取流程实例ID
		HistoricProcessInstance hpi = historyService.createHistoricProcessInstanceQuery()//对应历史的流程实例表
						.processInstanceBusinessKey(objId)//使用BusinessKey字段查询
						.singleResult();
		//流程实例ID
		String processInstanceId = hpi.getId();
		List<Comment> list = taskService.getProcessInstanceComments(processInstanceId);
		return list;
	}
    
    /**
	 * @Title: findCommentByTaskId 
	 * @Description: 根据任务id获取批注信息
	 * @param taskId
	 * @return 
	 * @return List<Comment>
	 * @throws
	 */
    @Override
    public List<Comment> findCommentByTaskId(String taskId) {
    	Task task = taskService.createTaskQuery()
    					.taskId(taskId)
    					.singleResult();
    	
    	return taskService.getProcessInstanceComments(task.getProcessInstanceId());
    }
    //------------------------------- 
     
    /**
	 * @Title: findLatestProcessDefinitionByPrcDefKey 
	 * @Description: 根据流程定义Key查询最新流程定义.
	 * @param processDefinitionKey
	 * @return 
	 * @return ProcessDefinition
	 * @throws
	 */
    @Override
    public ProcessDefinition findLatestProcessDefinitionByPrcDefKey(String processDefinitionKey) {
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                                                               .processDefinitionKey(processDefinitionKey)
                                                               .latestVersion()
                                                               .singleResult();
        return processDefinition;
                                 
    }
     
    /**
	 * @Title: findProcessDefinitionByPrcDefId 
	 * @Description: 根据流程定义Id查询最新流程定义
	 * @param processDefinitionId
	 * @return 
	 * @return ProcessDefinition
	 * @throws
	 */
    @Override
    public ProcessDefinition findProcessDefinitionByPrcDefId(String processDefinitionId) {
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .processDefinitionId(processDefinitionId)
                .orderByProcessDefinitionVersion()
                .desc()
                .singleResult();
         
        return processDefinition;
    }
     
    /**
	 * @Title: findProcessDefinitionEntityByProcDefId 
	 * @Description: 根据流程定义Id查询流程定义
	 * @param processDefinitionId
	 * @return 
	 * @return ProcessDefinitionEntity
	 * @throws
	 */
    @Override
    public ProcessDefinitionEntity findProcessDefinitionEntityByProcDefId(String processDefinitionId) {
        ProcessDefinitionEntity processDefinitionEntity  = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService)
                                                                  .getDeployedProcessDefinition(processDefinitionId);
        return processDefinitionEntity;
    }
     
    /**
	 * @Title: findProcessInstanceByProcInst 
	 * @Description: 根据流程实例Id查询流程实例
	 * @param processInstanceId
	 * @return 
	 * @return ProcessInstance
	 * @throws
	 */
    @Override
    public ProcessInstance findProcessInstanceByProcInst(String processInstanceId) {
        return runtimeService.createProcessInstanceQuery()
                             .processInstanceId(processInstanceId)
                             .singleResult();
    }
     
    /**
	 * @Title: findExecutionByProcInst 
	 * @Description: 根据流程实例Id查询流程实例
	 * @param processInstanceId
	 * @return 
	 * @return Execution
	 * @throws
	 */
    @Override
    public Execution findExecutionByProcInst(String processInstanceId) {
        return runtimeService.createExecutionQuery().processInstanceId(processInstanceId).singleResult();
    }
     
    /**
	 * @Title: findTaskByProcInstId 
	 * @Description: 根据流程实例Id查询任务
	 * @param processInstanceId
	 * @return 
	 * @return Task
	 * @throws
	 */
    @Override
    public Task findTaskByProcInstId(String processInstanceId) {
        return taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();
    }
    
    /**
	 * @Title: findTaskByExecutionId 
	 * @Description:  根据实例Id查询任务
	 * @param executionId
	 * @return 
	 * @return Task
	 * @throws
	 */
    @Override
    public Task findTaskByExecutionId(String executionId) {
        return taskService.createTaskQuery().executionId(executionId).singleResult();
    }
     
    /**
	 * @Title: findTaskDefinitionByActivityImpl 
	 * @Description:  根据活动节点查询任务定义
	 * @param activityImpl
	 * @return 
	 * @return TaskDefinition
	 * @throws
	 */
    @Override
    public TaskDefinition findTaskDefinitionByActivityImpl(ActivityImpl activityImpl) {
          return ((UserTaskActivityBehavior)activityImpl.getActivityBehavior()).getTaskDefinition(); 
    }
     
    /**
	 * @Title: beforeTaskDefinition 
	 * @Description: 查询上一个节点
	 * @param activityImpl
	 * @param activityId
	 * @param elString
	 * @return 
	 * @return TaskDefinition
	 * @throws
	 */
    @Override
    public TaskDefinition beforeTaskDefinition(ActivityImpl activityImpl,String activityId, String elString)  {
        if("userTask".equals(activityImpl.getProperty("type")) && !activityId.equals(activityImpl.getId())){ 
            TaskDefinition taskDefinition = null;
            if(activityImpl != null){
                taskDefinition = findTaskDefinitionByActivityImpl(activityImpl);
            }
            return taskDefinition;
        }else{ 
            List<PvmTransition> inTransitions = activityImpl.getIncomingTransitions();   //通过活动节点查询所有线路
            if(null != inTransitions && inTransitions.size() > 0){
                List<PvmTransition> inTransitionsTemp = null; 
                for(PvmTransition tr:inTransitions){   
                    PvmActivity ac = tr.getSource();      //获取线路的前节点   
                    if("exclusiveGateway".equals(ac.getProperty("type"))){ 
                        inTransitionsTemp = ac.getIncomingTransitions(); 
                        if(inTransitionsTemp.size() == 1){ 
                            return beforeTaskDefinition((ActivityImpl)inTransitionsTemp.get(0).getSource(), activityId, elString); 
                        }else if(inTransitionsTemp.size() > 1){ 
                            for(PvmTransition tr1 : inTransitionsTemp){ 
                                Object s = tr1.getProperty("conditionText"); 
                                if(elString.equals(StringUtils.replacePattern(StringUtils.trim(s.toString()), " ", ""))){ 
                                    return beforeTaskDefinition((ActivityImpl)tr1.getSource(), activityId, elString); 
                                } 
                            } 
                        } 
                    }
                }
            }
            return null; 
        } 
    }
     
     
    /**
	 * @Title: nextTaskDefinition 
	 * @Description: 查询下一个节点
	 * @param activityImpl
	 * @param activityId
	 * @param elString
	 * @return 
	 * @return TaskDefinition
	 * @throws
	 */
    @Override
    public TaskDefinition nextTaskDefinition(ActivityImpl activityImpl,String activityId, String elString)  {
         
        if("userTask".equals(activityImpl.getProperty("type")) && !activityId.equals(activityImpl.getId())){ 
            TaskDefinition taskDefinition = null;
            if(activityImpl != null){
                taskDefinition = findTaskDefinitionByActivityImpl(activityImpl);
            }
            return taskDefinition;
        }else{
            List<PvmTransition> outTransitions = activityImpl.getOutgoingTransitions();   //通过活动节点查询所有线路
            if(null != outTransitions && outTransitions.size() > 0){
                List<PvmTransition> outTransitionsTemp = null; 
                for(PvmTransition tr:outTransitions){  
                    PvmActivity ac = tr.getDestination();         //获取线路的终点节点   
                    if("exclusiveGateway".equals(ac.getProperty("type"))){ 
                        outTransitionsTemp = ac.getOutgoingTransitions(); 
                        if(outTransitionsTemp.size() == 1){ 
                            return nextTaskDefinition((ActivityImpl)outTransitionsTemp.get(0).getDestination(), activityId, elString); 
                        }else if(outTransitionsTemp.size() > 1){ 
                            for(PvmTransition tr1 : outTransitionsTemp){ 
                                Object s = tr1.getProperty("conditionText"); 
                                if(s != null && elString.equals(StringUtils.replacePattern(StringUtils.trim(s.toString()), " ", ""))){ 
                                    return nextTaskDefinition((ActivityImpl)tr1.getDestination(), activityId, elString); 
                                } 
                            } 
                        } 
                    }else if("userTask".equals(ac.getProperty("type"))){ 
                        return findTaskDefinitionByActivityImpl((ActivityImpl)ac);
                    }
                    else if("startEvent".equals(ac.getProperty("type"))){ 
                        return findTaskDefinitionByActivityImpl((ActivityImpl)ac);
                    }
                    else{ 
                        logger.info(ac.getProperty("type").toString()); 
                    } 
                }
            }
            return null; 
        }
     
    }
     
    /**
	 * @Title: findPvmActivity 
	 * @Description: 根据活动节点、活动线路查询线路的连接线
	 * @param activityImpl
	 * @param transitions
	 * @return 
	 * @return PvmActivity
	 * @throws
	 */
    @SuppressWarnings("rawtypes")
    @Override
    public PvmActivity findPvmActivity(ActivityImpl activityImpl, String transitions) {
         
        PvmActivity activity = null;
        List<PvmTransition> pvmTransitions = activityImpl.getOutgoingTransitions();   //获取所有线路
         
        for (Iterator iterator = pvmTransitions.iterator(); iterator.hasNext();) {
                PvmTransition pvmTransition = (PvmTransition) iterator.next();
                PvmActivity pvmActivity = pvmTransition.getDestination();           //获取下一个任务节点
                String transitionsVal = (String) pvmActivity.getProperty("name");
                if(transitions.equals(transitionsVal)){
                    activity = pvmActivity;
                    break;
                }
        }
        return activity;
    }
     
    /**
	 * @Title: findTaskDefinition 
	 * @Description: 根据流程定义Id查询任务定义
	 * @param processDefinitionId
	 * @return 
	 * @return TaskDefinition
	 * @throws
	 */
    @Override
    public TaskDefinition findTaskDefinition(String processDefinitionId) {
         
        //获取流程定义
        ProcessDefinitionEntity processDefinitionEntity = findProcessDefinitionEntityByProcDefId(processDefinitionId);
        TaskDefinition tdf = null;
         
        if(processDefinitionEntity != null){
            List<ActivityImpl> activityImpls = processDefinitionEntity.getActivities();    //获取所有活动的节点
            for(int i = activityImpls.size() - 1; i > 0; i-- ){
                ActivityImpl activityImpl = activityImpls.get(i);    
                String startEventType = (String) activityImpl.getProperty("type");
                if("startEvent".equals(startEventType)){
                    tdf = nextTaskDefinition(activityImpl, activityImpl.getId(), null);
                }
            }  
        }
        return tdf;
    }
     
    /**
	 * @Title: addTaskComment 
	 * @Description: 添加任务意见
	 * @param taskId
	 * @param processInstanceId
	 * @param comment 
	 * @return void
	 * @throws
	 */
    @Override
    public void addTaskComment(String taskId,String processInstanceId,String comment) {
         taskService.addComment(taskId, processInstanceId, comment);
    }
     
    /**
	 * @Title: claimTask 
	 * @Description: 拾取任务
	 * @param taskId
	 * @param operator 
	 * @return void
	 * @throws
	 */
    @Override
    public void claimTask(String taskId,String operator) {
         taskService.claim(taskId, operator);
    }
    
    /**
	 * @Title: selectlerkEverydayAchievenment 
	 * @Description: 员工每日业绩查看
	 * @param paramMap
	 * @return 
	 * @return Map<String, Object>
	 * @throws
	 */
    @Override
    public Map<String, Object> selectlerkEverydayAchievenment(Map<String, Object> paramMap) {
    	Map<String, Object> map1 = workFlowDao.selectlerkEverydayAchievenment(paramMap);
    	Map<String, Object> map2 = workFlowDao.selectlerkEverydayReception(paramMap);
    	List<Map<String, String>> map3 = workFlowDao.selectlerkEverydayTypeCount(paramMap);
    	
    	
    	if(null == map1){
    		map1 = new HashMap<String, Object>();
    	}
    	if(null != map2){
    		map1.putAll(map2);
    	}
    	if(null != map3){
    		List<String> typeInfo = new ArrayList<String>();
    		for (int i = 0; i < map3.size(); i++) {
    			StringBuilder temp = new StringBuilder();
    			temp.append(map3.get(i).get("typeName")).append("人数: ").append(String.valueOf(map3.get(i).get("typeCount")));
    			typeInfo.add(temp.toString());
			}
    		map1.put("typeCount", typeInfo);
    	}
    	
    	return map1;
    }

    /**
	 * @Title: selectPhoneStaffName 
	 * @Description: 获取电联人员
	 * @param phoneStaffIds
	 * @return 
	 * @return String
	 * @throws
	 */
    @Override
    public String selectPhoneStaffName(String[] phoneStaffIds) {
    	return workFlowDao.selectPhoneStaffName(phoneStaffIds);
    }
    
    /**
	 * @Title: selectClerkReceiveList 
	 * @Description: 业务员每日接待信息
	 * @param paramMap
	 * @return 
	 * @return List<OrderInfo>
	 * @throws
	 */
    @Override
    public List<Map<String, Object>> selectClerkReceiveList(Map<String, Object> paramMap) {
    	return workFlowDao.selectClerkReceiveList(paramMap);
    }
    
    /**
	 * @Title: selectClerkReceiveList 
	 * @Description: 业务员每日接待数量
	 * @param paramMap
	 * @return 
	 * @return List<OrderInfo>
	 * @throws
	 */
    @Override
    public int selectClerkReceiveCount(Map<String, Object> paramMap) {
    	return workFlowDao.selectClerkReceiveCount(paramMap);
    }
    
    /**
	 * @Title: selectTaskCountById 
	 * @Description: 获取业务员每日的业绩列表
	 * @param paramMap
	 * @return List<Map<String, Object>>
	 * @throws
	 */
    @Override
    public List<Map<String, Object>> selectClerkDayList(Map<String, Object> paramMap) {
    	return workFlowDao.selectClerkDayList(paramMap);
    }
}
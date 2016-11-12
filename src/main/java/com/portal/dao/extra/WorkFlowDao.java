package com.portal.dao.extra;

import java.util.Map;

import org.springframework.stereotype.Repository;
/**
 * @author miaowq
 * 审批流程业务查询
 *
 */
@Repository
public interface WorkFlowDao {

	/**
	 * @Title: selectlerkEverydayAchievenment 
	 * @Description: 员工每日业绩查看(订单统计)
	 * @param paramMap
	 * @return 
	 * @return Map<String, Object>
	 * @throws
	 */
	Map<String, Object> selectlerkEverydayAchievenment(Map<String, Object> paramMap);

	/**
	 * @Title: selectlerkEverydayAchievenment 
	 * @Description: 员工每日业绩查看（接待统计）
	 * @param paramMap
	 * @return 
	 * @return Map<String, Object>
	 * @throws
	 */
	Map<String, Object> selectlerkEverydayReception(Map<String, Object> paramMap);

	/**
	 * @Title: selectPhoneStaffName 
	 * @Description: 获取电联人员
	 * @param phoneStaffIds
	 * @return 
	 * @return String
	 * @throws
	 */
	String selectPhoneStaffName(String[] phoneStaffIds);
	
}
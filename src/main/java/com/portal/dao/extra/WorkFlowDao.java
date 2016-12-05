package com.portal.dao.extra;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.portal.bean.OrderInfo;
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

	/**
	 * @Title: selectClerkReceiveList 
	 * @Description: 业务员每日接待信息
	 * @param paramMap
	 * @return 
	 * @return List<OrderInfo>
	 * @throws
	 */
	List<Map<String, Object>> selectClerkReceiveList(Map<String, Object> paramMap);

	/**
	 * @Title: selectClerkReceiveList 
	 * @Description: 业务员每日接待数量
	 * @param paramMap
	 * @return 
	 * @return List<OrderInfo>
	 * @throws
	 */
	int selectClerkReceiveCount(Map<String, Object> paramMap);

	/**
	 * @Title: selectTaskCountById 
	 * @Description: 获取业务员每日的业绩列表
	 * @param paramMap
	 * @return List<Map<String, Object>>
	 * @throws
	 */
	List<Map<String, Object>> selectClerkDayList(Map<String, Object> paramMap);

	/**
	 * @Title: selectTaskCountById 
	 * @Description: 获取业务员每日接待客户种类数量
	 * @param paramMap
	 * @return List<Map<String, Object>>
	 * @throws
	 */
	List<Map<String, String>> selectlerkEverydayTypeCount(Map<String, Object> paramMap);
	
}
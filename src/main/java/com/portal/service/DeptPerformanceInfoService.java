package com.portal.service;

import com.portal.bean.Criteria;
import com.portal.bean.DeptPerformanceInfo;
import com.portal.bean.result.DeptPerforInfoForm;

import net.sf.json.JSONObject;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface DeptPerformanceInfoService {
    int countByExample(Criteria example);

    DeptPerformanceInfo selectByPrimaryKey(String id);

    List<DeptPerformanceInfo> selectByExample(Criteria example);

    int deleteByPrimaryKey(String id);

    int updateByPrimaryKeySelective(DeptPerformanceInfo record);

    int updateByPrimaryKey(DeptPerformanceInfo record);

    int deleteByExample(Criteria example);

    int updateByExampleSelective(DeptPerformanceInfo record, Criteria example);

    int updateByExample(DeptPerformanceInfo record, Criteria example);

    int insert(DeptPerformanceInfo record);

    int insertSelective(DeptPerformanceInfo record);
    
    /**
     * @Title: ajaxOrganiPerformance 
     * @Description: 报表系统中查询机构业绩
     * @param criteria  公共查询条件类
     * @return List<OrderInfoForm>
     * @throws
     */
    JSONObject ajaxOrganiPerformance(HttpServletRequest request);
    
    /**
     * @Title: ajaxPerformanceData 
     * @Description: 异步获取部门业绩数据
     * @param request
     * @param response
     * @return JSONObject
     * @throws
     */
    JSONObject ajaxPerformanceData(HttpServletRequest request, HttpServletResponse response);
    
    /**
     * @Title: ajaxIndividualRanking 
     * @Description: 异步获取个人业绩排名数据
     * @param request
     * @param response
     * @return JSONObject
     * @throws
     */
    JSONObject ajaxIndividualRanking(HttpServletRequest request, HttpServletResponse response);
    
    /**
     * @Title: getIndividualByCondition 
     * @Description: 根据条件查询数据-导出
     * @param criteria
     * @return List<DeptPerforInfoForm>
     * @author Xia ZhengWei
     * @date 2017年1月5日 下午10:43:36 
     * @version V1.0
     */
    List<DeptPerforInfoForm> getIndividualByCondition(Criteria criteria);
    
    /**
     * @Title: getPerformanceForTask 
     * @Description: 每日定时器获取所有员工的业绩
     * @param criteria
     * @return List<DeptPerforInfo>
     * @author Xia ZhengWei
     * @date 2017年2月8日 下午11:28:17 
     * @version V1.0
     */
    List<DeptPerformanceInfo> getPerformanceForTask(Criteria criteria);
}
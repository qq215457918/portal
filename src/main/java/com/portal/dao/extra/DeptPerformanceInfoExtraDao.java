package com.portal.dao.extra;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.portal.bean.Criteria;
import com.portal.bean.DeptPerformanceInfo;
import com.portal.bean.result.DeptPerforInfoForm;

@Repository
public interface DeptPerformanceInfoExtraDao {
    
    /**
     * @Title: getCountsByCondition 
     * @Description: 报表系统中查询机构业绩总条数
     * @param criteria  公共查询条件类
     * @return int
     * @throws
     */
    int getCountsByCondition(Criteria criteria);
    
    /**
     * @Title: getOrganiPerformance 
     * @Description: 报表系统中查询机构业绩
     * @param criteria  公共查询条件类
     * @return List<DeptPerforInfoForm>
     * @throws
     */
    List<DeptPerforInfoForm> getOrganiPerformance(Criteria criteria);
    
    /**
     * @Title: countByCondition 
     * @Description: 报表系统中查询部门业绩总条数
     * @param criteria  公共查询条件类
     * @return int
     * @throws
     */
    int countByCondition(Criteria criteria);
    
    /**
     * @Title: seleteByCondition 
     * @Description: 查询部门业绩
     * @param criteria  公共查询条件类
     * @return List<OrderInfoForm>
     * @throws
     */
    List<DeptPerforInfoForm> seleteByCondition(Criteria criteria);
    
    /**
     * @Title: getIndividualByCondition 
     * @Description: 查询个人业绩排名数据
     * @param criteria  公共查询条件类
     * @return List<OrderInfoForm>
     * @throws
     */
    List<DeptPerforInfoForm> getIndividualByCondition(Criteria criteria);
    
    /**
     * @Title: getPerformanceForTask 
     * @Description: 每日定时器获取所有员工的业绩
     * @param criteria
     * @return List<DeptPerformanceInfo>
     * @author Xia ZhengWei
     * @date 2017年2月8日 下午11:28:17 
     * @version V1.0
     */
    List<DeptPerformanceInfo> getPerformanceForTask(Criteria criteria);
    
}
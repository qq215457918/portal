package com.portal.dao.extra;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.portal.bean.Criteria;
import com.portal.bean.result.VisitEverydayInfoForm;

@Repository
public interface VisitEverydayInfoExtDao {
    
    /**
     * @Title: getVisitCounts 
     * @Description: 根据起始日期条件查询每日登门客户数量
     * @param example
     * @return Map<String,Integer>
     * @throws
     */
    Map<String, Integer> getVisitCounts(Criteria example);
    
    /**
     * @Title: getWeekVisitCounts 
     * @Description: 获取指定日期中每日登门客户数量
     * @param startVisitDate    开始日期
     * @param customerArea  客户所属区域
     * @return Map<String,Object>
     * @throws
     */
    Map<String, Object> getWeekVisitCounts(@Param(value="startVisitDate") String startVisitDate, @Param(value="customerArea") String customerArea);
    
    /**
     * 根据条件查询记录总数
     */
    int countByCondition(Criteria example);

    /**
     * 根据条件查询记录集
     */
    List<VisitEverydayInfoForm> selectByCondition(Criteria example);
}
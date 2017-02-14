package com.portal.dao.extra;

import java.util.List;
import java.util.Map;

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
     * @Title: getDayAndVisitCounts 
     * @Description: 获取日期对应的登门数量Map
     * @param example
     * @return List<VisitEverydayInfoForm>
     * @throws
     */
    List<VisitEverydayInfoForm> getDayAndVisitCounts(Criteria example);
    
    /**
     * 根据条件查询记录总数
     */
    int countByCondition(Criteria example);

    /**
     * 根据条件查询记录集
     */
    List<VisitEverydayInfoForm> selectByCondition(Criteria example);
    
    /**
     * @Title: getTaskDataByCondition 
     * @Description: 定时器获取数据
     * @param criteria
     * @return List<VisitEverydayInfoForm>
     * @author Xia ZhengWei
     * @date 2017年1月19日 下午9:11:56 
     * @version V1.0
     */
    List<VisitEverydayInfoForm> getTaskDataByCondition(Criteria criteria);
}
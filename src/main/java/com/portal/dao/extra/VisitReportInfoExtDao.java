package com.portal.dao.extra;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.portal.bean.Criteria;
import com.portal.bean.result.VisitReportInfoForm;

@Repository
public interface VisitReportInfoExtDao {
    
    /**
     * @Title: getCustomerCounts 
     * @Description: 根据起始日期条件查询接待客户数量
     * @param example
     * @return Map<String,Integer>
     * @throws
     */
    Map<String, Integer> getCustomerCounts(Criteria example);
    
    /**
     * @Title: getAllCategoryCustomer 
     * @Description: 获取所有各种分类对应的客户数量
     * @param example
     * @return Map<String,Object>
     * @throws
     */
    Map<String, Object> getAllCategoryCustomer(Criteria example);
    
    /**
     * 根据条件查询记录总数
     */
    int countByCondition(Criteria example);

    /**
     * 根据条件查询记录集
     */
    List<VisitReportInfoForm> selectByCondition(Criteria example);
    
    /**
     * 根据条件查询业务员业绩数据总数
     */
    int getPerforCounts(Criteria example);
    
    /**
     * @Title: getPerforByCondition 
     * @Description: 根据条件查询业务员业绩数据
     * @param example
     * @return List<VisitReportInfoForm>
     * @author Xia ZhengWei
     * @date 2016年10月27日 下午9:22:04 
     * @version V1.0
     */
    List<VisitReportInfoForm> getPerforByCondition(Criteria example);
    
    /**
     * @Title: checkIsNewCount 
     * @Description: 判断客户是否为新客户
     * @param criteria
     * @return int
     * @author Xia ZhengWei
     * @date 2017年1月23日 下午10:30:50 
     * @version V1.0
     */
    int checkIsNewCount(Criteria criteria);
    
    /**
     * @Title: getRecevieCountsAndOrders 
     * @Description: 定时器获取成单及锁定的接待数和出单数
     * @param criteria 
     * @return List<VisitReportInfoForm> 
     * @author Xia ZhengWei
     * @date 2017年2月13日 下午10:45:24 
     * @version V1.0
     */
    List<VisitReportInfoForm>  getRecevieCountsAndOrders(Criteria criteria);
    
}
package com.portal.dao.extra;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.portal.bean.Criteria;
import com.portal.bean.VisitReportInfo;

@Repository
public interface VisitReportInfoExtDao {
    
    /**
     * @Title: getCustomerCounts 
     * @Description: 根据起始日期条件查询接待客户数量
     * @param example
     * @return int
     * @throws
     */
    Map<String, Integer> getCustomerCounts(Criteria example);
    
    /**
     * @Title: getAllCategoryCustomer 
     * @Description: 获取所有各种分类对应的客户数量
     * @param area  客户所属地区
     * @return Map<String,Integer>
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
    List<VisitReportInfo> selectByCondition(Criteria example);
    
}
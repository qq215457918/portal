package com.portal.dao.extra;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.portal.bean.Criteria;
import com.portal.bean.result.OrderInfoForm;

@Repository
public interface OrderInfoExtraDao {
    
    /**
     * @Title: countByCondition 
     * @Description: 报表系统中查询机构业绩总条数
     * @param criteria  公共查询条件类
     * @return int
     * @throws
     */
    int countByCondition(Criteria criteria);
    
    /**
     * @Title: getOrganiPerformance 
     * @Description: 报表系统中查询机构业绩
     * @param criteria  公共查询条件类
     * @return List<OrderInfoForm>
     * @throws
     */
    List<OrderInfoForm> getOrganiPerformance(Criteria criteria);
    
    
    
    
}
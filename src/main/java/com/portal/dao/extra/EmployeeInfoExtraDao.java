package com.portal.dao.extra;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.portal.bean.Criteria;
import com.portal.bean.result.EmployeeInfoForm;

@Repository
public interface EmployeeInfoExtraDao {
    
    /**
     * 根据条件查询记录总数
     */
    int countByConditions(Criteria example);

    /**
     * 根据条件查询记录集
     */
    List<EmployeeInfoForm> selectByConditions(Criteria example);

}
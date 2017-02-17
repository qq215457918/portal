package com.portal.dao.extra;

import java.util.List;
import java.util.Map;

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

    /**
     * @Title: getReceiveNameByPhoneId 
     * @Description: 统计每周对接业绩根据客服ID获取接待名称
     * @param criteria
     * @return Map<String, String>
     * @author Xia ZhengWei
     * @date 2017年2月13日 下午9:55:26 
     * @version V1.0
     */
    Map<String, String> getReceiveNameByPhoneId(Criteria criteria);
    
}
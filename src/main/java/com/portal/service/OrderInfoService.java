package com.portal.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.portal.bean.Criteria;
import com.portal.bean.OrderInfo;

import net.sf.json.JSONObject;

public interface OrderInfoService {
    int countByExample(Criteria example);

    OrderInfo selectByPrimaryKey(String id);

    List<OrderInfo> selectByExample(Criteria example);

    int deleteByPrimaryKey(String id);

    int updateByPrimaryKeySelective(OrderInfo record);

    int updateByPrimaryKey(OrderInfo record);

    int deleteByExample(Criteria example);

    int updateByExampleSelective(OrderInfo record, Criteria example);

    int updateByExample(OrderInfo record, Criteria example);

    int insert(OrderInfo record);

    int insertSelective(OrderInfo record);
    
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
    JSONObject getOrganiPerformance(HttpServletRequest request);
}
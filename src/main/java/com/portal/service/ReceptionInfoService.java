package com.portal.service;

import com.portal.bean.Criteria;
import com.portal.bean.ReceptionInfo;

import net.sf.json.JSONObject;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

public interface ReceptionInfoService {
    int countByExample(Criteria example);

    ReceptionInfo selectByPrimaryKey(String id);

    List<ReceptionInfo> selectByExample(Criteria example);

    int deleteByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ReceptionInfo record);

    int updateByPrimaryKey(ReceptionInfo record);

    int deleteByExample(Criteria example);

    int updateByExampleSelective(ReceptionInfo record, Criteria example);

    int updateByExample(ReceptionInfo record, Criteria example);

    int insert(ReceptionInfo record);

    int insertSelective(ReceptionInfo record);
    
    /**
     * @Title: ajaxFiltrateCustomers 
     * @Description: 报表统计数据--异步获取登门出单统计数据
     * @param request
     * @return JSONObject
     * @author Xia ZhengWei
     * @date 2016年10月17日 下午9:03:26 
     * @version V1.0
     */
    JSONObject ajaxVisitAndOutOrder(HttpServletRequest request);
    
    /**
     * @Title: ajaxOutOrderDetail 
     * @Description: 报表统计数据--异步获取登门出单详细数据
     * @param request
     * @return 
     * @return JSONObject
     * @throws 
     * @author Xia ZhengWei
     * @date 2016年10月17日 下午11:40:32 
     * @version V1.0
     */
    JSONObject ajaxOutOrderDetail(HttpServletRequest request);
}
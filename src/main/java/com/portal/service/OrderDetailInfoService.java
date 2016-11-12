package com.portal.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.portal.bean.Criteria;
import com.portal.bean.OrderDetailInfo;

import net.sf.json.JSONObject;

public interface OrderDetailInfoService {
    int countByExample(Criteria example);

    OrderDetailInfo selectByPrimaryKey(String id);

    List<OrderDetailInfo> selectByExample(Criteria example);

    int deleteByPrimaryKey(String id);

    int updateByPrimaryKeySelective(OrderDetailInfo record);

    int updateByPrimaryKey(OrderDetailInfo record);

    int deleteByExample(Criteria example);

    int updateByExampleSelective(OrderDetailInfo record, Criteria example);

    int updateByExample(OrderDetailInfo record, Criteria example);

    int insert(OrderDetailInfo record);

    int insertSelective(OrderDetailInfo record);
    
    /**
     * @Title: ajaxOutOrderDetail 
     * @Description: 报表统计数据--异步获取登门出单详细数据
     * @param request
     * @return JSONObject
     * @author Xia ZhengWei
     * @date 2016年10月17日 下午11:40:32 
     * @version V1.0
     */
    JSONObject ajaxOutOrderDetail(HttpServletRequest request);

	/**
	 * @Title: selectOrderInfoById 
	 * @Description: 通过id获取订单信息列表
	 * @param param
	 * @return void
	 * @throws
	 */
	List<OrderDetailInfo> selectOrderInfoById(Map<String, Object> param);
}
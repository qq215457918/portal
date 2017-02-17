package com.portal.service;

import com.portal.bean.Criteria;
import com.portal.bean.OrderFundSettlement;
import java.util.List;
import java.util.Map;

public interface OrderFundSettlementService {
    int countByExample(Criteria example);

    OrderFundSettlement selectByPrimaryKey(String id);

    List<OrderFundSettlement> selectByExample(Criteria example);

    int deleteByPrimaryKey(String id);

    int updateByPrimaryKeySelective(OrderFundSettlement record);

    int updateByPrimaryKey(OrderFundSettlement record);

    int deleteByExample(Criteria example);

    int updateByExampleSelective(OrderFundSettlement record, Criteria example);

    int updateByExample(OrderFundSettlement record, Criteria example);

    int insert(OrderFundSettlement record);

    int insertSelective(OrderFundSettlement record);

    /**
     * @Title: getOrderFundInfo 
     * @Description: 获取收款信息和商品信息
     * @param orderId
     * @return 
     * @return List<Map<String,Object>>
     * @throws
     */
	List<Map<String, String>> getOrderFundInfo(String orderId);
}
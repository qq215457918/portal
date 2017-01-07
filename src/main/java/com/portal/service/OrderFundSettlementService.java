package com.portal.service;

import com.portal.bean.Criteria;
import com.portal.bean.OrderFundSettlement;
import java.util.List;

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
}
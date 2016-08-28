package com.portal.service;

import com.portal.bean.Criteria;
import com.portal.bean.OrderInfo;
import java.util.List;

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
}
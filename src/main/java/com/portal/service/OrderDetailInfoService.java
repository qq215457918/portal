package com.portal.service;

import com.portal.bean.Criteria;
import com.portal.bean.OrderDetailInfo;
import java.util.List;

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
}
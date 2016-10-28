package com.portal.service;

import com.portal.bean.Criteria;
import com.portal.bean.OrderInfo;
import com.portal.bean.result.OrderInfoForm;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface OrderInfoService {

    /**
     * 获取正常的订单类型
     * @param customerId
     * @return
     */
    OrderInfoForm queryGoodsInfo(String customerId);

    /**
     * 查看已经支付的定金订单
     * @param customerId
     * @return
     * @throws InvocationTargetException 
     * @throws IllegalAccessException 
     */
    public OrderInfoForm queryRevokeDepositInfo(String customerId);

    /**
     * 退货订单
     * @param customerId
     * @return
     * @throws InvocationTargetException 
     * @throws IllegalAccessException 
     */
    public OrderInfoForm queryReturnGoodsInfo(String customerId);

    /**
     * 换货订单
     * @param customerId
     * @return
     * @throws InvocationTargetException 
     * @throws IllegalAccessException 
     */
    public OrderInfoForm xchangeReturnGoodsInfo(String customerId);

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

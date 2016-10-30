package com.portal.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.portal.bean.Criteria;
import com.portal.bean.OrderInfo;
import com.portal.bean.result.OrderInfoForm;

import net.sf.json.JSONObject;

public interface OrderInfoService {

    /**
     * 获取正常的订单类型
     * @param customerId
     * @return
     */
    List<OrderInfoForm> queryGoodsInfo(String customerId);

    /**
     * 查看已经支付的定金订单
     * @param customerId
     * @return
     * @throws InvocationTargetException 
     * @throws IllegalAccessException 
     */
    public List<OrderInfoForm> queryRevokeDepositInfo(String customerId);

    /**
     * 退货订单
     * @param customerId
     * @return
     * @throws InvocationTargetException 
     * @throws IllegalAccessException 
     */
    public List<OrderInfoForm> queryReturnGoodsInfo(String customerId);

    /**
     * 换货订单
     * @param customerId
     * @return
     * @throws InvocationTargetException 
     * @throws IllegalAccessException 
     */
    public List<OrderInfoForm> xchangeReturnGoodsInfo(String customerId);

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
     * @Title: ajaxClinchPerforEveryDay 
     * @Description: 异步获取每日成交业绩数据
     * @param request
     * @return JSONObject
     * @author Xia ZhengWei
     * @date 2016年10月30日 下午7:02:14 
     * @version V1.0
     */
    JSONObject ajaxClinchPerforEveryDay(HttpServletRequest request);
    
}

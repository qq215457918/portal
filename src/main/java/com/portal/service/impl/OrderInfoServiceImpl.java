package com.portal.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portal.bean.Criteria;
import com.portal.bean.OrderDetailInfo;
import com.portal.bean.OrderInfo;
import com.portal.bean.result.OrderInfoForm;
import com.portal.dao.OrderDetailInfoDao;
import com.portal.dao.OrderInfoDao;
import com.portal.service.OrderInfoService;

@Service
public class OrderInfoServiceImpl implements OrderInfoService {
    @Autowired
    private OrderInfoDao orderInfoDao;
    
    @Autowired
    private OrderDetailInfoDao orderInfoDetailDao;
    
    private static final Logger logger = LoggerFactory.getLogger(OrderInfoServiceImpl.class);
    
    public Criteria getCriteria(String customerId,int status,int orderType ,int payType){
    	Criteria example = new Criteria();
    	example.put("customer_id", customerId);
    	example.put("status", status);
    	example.put("order_type", orderType);
    	example.put("pay_type", payType);
    	return example;
    }
    
   public OrderInfoForm getOrderInfo(String customerId,int orderType ,int payType) throws IllegalAccessException, InvocationTargetException{
   	OrderInfoForm orderInfoForm = new OrderInfoForm();
   	List<OrderInfo> orderInfoList = orderInfoDao.selectByExample(getCriteria(customerId,1,orderType,payType));
   	BeanUtils.copyProperties(orderInfoForm, orderInfoList.get(0));
   	orderInfoForm.setOrderDetailInfoList(queryDetaiInfo(orderInfoList.get(0).getId()));
	   
	   return orderInfoForm;
   }
    /**
     * 查询商品订单 by customerId
     * by meng.yue
     * @return
     * @throws InvocationTargetException 
     * @throws IllegalAccessException 
     */
    public OrderInfoForm queryGoodsInfo(String customerId) throws IllegalAccessException, InvocationTargetException{

    	return getOrderInfo(customerId,1,0);
    } 
    
    /**
     * 撤销定金订单
     * @param customerId
     * @return
     * @throws InvocationTargetException 
     * @throws IllegalAccessException 
     */
    public OrderInfoForm queryRevokeDepositInfo(String customerId) throws IllegalAccessException, InvocationTargetException{
    	return getOrderInfo(customerId,1,1);
    } 
    
    /**
     * 退货订单
     * @param customerId
     * @return
     * @throws InvocationTargetException 
     * @throws IllegalAccessException 
     */
    public OrderInfoForm queryReturnGoodsInfo(String customerId) throws IllegalAccessException, InvocationTargetException{
    	return getOrderInfo(customerId,2,1);
    } 
    
    
    /**
     * 换货订单
     * @param customerId
     * @return
     * @throws InvocationTargetException 
     * @throws IllegalAccessException 
     */
    public OrderInfoForm xchangeReturnGoodsInfo(String customerId) throws IllegalAccessException, InvocationTargetException{
    	return getOrderInfo(customerId,3,1);
    } 
    
    /**
     * 获取 订单详情信息
     * @param orderId
     * @return
     */
    public List<OrderDetailInfo> queryDetaiInfo(String orderId){
    	Criteria example = new Criteria();
    	example.put("order_id", orderId);
    	return orderInfoDetailDao.selectByExample(example);
    }
    
    public int countByExample(Criteria example) {
        int count = this.orderInfoDao.countByExample(example);
        logger.debug("count: {}", count);
        return count;
    }

    public OrderInfo selectByPrimaryKey(String id) {
        return this.orderInfoDao.selectByPrimaryKey(id);
    }

    public List<OrderInfo> selectByExample(Criteria example) {
        return this.orderInfoDao.selectByExample(example);
    }

    public int deleteByPrimaryKey(String id) {
        return this.orderInfoDao.deleteByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(OrderInfo record) {
        return this.orderInfoDao.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(OrderInfo record) {
        return this.orderInfoDao.updateByPrimaryKey(record);
    }

    public int deleteByExample(Criteria example) {
        return this.orderInfoDao.deleteByExample(example);
    }

    public int updateByExampleSelective(OrderInfo record, Criteria example) {
        return this.orderInfoDao.updateByExampleSelective(record, example);
    }

    public int updateByExample(OrderInfo record, Criteria example) {
        return this.orderInfoDao.updateByExample(record, example);
    }

    public int insert(OrderInfo record) {
        return this.orderInfoDao.insert(record);
    }

    public int insertSelective(OrderInfo record) {
        return this.orderInfoDao.insertSelective(record);
    }
}
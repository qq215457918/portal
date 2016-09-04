package com.portal.service.impl;

import com.portal.bean.Criteria;
import com.portal.bean.OrderInfo;
import com.portal.dao.OrderInfoDao;
import com.portal.service.OrderInfoService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderInfoServiceImpl implements OrderInfoService {
    @Autowired
    private OrderInfoDao orderInfoDao;

    private static final Logger logger = LoggerFactory.getLogger(OrderInfoServiceImpl.class);

    /**
     * 查询商品订单 by customerId
     * by meng.yue
     * @return
     */
    public List<OrderInfo> queryGoodsInfo(String customerId){
    	return null;
    } 
    
    /**
     * 撤销定金订单
     * @param customerId
     * @return
     */
    public List<OrderInfo> queryRevokeDepositInfo(String customerId){
    	return null;
    } 
    
    /**
     * 缴纳定金订单
     * @param customerId
     * @return
     */
    public List<OrderInfo> queryPayDepositInfo(String customerId){
    	return null;
    } 
    
    /**
     * 退换货订单
     * @param customerId
     * @return
     */
    public List<OrderInfo> queryReturnGoodsInfo(String customerId){
    	return null;
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
package com.portal.service.impl;

import com.portal.bean.Criteria;
import com.portal.bean.OrderFundSettlement;
import com.portal.dao.OrderFundSettlementDao;
import com.portal.service.OrderFundSettlementService;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderFundSettlementServiceImpl implements OrderFundSettlementService {
    @Autowired
    private OrderFundSettlementDao orderFundSettlementDao;

    private static final Logger logger = LoggerFactory.getLogger(OrderFundSettlementServiceImpl.class);

    public int countByExample(Criteria example) {
        int count = this.orderFundSettlementDao.countByExample(example);
        logger.debug("count: {}", count);
        return count;
    }

    public OrderFundSettlement selectByPrimaryKey(String id) {
        return this.orderFundSettlementDao.selectByPrimaryKey(id);
    }

    public List<OrderFundSettlement> selectByExample(Criteria example) {
        return this.orderFundSettlementDao.selectByExample(example);
    }

    public int deleteByPrimaryKey(String id) {
        return this.orderFundSettlementDao.deleteByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(OrderFundSettlement record) {
        return this.orderFundSettlementDao.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(OrderFundSettlement record) {
        return this.orderFundSettlementDao.updateByPrimaryKey(record);
    }

    public int deleteByExample(Criteria example) {
        return this.orderFundSettlementDao.deleteByExample(example);
    }

    public int updateByExampleSelective(OrderFundSettlement record, Criteria example) {
        return this.orderFundSettlementDao.updateByExampleSelective(record, example);
    }

    public int updateByExample(OrderFundSettlement record, Criteria example) {
        return this.orderFundSettlementDao.updateByExample(record, example);
    }

    public int insert(OrderFundSettlement record) {
        return this.orderFundSettlementDao.insert(record);
    }

    public int insertSelective(OrderFundSettlement record) {
        return this.orderFundSettlementDao.insertSelective(record);
    }
    
    /**
     * @Title: getOrderFundInfo 
     * @Description: 获取收款信息和商品信息
     * @param orderId
     * @return 
     * @return List<Map<String,Object>>
     * @throws
     */
    @Override
    public List<Map<String, String>> getOrderFundInfo(String orderId) {
    	return orderFundSettlementDao.getOrderFundInfo(orderId);
    }
}
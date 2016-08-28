package com.portal.service.impl;

import com.portal.bean.Criteria;
import com.portal.bean.OrderDetailInfo;
import com.portal.dao.OrderDetailInfoDao;
import com.portal.service.OrderDetailInfoService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailInfoServiceImpl implements OrderDetailInfoService {
    @Autowired
    private OrderDetailInfoDao orderDetailInfoDao;

    private static final Logger logger = LoggerFactory.getLogger(OrderDetailInfoServiceImpl.class);

    public int countByExample(Criteria example) {
        int count = this.orderDetailInfoDao.countByExample(example);
        logger.debug("count: {}", count);
        return count;
    }

    public OrderDetailInfo selectByPrimaryKey(String id) {
        return this.orderDetailInfoDao.selectByPrimaryKey(id);
    }

    public List<OrderDetailInfo> selectByExample(Criteria example) {
        return this.orderDetailInfoDao.selectByExample(example);
    }

    public int deleteByPrimaryKey(String id) {
        return this.orderDetailInfoDao.deleteByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(OrderDetailInfo record) {
        return this.orderDetailInfoDao.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(OrderDetailInfo record) {
        return this.orderDetailInfoDao.updateByPrimaryKey(record);
    }

    public int deleteByExample(Criteria example) {
        return this.orderDetailInfoDao.deleteByExample(example);
    }

    public int updateByExampleSelective(OrderDetailInfo record, Criteria example) {
        return this.orderDetailInfoDao.updateByExampleSelective(record, example);
    }

    public int updateByExample(OrderDetailInfo record, Criteria example) {
        return this.orderDetailInfoDao.updateByExample(record, example);
    }

    public int insert(OrderDetailInfo record) {
        return this.orderDetailInfoDao.insert(record);
    }

    public int insertSelective(OrderDetailInfo record) {
        return this.orderDetailInfoDao.insertSelective(record);
    }
}
package com.portal.service.impl;

import com.portal.bean.Criteria;
import com.portal.bean.CustomerInfo;
import com.portal.dao.CustomerInfoDao;
import com.portal.dao.extra.CustomerInfoExtraDao;
import com.portal.service.CustomerInfoService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerInfoServiceImpl implements CustomerInfoService {
    @Autowired
    private CustomerInfoDao customerInfoDao;
    
    @Autowired
    private CustomerInfoExtraDao customerInfoExtraDao;

    private static final Logger logger = LoggerFactory.getLogger(CustomerInfoServiceImpl.class);

  /**
   * 通过电话号码查询客户信息
   */
    
    public CustomerInfo selectByPhone(String phone) {
        return this.customerInfoExtraDao.selectByPhone(phone);
    }
    
    public int countByExample(Criteria example) {
        int count = this.customerInfoDao.countByExample(example);
        logger.debug("count: {}", count);
        return count;
    }

    public CustomerInfo selectByPrimaryKey(String id) {
        return this.customerInfoDao.selectByPrimaryKey(id);
    }

    public List<CustomerInfo> selectByExample(Criteria example) {
        return this.customerInfoDao.selectByExample(example);
    }

    public int deleteByPrimaryKey(String id) {
        return this.customerInfoDao.deleteByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(CustomerInfo record) {
        return this.customerInfoDao.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(CustomerInfo record) {
        return this.customerInfoDao.updateByPrimaryKey(record);
    }

    public int deleteByExample(Criteria example) {
        return this.customerInfoDao.deleteByExample(example);
    }

    public int updateByExampleSelective(CustomerInfo record, Criteria example) {
        return this.customerInfoDao.updateByExampleSelective(record, example);
    }

    public int updateByExample(CustomerInfo record, Criteria example) {
        return this.customerInfoDao.updateByExample(record, example);
    }

    public int insert(CustomerInfo record) {
        return this.customerInfoDao.insert(record);
    }

    public int insertSelective(CustomerInfo record) {
        return this.customerInfoDao.insertSelective(record);
    }
}
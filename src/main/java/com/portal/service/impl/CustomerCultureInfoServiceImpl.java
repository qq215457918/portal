package com.portal.service.impl;

import com.portal.bean.Criteria;
import com.portal.bean.CustomerCultureInfo;
import com.portal.dao.CustomerCultureInfoDao;
import com.portal.service.CustomerCultureInfoService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerCultureInfoServiceImpl implements CustomerCultureInfoService {
    @Autowired
    private CustomerCultureInfoDao customerCultureInfoDao;

    private static final Logger logger = LoggerFactory.getLogger(CustomerCultureInfoServiceImpl.class);

    public int countByExample(Criteria example) {
        int count = this.customerCultureInfoDao.countByExample(example);
        logger.debug("count: {}", count);
        return count;
    }

    public CustomerCultureInfo selectByPrimaryKey(String id) {
        return this.customerCultureInfoDao.selectByPrimaryKey(id);
    }

    public List<CustomerCultureInfo> selectByExample(Criteria example) {
        return this.customerCultureInfoDao.selectByExample(example);
    }

    public int deleteByPrimaryKey(String id) {
        return this.customerCultureInfoDao.deleteByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(CustomerCultureInfo record) {
        return this.customerCultureInfoDao.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(CustomerCultureInfo record) {
        return this.customerCultureInfoDao.updateByPrimaryKey(record);
    }

    public int deleteByExample(Criteria example) {
        return this.customerCultureInfoDao.deleteByExample(example);
    }

    public int updateByExampleSelective(CustomerCultureInfo record, Criteria example) {
        return this.customerCultureInfoDao.updateByExampleSelective(record, example);
    }

    public int updateByExample(CustomerCultureInfo record, Criteria example) {
        return this.customerCultureInfoDao.updateByExample(record, example);
    }

    public int insert(CustomerCultureInfo record) {
        return this.customerCultureInfoDao.insert(record);
    }

    public int insertSelective(CustomerCultureInfo record) {
        return this.customerCultureInfoDao.insertSelective(record);
    }
}
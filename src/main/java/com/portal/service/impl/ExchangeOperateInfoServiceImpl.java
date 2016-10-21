package com.portal.service.impl;

import com.portal.bean.Criteria;
import com.portal.bean.ExchangeOperateInfo;
import com.portal.dao.ExchangeOperateInfoDao;
import com.portal.service.ExchangeOperateInfoService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExchangeOperateInfoServiceImpl implements ExchangeOperateInfoService {
    @Autowired
    private ExchangeOperateInfoDao exchangeOperateInfoDao;

    private static final Logger logger = LoggerFactory.getLogger(ExchangeOperateInfoServiceImpl.class);

    public int countByExample(Criteria example) {
        int count = this.exchangeOperateInfoDao.countByExample(example);
        logger.debug("count: {}", count);
        return count;
    }

    public ExchangeOperateInfo selectByPrimaryKey(String id) {
        return this.exchangeOperateInfoDao.selectByPrimaryKey(id);
    }

    public List<ExchangeOperateInfo> selectByExample(Criteria example) {
        return this.exchangeOperateInfoDao.selectByExample(example);
    }

    public int deleteByPrimaryKey(String id) {
        return this.exchangeOperateInfoDao.deleteByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(ExchangeOperateInfo record) {
        return this.exchangeOperateInfoDao.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(ExchangeOperateInfo record) {
        return this.exchangeOperateInfoDao.updateByPrimaryKey(record);
    }

    public int deleteByExample(Criteria example) {
        return this.exchangeOperateInfoDao.deleteByExample(example);
    }

    public int updateByExampleSelective(ExchangeOperateInfo record, Criteria example) {
        return this.exchangeOperateInfoDao.updateByExampleSelective(record, example);
    }

    public int updateByExample(ExchangeOperateInfo record, Criteria example) {
        return this.exchangeOperateInfoDao.updateByExample(record, example);
    }

    public int insert(ExchangeOperateInfo record) {
        return this.exchangeOperateInfoDao.insert(record);
    }

    public int insertSelective(ExchangeOperateInfo record) {
        return this.exchangeOperateInfoDao.insertSelective(record);
    }
}
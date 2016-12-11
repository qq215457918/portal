package com.portal.service.impl;

import com.portal.bean.Criteria;
import com.portal.bean.SellDailyDetail;
import com.portal.dao.SellDailyDetailDao;
import com.portal.service.SellDailyDetailService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellDailyDetailServiceImpl implements SellDailyDetailService {
    @Autowired
    private SellDailyDetailDao sellDailyDetailDao;

    private static final Logger logger = LoggerFactory.getLogger(SellDailyDetailServiceImpl.class);

    public int countByExample(Criteria example) {
        int count = this.sellDailyDetailDao.countByExample(example);
        logger.debug("count: {}", count);
        return count;
    }

    public SellDailyDetail selectByPrimaryKey(String id) {
        return this.sellDailyDetailDao.selectByPrimaryKey(id);
    }

    public List<SellDailyDetail> selectByExample(Criteria example) {
        return this.sellDailyDetailDao.selectByExample(example);
    }

    public int deleteByPrimaryKey(String id) {
        return this.sellDailyDetailDao.deleteByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(SellDailyDetail record) {
        return this.sellDailyDetailDao.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(SellDailyDetail record) {
        return this.sellDailyDetailDao.updateByPrimaryKey(record);
    }

    public int deleteByExample(Criteria example) {
        return this.sellDailyDetailDao.deleteByExample(example);
    }

    public int updateByExampleSelective(SellDailyDetail record, Criteria example) {
        return this.sellDailyDetailDao.updateByExampleSelective(record, example);
    }

    public int updateByExample(SellDailyDetail record, Criteria example) {
        return this.sellDailyDetailDao.updateByExample(record, example);
    }

    public int insert(SellDailyDetail record) {
        return this.sellDailyDetailDao.insert(record);
    }

    public int insertSelective(SellDailyDetail record) {
        return this.sellDailyDetailDao.insertSelective(record);
    }
}
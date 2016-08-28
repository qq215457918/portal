package com.portal.service.impl;

import com.portal.bean.Criteria;
import com.portal.bean.ReportGood;
import com.portal.dao.ReportGoodDao;
import com.portal.service.ReportGoodService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportGoodServiceImpl implements ReportGoodService {
    @Autowired
    private ReportGoodDao reportGoodDao;

    private static final Logger logger = LoggerFactory.getLogger(ReportGoodServiceImpl.class);

    public int countByExample(Criteria example) {
        int count = this.reportGoodDao.countByExample(example);
        logger.debug("count: {}", count);
        return count;
    }

    public ReportGood selectByPrimaryKey(String id) {
        return this.reportGoodDao.selectByPrimaryKey(id);
    }

    public List<ReportGood> selectByExample(Criteria example) {
        return this.reportGoodDao.selectByExample(example);
    }

    public int deleteByPrimaryKey(String id) {
        return this.reportGoodDao.deleteByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(ReportGood record) {
        return this.reportGoodDao.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(ReportGood record) {
        return this.reportGoodDao.updateByPrimaryKey(record);
    }

    public int deleteByExample(Criteria example) {
        return this.reportGoodDao.deleteByExample(example);
    }

    public int updateByExampleSelective(ReportGood record, Criteria example) {
        return this.reportGoodDao.updateByExampleSelective(record, example);
    }

    public int updateByExample(ReportGood record, Criteria example) {
        return this.reportGoodDao.updateByExample(record, example);
    }

    public int insert(ReportGood record) {
        return this.reportGoodDao.insert(record);
    }

    public int insertSelective(ReportGood record) {
        return this.reportGoodDao.insertSelective(record);
    }
}
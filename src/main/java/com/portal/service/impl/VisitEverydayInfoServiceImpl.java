package com.portal.service.impl;

import com.portal.bean.Criteria;
import com.portal.bean.VisitEverydayInfo;
import com.portal.dao.VisitEverydayInfoDao;
import com.portal.service.VisitEverydayInfoService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VisitEverydayInfoServiceImpl implements VisitEverydayInfoService {
    @Autowired
    private VisitEverydayInfoDao visitEverydayInfoDao;

    private static final Logger logger = LoggerFactory.getLogger(VisitEverydayInfoServiceImpl.class);

    public int countByExample(Criteria example) {
        int count = this.visitEverydayInfoDao.countByExample(example);
        logger.debug("count: {}", count);
        return count;
    }

    public VisitEverydayInfo selectByPrimaryKey(String id) {
        return this.visitEverydayInfoDao.selectByPrimaryKey(id);
    }

    public List<VisitEverydayInfo> selectByExample(Criteria example) {
        return this.visitEverydayInfoDao.selectByExample(example);
    }

    public int deleteByPrimaryKey(String id) {
        return this.visitEverydayInfoDao.deleteByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(VisitEverydayInfo record) {
        return this.visitEverydayInfoDao.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(VisitEverydayInfo record) {
        return this.visitEverydayInfoDao.updateByPrimaryKey(record);
    }

    public int deleteByExample(Criteria example) {
        return this.visitEverydayInfoDao.deleteByExample(example);
    }

    public int updateByExampleSelective(VisitEverydayInfo record, Criteria example) {
        return this.visitEverydayInfoDao.updateByExampleSelective(record, example);
    }

    public int updateByExample(VisitEverydayInfo record, Criteria example) {
        return this.visitEverydayInfoDao.updateByExample(record, example);
    }

    public int insert(VisitEverydayInfo record) {
        return this.visitEverydayInfoDao.insert(record);
    }

    public int insertSelective(VisitEverydayInfo record) {
        return this.visitEverydayInfoDao.insertSelective(record);
    }
}
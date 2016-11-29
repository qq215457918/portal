package com.portal.service.impl;

import com.portal.bean.Criteria;
import com.portal.bean.CultureInfo;
import com.portal.dao.CultureInfoDao;
import com.portal.service.CultureInfoService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CultureInfoServiceImpl implements CultureInfoService {
    @Autowired
    private CultureInfoDao cultureInfoDao;

    private static final Logger logger = LoggerFactory.getLogger(CultureInfoServiceImpl.class);

    public int countByExample(Criteria example) {
        int count = this.cultureInfoDao.countByExample(example);
        logger.debug("count: {}", count);
        return count;
    }

    public CultureInfo selectByPrimaryKey(String id) {
        return this.cultureInfoDao.selectByPrimaryKey(id);
    }

    public List<CultureInfo> selectByExample(Criteria example) {
        return this.cultureInfoDao.selectByExample(example);
    }

    public int deleteByPrimaryKey(String id) {
        return this.cultureInfoDao.deleteByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(CultureInfo record) {
        return this.cultureInfoDao.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(CultureInfo record) {
        return this.cultureInfoDao.updateByPrimaryKey(record);
    }

    public int deleteByExample(Criteria example) {
        return this.cultureInfoDao.deleteByExample(example);
    }

    public int updateByExampleSelective(CultureInfo record, Criteria example) {
        return this.cultureInfoDao.updateByExampleSelective(record, example);
    }

    public int updateByExample(CultureInfo record, Criteria example) {
        return this.cultureInfoDao.updateByExample(record, example);
    }

    public int insert(CultureInfo record) {
        return this.cultureInfoDao.insert(record);
    }

    public int insertSelective(CultureInfo record) {
        return this.cultureInfoDao.insertSelective(record);
    }
}
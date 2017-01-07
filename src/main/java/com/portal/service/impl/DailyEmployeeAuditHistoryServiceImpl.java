package com.portal.service.impl;

import com.portal.bean.Criteria;
import com.portal.bean.DailyEmployeeAuditHistory;
import com.portal.dao.DailyEmployeeAuditHistoryDao;
import com.portal.service.DailyEmployeeAuditHistoryService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DailyEmployeeAuditHistoryServiceImpl implements DailyEmployeeAuditHistoryService {
    @Autowired
    private DailyEmployeeAuditHistoryDao dailyEmployeeAuditHistoryDao;

    private static final Logger logger = LoggerFactory.getLogger(DailyEmployeeAuditHistoryServiceImpl.class);

    public int countByExample(Criteria example) {
        int count = this.dailyEmployeeAuditHistoryDao.countByExample(example);
        logger.debug("count: {}", count);
        return count;
    }

    public DailyEmployeeAuditHistory selectByPrimaryKey(String id) {
        return this.dailyEmployeeAuditHistoryDao.selectByPrimaryKey(id);
    }

    public List<DailyEmployeeAuditHistory> selectByExample(Criteria example) {
        return this.dailyEmployeeAuditHistoryDao.selectByExample(example);
    }

    public int deleteByPrimaryKey(String id) {
        return this.dailyEmployeeAuditHistoryDao.deleteByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(DailyEmployeeAuditHistory record) {
        return this.dailyEmployeeAuditHistoryDao.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(DailyEmployeeAuditHistory record) {
        return this.dailyEmployeeAuditHistoryDao.updateByPrimaryKey(record);
    }

    public int deleteByExample(Criteria example) {
        return this.dailyEmployeeAuditHistoryDao.deleteByExample(example);
    }

    public int updateByExampleSelective(DailyEmployeeAuditHistory record, Criteria example) {
        return this.dailyEmployeeAuditHistoryDao.updateByExampleSelective(record, example);
    }

    public int updateByExample(DailyEmployeeAuditHistory record, Criteria example) {
        return this.dailyEmployeeAuditHistoryDao.updateByExample(record, example);
    }

    public int insert(DailyEmployeeAuditHistory record) {
        return this.dailyEmployeeAuditHistoryDao.insert(record);
    }

    public int insertSelective(DailyEmployeeAuditHistory record) {
        return this.dailyEmployeeAuditHistoryDao.insertSelective(record);
    }
}
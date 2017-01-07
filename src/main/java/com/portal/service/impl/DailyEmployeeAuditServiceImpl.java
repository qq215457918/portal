package com.portal.service.impl;

import com.portal.bean.Criteria;
import com.portal.bean.DailyEmployeeAudit;
import com.portal.dao.DailyEmployeeAuditDao;
import com.portal.service.DailyEmployeeAuditService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DailyEmployeeAuditServiceImpl implements DailyEmployeeAuditService {
    @Autowired
    private DailyEmployeeAuditDao dailyEmployeeAuditDao;

    private static final Logger logger = LoggerFactory.getLogger(DailyEmployeeAuditServiceImpl.class);

    public int countByExample(Criteria example) {
        int count = this.dailyEmployeeAuditDao.countByExample(example);
        logger.debug("count: {}", count);
        return count;
    }

    public DailyEmployeeAudit selectByPrimaryKey(String id) {
        return this.dailyEmployeeAuditDao.selectByPrimaryKey(id);
    }

    public List<DailyEmployeeAudit> selectByExample(Criteria example) {
        return this.dailyEmployeeAuditDao.selectByExample(example);
    }

    public int deleteByPrimaryKey(String id) {
        return this.dailyEmployeeAuditDao.deleteByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(DailyEmployeeAudit record) {
        return this.dailyEmployeeAuditDao.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(DailyEmployeeAudit record) {
        return this.dailyEmployeeAuditDao.updateByPrimaryKey(record);
    }

    public int deleteByExample(Criteria example) {
        return this.dailyEmployeeAuditDao.deleteByExample(example);
    }

    public int updateByExampleSelective(DailyEmployeeAudit record, Criteria example) {
        return this.dailyEmployeeAuditDao.updateByExampleSelective(record, example);
    }

    public int updateByExample(DailyEmployeeAudit record, Criteria example) {
        return this.dailyEmployeeAuditDao.updateByExample(record, example);
    }

    public int insert(DailyEmployeeAudit record) {
        return this.dailyEmployeeAuditDao.insert(record);
    }

    public int insertSelective(DailyEmployeeAudit record) {
        return this.dailyEmployeeAuditDao.insertSelective(record);
    }
}
package com.portal.service.impl;

import com.portal.bean.Criteria;
import com.portal.bean.EmployeeInfo;
import com.portal.dao.EmployeeInfoDao;
import com.portal.service.EmployeeInfoService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeInfoServiceImpl implements EmployeeInfoService {
    @Autowired
    private EmployeeInfoDao employeeInfoDao;

    private static final Logger logger = LoggerFactory.getLogger(EmployeeInfoServiceImpl.class);

    public int countByExample(Criteria example) {
        int count = this.employeeInfoDao.countByExample(example);
        logger.debug("count: {}", count);
        return count;
    }

    public EmployeeInfo selectByPrimaryKey(String id) {
        return this.employeeInfoDao.selectByPrimaryKey(id);
    }

    public List<EmployeeInfo> selectByExample(Criteria example) {
        return this.employeeInfoDao.selectByExample(example);
    }

    public int deleteByPrimaryKey(String id) {
        return this.employeeInfoDao.deleteByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(EmployeeInfo record) {
        return this.employeeInfoDao.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(EmployeeInfo record) {
        return this.employeeInfoDao.updateByPrimaryKey(record);
    }

    public int deleteByExample(Criteria example) {
        return this.employeeInfoDao.deleteByExample(example);
    }

    public int updateByExampleSelective(EmployeeInfo record, Criteria example) {
        return this.employeeInfoDao.updateByExampleSelective(record, example);
    }

    public int updateByExample(EmployeeInfo record, Criteria example) {
        return this.employeeInfoDao.updateByExample(record, example);
    }

    public int insert(EmployeeInfo record) {
        return this.employeeInfoDao.insert(record);
    }

    public int insertSelective(EmployeeInfo record) {
        return this.employeeInfoDao.insertSelective(record);
    }
}
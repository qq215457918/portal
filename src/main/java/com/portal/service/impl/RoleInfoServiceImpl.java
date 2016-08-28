package com.portal.service.impl;

import com.portal.bean.Criteria;
import com.portal.bean.RoleInfo;
import com.portal.dao.RoleInfoDao;
import com.portal.service.RoleInfoService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleInfoServiceImpl implements RoleInfoService {
    @Autowired
    private RoleInfoDao roleInfoDao;

    private static final Logger logger = LoggerFactory.getLogger(RoleInfoServiceImpl.class);

    public int countByExample(Criteria example) {
        int count = this.roleInfoDao.countByExample(example);
        logger.debug("count: {}", count);
        return count;
    }

    public RoleInfo selectByPrimaryKey(String id) {
        return this.roleInfoDao.selectByPrimaryKey(id);
    }

    public List<RoleInfo> selectByExample(Criteria example) {
        return this.roleInfoDao.selectByExample(example);
    }

    public int deleteByPrimaryKey(String id) {
        return this.roleInfoDao.deleteByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(RoleInfo record) {
        return this.roleInfoDao.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(RoleInfo record) {
        return this.roleInfoDao.updateByPrimaryKey(record);
    }

    public int deleteByExample(Criteria example) {
        return this.roleInfoDao.deleteByExample(example);
    }

    public int updateByExampleSelective(RoleInfo record, Criteria example) {
        return this.roleInfoDao.updateByExampleSelective(record, example);
    }

    public int updateByExample(RoleInfo record, Criteria example) {
        return this.roleInfoDao.updateByExample(record, example);
    }

    public int insert(RoleInfo record) {
        return this.roleInfoDao.insert(record);
    }

    public int insertSelective(RoleInfo record) {
        return this.roleInfoDao.insertSelective(record);
    }
}
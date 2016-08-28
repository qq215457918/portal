package com.portal.service.impl;

import com.portal.bean.Criteria;
import com.portal.bean.PermissionInfo;
import com.portal.dao.PermissionInfoDao;
import com.portal.service.PermissionInfoService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissionInfoServiceImpl implements PermissionInfoService {
    @Autowired
    private PermissionInfoDao permissionInfoDao;

    private static final Logger logger = LoggerFactory.getLogger(PermissionInfoServiceImpl.class);

    public int countByExample(Criteria example) {
        int count = this.permissionInfoDao.countByExample(example);
        logger.debug("count: {}", count);
        return count;
    }

    public PermissionInfo selectByPrimaryKey(String id) {
        return this.permissionInfoDao.selectByPrimaryKey(id);
    }

    public List<PermissionInfo> selectByExample(Criteria example) {
        return this.permissionInfoDao.selectByExample(example);
    }

    public int deleteByPrimaryKey(String id) {
        return this.permissionInfoDao.deleteByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(PermissionInfo record) {
        return this.permissionInfoDao.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(PermissionInfo record) {
        return this.permissionInfoDao.updateByPrimaryKey(record);
    }

    public int deleteByExample(Criteria example) {
        return this.permissionInfoDao.deleteByExample(example);
    }

    public int updateByExampleSelective(PermissionInfo record, Criteria example) {
        return this.permissionInfoDao.updateByExampleSelective(record, example);
    }

    public int updateByExample(PermissionInfo record, Criteria example) {
        return this.permissionInfoDao.updateByExample(record, example);
    }

    public int insert(PermissionInfo record) {
        return this.permissionInfoDao.insert(record);
    }

    public int insertSelective(PermissionInfo record) {
        return this.permissionInfoDao.insertSelective(record);
    }
}
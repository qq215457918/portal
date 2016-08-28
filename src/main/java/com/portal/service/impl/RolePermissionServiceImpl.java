package com.portal.service.impl;

import com.portal.bean.Criteria;
import com.portal.bean.RolePermission;
import com.portal.dao.RolePermissionDao;
import com.portal.service.RolePermissionService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RolePermissionServiceImpl implements RolePermissionService {
    @Autowired
    private RolePermissionDao rolePermissionDao;

    private static final Logger logger = LoggerFactory.getLogger(RolePermissionServiceImpl.class);

    public int countByExample(Criteria example) {
        int count = this.rolePermissionDao.countByExample(example);
        logger.debug("count: {}", count);
        return count;
    }

    public RolePermission selectByPrimaryKey(String id) {
        return this.rolePermissionDao.selectByPrimaryKey(id);
    }

    public List<RolePermission> selectByExample(Criteria example) {
        return this.rolePermissionDao.selectByExample(example);
    }

    public int deleteByPrimaryKey(String id) {
        return this.rolePermissionDao.deleteByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(RolePermission record) {
        return this.rolePermissionDao.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(RolePermission record) {
        return this.rolePermissionDao.updateByPrimaryKey(record);
    }

    public int deleteByExample(Criteria example) {
        return this.rolePermissionDao.deleteByExample(example);
    }

    public int updateByExampleSelective(RolePermission record, Criteria example) {
        return this.rolePermissionDao.updateByExampleSelective(record, example);
    }

    public int updateByExample(RolePermission record, Criteria example) {
        return this.rolePermissionDao.updateByExample(record, example);
    }

    public int insert(RolePermission record) {
        return this.rolePermissionDao.insert(record);
    }

    public int insertSelective(RolePermission record) {
        return this.rolePermissionDao.insertSelective(record);
    }
}
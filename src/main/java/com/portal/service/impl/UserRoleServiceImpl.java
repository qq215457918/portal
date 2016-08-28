package com.portal.service.impl;

import com.portal.bean.Criteria;
import com.portal.bean.UserRole;
import com.portal.dao.UserRoleDao;
import com.portal.service.UserRoleService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceImpl implements UserRoleService {
    @Autowired
    private UserRoleDao userRoleDao;

    private static final Logger logger = LoggerFactory.getLogger(UserRoleServiceImpl.class);

    public int countByExample(Criteria example) {
        int count = this.userRoleDao.countByExample(example);
        logger.debug("count: {}", count);
        return count;
    }

    public UserRole selectByPrimaryKey(String id) {
        return this.userRoleDao.selectByPrimaryKey(id);
    }

    public List<UserRole> selectByExample(Criteria example) {
        return this.userRoleDao.selectByExample(example);
    }

    public int deleteByPrimaryKey(String id) {
        return this.userRoleDao.deleteByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(UserRole record) {
        return this.userRoleDao.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(UserRole record) {
        return this.userRoleDao.updateByPrimaryKey(record);
    }

    public int deleteByExample(Criteria example) {
        return this.userRoleDao.deleteByExample(example);
    }

    public int updateByExampleSelective(UserRole record, Criteria example) {
        return this.userRoleDao.updateByExampleSelective(record, example);
    }

    public int updateByExample(UserRole record, Criteria example) {
        return this.userRoleDao.updateByExample(record, example);
    }

    public int insert(UserRole record) {
        return this.userRoleDao.insert(record);
    }

    public int insertSelective(UserRole record) {
        return this.userRoleDao.insertSelective(record);
    }
}
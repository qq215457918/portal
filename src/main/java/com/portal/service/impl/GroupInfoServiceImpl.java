package com.portal.service.impl;

import com.portal.bean.Criteria;
import com.portal.bean.GroupInfo;
import com.portal.dao.GroupInfoDao;
import com.portal.service.GroupInfoService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupInfoServiceImpl implements GroupInfoService {
    @Autowired
    private GroupInfoDao groupInfoDao;

    private static final Logger logger = LoggerFactory.getLogger(GroupInfoServiceImpl.class);

    public int countByExample(Criteria example) {
        int count = this.groupInfoDao.countByExample(example);
        logger.debug("count: {}", count);
        return count;
    }

    public GroupInfo selectByPrimaryKey(String id) {
        return this.groupInfoDao.selectByPrimaryKey(id);
    }

    public List<GroupInfo> selectByExample(Criteria example) {
        return this.groupInfoDao.selectByExample(example);
    }

    public int deleteByPrimaryKey(String id) {
        return this.groupInfoDao.deleteByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(GroupInfo record) {
        return this.groupInfoDao.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(GroupInfo record) {
        return this.groupInfoDao.updateByPrimaryKey(record);
    }

    public int deleteByExample(Criteria example) {
        return this.groupInfoDao.deleteByExample(example);
    }

    public int updateByExampleSelective(GroupInfo record, Criteria example) {
        return this.groupInfoDao.updateByExampleSelective(record, example);
    }

    public int updateByExample(GroupInfo record, Criteria example) {
        return this.groupInfoDao.updateByExample(record, example);
    }

    public int insert(GroupInfo record) {
        return this.groupInfoDao.insert(record);
    }

    public int insertSelective(GroupInfo record) {
        return this.groupInfoDao.insertSelective(record);
    }
}
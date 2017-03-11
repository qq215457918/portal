package com.portal.service.impl;

import com.portal.bean.Criteria;
import com.portal.bean.GroupInfo;
import com.portal.dao.GroupInfoDao;
import com.portal.dao.extra.GroupInfoExtraDao;
import com.portal.service.GroupInfoService;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupInfoServiceImpl implements GroupInfoService {
    @Autowired
    private GroupInfoDao groupInfoDao;

    @Autowired
    private GroupInfoExtraDao groupInfoExtraDao;

    // 公共查询条件类
    Criteria criteria = new Criteria();

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

    public List<GroupInfo> getAllCompany() {
        return groupInfoExtraDao.getAllCompany();
    }

    public int addGroupInfo(GroupInfo record) {
        return groupInfoExtraDao.addGroupInfo(record);
    }

    public int deleteGroupInfo(String id) {
        int count = 0;
        count = deleteByPrimaryKey(id);
        criteria.clear();
        criteria.put("parentsId", id);
        List<GroupInfo> groupList = selectByExample(criteria);
        if (CollectionUtils.isNotEmpty(groupList)) {
            for (GroupInfo groupInfo : groupList) {
                count += deleteByPrimaryKey(groupInfo.getId());
            }
        }
        return count;
    }
}

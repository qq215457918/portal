package com.portal.service;

import com.portal.bean.Criteria;
import com.portal.bean.RoleInfo;
import java.util.List;

public interface RoleInfoService {
    int countByExample(Criteria example);

    RoleInfo selectByPrimaryKey(String id);

    List<RoleInfo> selectByExample(Criteria example);

    int deleteByPrimaryKey(String id);

    int updateByPrimaryKeySelective(RoleInfo record);

    int updateByPrimaryKey(RoleInfo record);

    int deleteByExample(Criteria example);

    int updateByExampleSelective(RoleInfo record, Criteria example);

    int updateByExample(RoleInfo record, Criteria example);

    int insert(RoleInfo record);

    int insertSelective(RoleInfo record);
}
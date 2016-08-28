package com.portal.service;

import com.portal.bean.Criteria;
import com.portal.bean.UserRole;
import java.util.List;

public interface UserRoleService {
    int countByExample(Criteria example);

    UserRole selectByPrimaryKey(String id);

    List<UserRole> selectByExample(Criteria example);

    int deleteByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserRole record);

    int updateByPrimaryKey(UserRole record);

    int deleteByExample(Criteria example);

    int updateByExampleSelective(UserRole record, Criteria example);

    int updateByExample(UserRole record, Criteria example);

    int insert(UserRole record);

    int insertSelective(UserRole record);
}
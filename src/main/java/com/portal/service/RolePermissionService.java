package com.portal.service;

import com.portal.bean.Criteria;
import com.portal.bean.RolePermission;
import java.util.List;

public interface RolePermissionService {
    int countByExample(Criteria example);

    RolePermission selectByPrimaryKey(String id);

    List<RolePermission> selectByExample(Criteria example);

    int deleteByPrimaryKey(String id);

    int updateByPrimaryKeySelective(RolePermission record);

    int updateByPrimaryKey(RolePermission record);

    int deleteByExample(Criteria example);

    int updateByExampleSelective(RolePermission record, Criteria example);

    int updateByExample(RolePermission record, Criteria example);

    int insert(RolePermission record);

    int insertSelective(RolePermission record);
}
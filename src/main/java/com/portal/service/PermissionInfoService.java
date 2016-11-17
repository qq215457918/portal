package com.portal.service;

import com.portal.bean.Criteria;
import com.portal.bean.PermissionInfo;
import java.util.List;

public interface PermissionInfoService {

    /**
     * 通过roleId 查询用户信息
     * @param userId
     * @return
     */
    List<PermissionInfo> selectByRoleId(String roleId);

    int countByExample(Criteria example);

    PermissionInfo selectByPrimaryKey(String id);

    List<PermissionInfo> selectByExample(Criteria example);

    int deleteByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PermissionInfo record);

    int updateByPrimaryKey(PermissionInfo record);

    int deleteByExample(Criteria example);

    int updateByExampleSelective(PermissionInfo record, Criteria example);

    int updateByExample(PermissionInfo record, Criteria example);

    int insert(PermissionInfo record);

    int insertSelective(PermissionInfo record);
}

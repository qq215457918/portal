package com.portal.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portal.bean.Criteria;
import com.portal.bean.EmployeeInfo;
import com.portal.bean.Role;
import com.portal.bean.result.EmployeeInfoForm;
import com.portal.dao.EmployeeInfoDao;
import com.portal.dao.extra.EmployeeInfoExtraDao;
import com.portal.dao.impl.RoleMapper;
import com.portal.service.ResourceService;
import com.portal.service.RoleService;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleDao;
    @Autowired
    private ResourceService resourceService;
    
    @Autowired
    private EmployeeInfoExtraDao employeeExtDao;
    
    @Autowired
    private EmployeeInfoDao employeeDao;
    
    Criteria criteria = new Criteria();

    public int insertRole(Role role) {
        String resourcesIds = role.getResourceIdsStr();
        if(resourcesIds.indexOf(',') != 0) {
            resourcesIds = "," + resourcesIds;
        }
        return roleDao.insertRole(role, resourcesIds);
    }

    public int updateRole(Role role) {
        String resourcesIds = role.getResourceIdsStr();
        if(resourcesIds.indexOf(',') != 0) {
            resourcesIds = "," + resourcesIds;
        }
        return roleDao.updateRole(role, resourcesIds);
    }

    public void deleteRole(Long roleId) {
        // 判断员工是否关联此角色, 如果关联则移除关联关系
        int count = roleDao.deleteRole(roleId);
        if(count > 0) {
            criteria.clear();
            criteria.put("roleIds", "," + roleId + ",");
            List<EmployeeInfoForm> list = employeeExtDao.selectByExamples(criteria);
            if(CollectionUtils.isNotEmpty(list)) {
                String roleIds = "";
                EmployeeInfo employee = null;
                for (EmployeeInfoForm employeeInfoForm : list) {
                    employee = new EmployeeInfo();
                    BeanUtils.copyProperties(employeeInfoForm, employee);
                    roleIds = employeeInfoForm.getRoleIds();
                    roleIds = roleIds.substring(1, roleIds.length());
                    String[] ids = roleIds.split(",");
                    if(ids.length <= 1) {
                        employee.setRoleIds("");
                    }else {
                        roleIds = roleIds.replaceAll(roleId + ",", "");
                        employee.setRoleIds("," + roleIds);
                    }
                    employeeDao.updateByPrimaryKey(employee);
                }
            }
        }
    }

    @Override
    public Role findOne(Long roleId) {
        List<Role> list = roleDao.findOne(roleId);
        if(CollectionUtils.isNotEmpty(list)) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }

    @Override
    public Set<String> findRoles(Long... roleIds) {
        Set<String> roles = new HashSet<String>();
        for (Long roleId : roleIds) {
            Role role = findOne(roleId);
            if (role != null) {
                roles.add(role.getRole());
            }
        }
        return roles;
    }

    @Override
    public Set<String> findPermissions(Long[] roleIds) {
        Set<Long> resourceIds = new HashSet<Long>();
        for (Long roleId : roleIds) {
            Role role = findOne(roleId);
            if (role != null) {
                resourceIds.addAll(role.getResourceIds());
            }
        }
        return resourceService.findPermissions(resourceIds);
    }
}

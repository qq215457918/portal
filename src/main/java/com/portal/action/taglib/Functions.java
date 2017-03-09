package com.portal.action.taglib;

import java.util.Collection;

import org.springframework.util.CollectionUtils;

import com.portal.bean.GroupInfo;
import com.portal.bean.Resource;
import com.portal.bean.Role;
import com.portal.common.util.SpringUtils;
import com.portal.service.GroupInfoService;
import com.portal.service.ResourceService;
import com.portal.service.RoleService;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-2-15
 * <p>Version: 1.0
 */
public class Functions {

    public static boolean in(Iterable iterable, Object element) {
        if (iterable == null) {
            return false;
        }
        return CollectionUtils.contains(iterable.iterator(), element);
    }

    public static String organizationName(Long organizationId) {
        GroupInfo group = groupService.selectByPrimaryKey(organizationId.toString());
        if (group == null) {
            return "";
        }
        return group.getName();
    }

    public static String organizationNames(Collection<Long> organizationIds) {
        if (CollectionUtils.isEmpty(organizationIds)) {
            return "";
        }

        StringBuilder s = new StringBuilder();
        for (Long organizationId : organizationIds) {
            GroupInfo group = groupService.selectByPrimaryKey(organizationId.toString());
            if (group == null) {
                return "";
            }
            s.append(group.getName());
            s.append(",");  
        }

        if (s.length() > 0) {
            s.deleteCharAt(s.length() - 1);
        }

        return s.toString();
    }

    public static String roleName(Long roleId) {
        Role role = getRoleService().findOne(roleId);
        if (role == null) {
            return "";
        }
        return role.getDescription();
    }

    public static String roleNames(Collection<Long> roleIds) {
        if (CollectionUtils.isEmpty(roleIds)) {
            return "";
        }

        StringBuilder s = new StringBuilder();
        for (Long roleId : roleIds) {
            Role role = getRoleService().findOne(roleId);
            if (role == null) {
                return "";
            }
            s.append(role.getDescription());
            s.append(",");
        }

        if (s.length() > 0) {
            s.deleteCharAt(s.length() - 1);
        }

        return s.toString();
    }

    public static String resourceName(Long resourceId) {
        Resource resource = getResourceService().findOne(resourceId);
        if (resource == null) {
            return "";
        }
        return resource.getName();
    }

    public static String resourceNames(Collection<Long> resourceIds) {
        if (CollectionUtils.isEmpty(resourceIds)) {
            return "";
        }

        StringBuilder s = new StringBuilder();
        for (Long resourceId : resourceIds) {
            Resource resource = getResourceService().findOne(resourceId);
            if (resource == null) {
                return "";
            }
            s.append(resource.getName());
            s.append(",");
        }

        if (s.length() > 0) {
            s.deleteCharAt(s.length() - 1);
        }

        return s.toString();
    }

    private static GroupInfoService groupService;
    private static RoleService roleService;
    private static ResourceService resourceService;

    public static GroupInfoService getGroupInfoService() {
        if (groupService == null) {
            groupService = SpringUtils.getBean(GroupInfoService.class);
        }
        return groupService;
    }

    public static RoleService getRoleService() {
        if (roleService == null) {
            roleService = SpringUtils.getBean(RoleService.class);
        }
        return roleService;
    }

    public static ResourceService getResourceService() {
        if (resourceService == null) {
            resourceService = SpringUtils.getBean(ResourceService.class);
        }
        return resourceService;
    }
}

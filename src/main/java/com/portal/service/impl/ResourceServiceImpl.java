package com.portal.service.impl;

import com.portal.bean.Resource;
import com.portal.bean.Role;
import com.portal.dao.impl.ResourceMapper;
import com.portal.dao.impl.RoleMapper;
import com.portal.service.ResourceService;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authz.permission.WildcardPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-2-14
 * <p>Version: 1.0
 */
@Service

public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceMapper resourceDao;
    
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public int insertResource(Resource resource) {
        return resourceDao.insertResource(resource);
    }

    @Override
    public int updateResource(Resource resource) {
        return resourceDao.updateResource(resource);
    }

    @Override
    public void deleteResource(Long resourceId) {
        int count = resourceDao.deleteResource(resourceId);
        if(count > 0) {
            List<Role> roleList = resourceDao.getPromissionCounts("," + resourceId + ",");
            if(CollectionUtils.isNotEmpty(roleList)) {
                for (Role role : roleList) {
                    String reles = role.getResourceIdsStr();
                    reles = reles.replaceAll("," + resourceId, "");
                    if(reles.indexOf(',') != 0) {
                        reles = "," + reles;
                    }
                    roleMapper.updateRole(role, reles);
                }
            }
            List<Resource> resources = resourceDao.getChildCounts(resourceId);
            if(CollectionUtils.isNotEmpty(roleList)) {
                for (Resource resource : resources) {
                    resourceDao.deleteResource(resource.getId());
                }
            }
        }
    }

    @Override
    public Resource findOne(Long resourceId) {
        return resourceDao.findOne(resourceId).get(0);
    }

    @Override
    public List<Resource> findAll() {
        return resourceDao.findAll();
    }

    @Override
    public Set<String> findPermissions(Set<Long> resourceIds) {
        Set<String> permissions = new HashSet<String>();
        for (Long resourceId : resourceIds) {
            Resource resource = findOne(resourceId);
            if (resource != null && !StringUtils.isEmpty(resource.getPermission())) {
                permissions.add(resource.getPermission());
            }
        }
        return permissions;
    }

    @Override
    public List<Resource> findMenus(Set<String> permissions) {
        List<Resource> allResources = findAll();
        List<Resource> menus = new ArrayList<Resource>();
        for (Resource resource : allResources) {
            if (resource.isRootNode()) {
                continue;
            }
            if (resource.getType() != Resource.ResourceType.menu) {
                continue;
            }
            if (!hasPermission(permissions, resource)) {
                continue;
            }
            menus.add(resource);
        }
        return menus;
    }

    private boolean hasPermission(Set<String> permissions, Resource resource) {
        if (StringUtils.isEmpty(resource.getPermission())) {
            return true;
        }
        for (String permission : permissions) {
            WildcardPermission p1 = new WildcardPermission(permission);
            WildcardPermission p2 = new WildcardPermission(resource.getPermission());
            if (p1.implies(p2) || p2.implies(p1)) {
                return true;
            }
        }
        return false;
    }

    public int checkHasChildAndPromi(Long resourcesId) {
        int counts = 0;
        List<Role> roleList = resourceDao.getPromissionCounts("," + resourcesId + ",");
        if(CollectionUtils.isEmpty(roleList)) {
            List<Resource> resources = resourceDao.getChildCounts(resourcesId);
            if(CollectionUtils.isNotEmpty(roleList)) {
                counts = resources.size();
            }
        }else {
            counts = roleList.size();
        }
        return counts;
    }
}

package com.portal.service;

import com.portal.bean.Resource;
import java.util.List;
import java.util.Set;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
public interface ResourceService {

    public int insertResource(Resource resource);

    public int updateResource(Resource resource);

    public void deleteResource(Long resourceId);

    Resource findOne(Long resourceId);

    List<Resource> findAll();

    /**
     * 得到资源对应的权限字符串
     * @param resourceIds
     * @return
     */
    Set<String> findPermissions(Set<Long> resourceIds);

    /**
     * 根据用户权限得到菜单
     * @param permissions
     * @return
     */
    List<Resource> findMenus(Set<String> permissions);
    
    // 删除菜单时判断是否存在子菜单和关联权限关系
    int checkHasChildAndPromi(Long resourcesId);
}

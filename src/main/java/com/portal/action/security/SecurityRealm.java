package com.portal.action.security;

import com.portal.bean.EmployeeInfo;
import com.portal.bean.PermissionInfo;
import com.portal.bean.RoleInfo;
import com.portal.service.EmployeeInfoService;
import com.portal.service.PermissionInfoService;
import com.portal.service.RoleInfoService;
import java.util.List;
import javax.annotation.Resource;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 用户身份验证,授权 Realm 组件
 * 
 * 自定义AuthorizingRealm 重写获取验证的方法类
 **/
@Component(value = "securityRealm")
public class SecurityRealm extends AuthorizingRealm {

    @Autowired
    private EmployeeInfoService employeeService;

    @Resource
    private RoleInfoService roleService;

    @Resource
    private PermissionInfoService permissionService;

    /**
     * 权限检查
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        String username = String.valueOf(principals.getPrimaryPrincipal());
        //通过loginName 获取用户信息和用户的roleInfo
        final EmployeeInfo employeeInfo = employeeService.selectByUserName(username);
        final List<RoleInfo> roleInfos = roleService.selectRoleByUserId(employeeInfo.getId());
        for (RoleInfo role : roleInfos) {
            // 添加角色信息
            System.err.println(role);
            authorizationInfo.addRole(role.getName());
            final List<PermissionInfo> permissions = permissionService.selectByRoleId(role.getId());
            for (PermissionInfo permission : permissions) {
                // 添加权限
                System.err.println(permission);
                authorizationInfo.addStringPermission(permission.getMenuUrl());
            }
        }
        return authorizationInfo;
    }

    /**
     * 登录验证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {
        String username = String.valueOf(token.getPrincipal());
        String password = new String((char[]) token.getCredentials());
        // 通过数据库进行验证
        final EmployeeInfo authentication = employeeService.authentication(username, password);
        if (authentication == null) {
            throw new AuthenticationException("用户名或密码错误.");
        }
        SimpleAuthenticationInfo authenticationInfo =
                new SimpleAuthenticationInfo(username, password, getName());
        return authenticationInfo;
    }

}

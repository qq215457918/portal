package com.portal.action.security;

import com.portal.bean.EmployeeInfo;
import com.portal.bean.PermissionInfo;
import com.portal.bean.RoleInfo;
import com.portal.service.EmployeeInfoService;
import com.portal.service.PermissionInfoService;
import com.portal.service.RoleInfoService;
import java.util.List;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
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

    @Autowired
    private RoleInfoService roleService;

    @Autowired
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
     * 修改为使用SimpleAuthenticationInfo 进行密码匹配。
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {
        String username = (String) token.getPrincipal();
        EmployeeInfo employeeInfo = employeeService.selectByUserName(username);

        if (employeeInfo == null) {
            throw new UnknownAccountException();//没找到帐号
        }

        //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                employeeInfo.getLoginName(), //用户名
                employeeInfo.getPassword(), //密码
                // ByteSource.Util.bytes(user.getCredentialsSalt()),//salt=username+salt
                getName()  //realm name
        );
        return authenticationInfo;

    }

}

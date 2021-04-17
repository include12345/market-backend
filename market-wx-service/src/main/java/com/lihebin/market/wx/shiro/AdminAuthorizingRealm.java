package com.lihebin.market.wx.shiro;

import com.lihebin.market.data.dao.AdminDao;
import com.lihebin.market.data.dao.PermissionDao;
import com.lihebin.market.data.dao.RoleDao;
import com.lihebin.market.data.model.AdminData;
import com.lihebin.market.data.model.PermissionData;
import com.lihebin.market.data.model.RoleData;
import com.lihebin.market.utils.MD5Util;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class AdminAuthorizingRealm extends AuthorizingRealm {

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PermissionDao permissionDao;

    @Autowired
    private AdminDao adminDao;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        if (principalCollection == null) {
            throw new AuthorizationException("PrincipalCollection method argument cannot be null.");
        }
        AdminData admin = (AdminData) getAvailablePrincipal(principalCollection);
        List<Long> roleIds = admin.getRoleIds();
        if (roleIds.isEmpty()) {
            throw new UnknownAccountException("找不到用户（" + admin.getUsername() + "）的角色信息");
        }
        List<RoleData> roleDataList = roleDao.listRoleByIds(roleIds, false);
        if (roleDataList.isEmpty()) {
            throw new UnknownAccountException("找不到用户（" + admin.getUsername() + "）的角色信息");
        }
        List<PermissionData> permissionDataList = permissionDao.listPremissionByRoleIds(roleIds, false);
        if (permissionDataList.isEmpty()) {
            throw new UnknownAccountException("找不到用户（" + admin.getUsername() + "）的权限信息");
        }
        Set<String> roleNames = roleDataList.stream().map(RoleData::getName).collect(Collectors.toSet());
        Set<String> permissionNames = permissionDataList.stream().map(PermissionData::getPermission).collect(Collectors.toSet());
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setRoles(roleNames);
        info.setStringPermissions(permissionNames);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        String username = upToken.getUsername();
        String password = new String(upToken.getPassword());

        if (StringUtils.isEmpty(username)) {
            throw new AccountException("用户名不能为空");
        }
        if (StringUtils.isEmpty(password)) {
            throw new AccountException("密码不能为空");
        }
        List<AdminData> adminDatas = adminDao.findAllByUsernameAndDeleted(username, false);
        if (adminDatas.size() == 0) {
            throw new UnknownAccountException("找不到用户（" + username + "）的帐号信息");
        }
        AdminData adminData = adminDatas.get(0);

        if (!adminData.getPassword().equals(MD5Util.getSign(password))) {
            throw new UnknownAccountException("找不到用户（" + username + "）的帐号信息");
        }
        return new SimpleAuthenticationInfo(adminData, password, getName());

    }
}

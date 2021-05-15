package com.lihebin.market.wx.service.impl;

import com.google.code.kaptcha.Producer;
import com.lihebin.market.data.dao.AdminDao;
import com.lihebin.market.data.dao.PermissionDao;
import com.lihebin.market.data.dao.RoleDao;
import com.lihebin.market.data.model.AdminData;
import com.lihebin.market.data.model.PermissionData;
import com.lihebin.market.data.model.RoleData;
import com.lihebin.market.enums.CodeEnum;
import com.lihebin.market.exception.BackendException;
import com.lihebin.market.utils.IpUtil;
import com.lihebin.market.wx.domain.AuthInfoResult;
import com.lihebin.market.wx.domain.AuthReq;
import com.lihebin.market.wx.domain.AuthResult;
import com.lihebin.market.wx.service.AuthService;
import com.lihebin.market.wx.shiro.Permission;
import com.lihebin.market.wx.shiro.PermissionUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by lihebin on 2021/4/14.
 */
@Service
@CacheConfig(cacheNames = "cacheManager")
public class AuthServiceImpl implements AuthService{

    @Autowired
    private Producer kaptchaProducer;

    @Autowired
    private AdminDao adminDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PermissionDao permissionDao;

    @Autowired
    private ApplicationContext context;

    private static final String basicPackage = "com.lihebin.market.wx.web.admin";




    @Override
    public String getKaptcha(HttpServletRequest request) {
        // 生成验证码
        String text = kaptchaProducer.createText();
        BufferedImage image = kaptchaProducer.createImage(text);
        HttpSession session = request.getSession();
        session.setAttribute("kaptcha", text);

        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ImageIO.write(image, "jpeg", outputStream);
            String base64 = Base64.getEncoder().encodeToString(outputStream.toByteArray());
            return "data:image/jpeg;base64," + base64.replaceAll("\r\n", "");
        } catch (IOException e) {
            throw new BackendException(CodeEnum.KAPTCHA_NULL);
        }
    }

    @Override
    public AuthResult adminLogin(AuthReq authReq, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String kaptcha = (String)session.getAttribute("kaptcha");
        if (!authReq.getCode().equalsIgnoreCase(kaptcha)) {
            throw new BackendException(CodeEnum.KAPTCHA_ERROR);
        }
        Subject currentUser = SecurityUtils.getSubject();
        try {
            currentUser.login(new UsernamePasswordToken(authReq.getUsername(), authReq.getPassword()));
        } catch (UnknownAccountException uae) {
            throw new BackendException(CodeEnum.FAIL_UN_LOGIN_USER_OR_PASS);

        } catch (LockedAccountException lae) {
            throw new BackendException(CodeEnum.FAIL_UN_LOGIN_CHECKED);
        } catch (AuthenticationException ae) {
            throw new BackendException(CodeEnum.FAIL_UN_LOGIN_NO_AUTH);
        }
        currentUser = SecurityUtils.getSubject();

        AdminData adminData = (AdminData) currentUser.getPrincipal();
        adminData.setLastLoginIp(IpUtil.getIpAddr(request));
        adminData.setLastLoginTime(System.currentTimeMillis());
        adminDao.save(adminData);
        AuthResult authResult = new AuthResult();
        authResult.setAvatar(adminData.getAvatar());
        authResult.setNickName(adminData.getUsername());
        authResult.setToken(currentUser.getSession().getId().toString());
        return authResult;
    }

    @Override
    public void adminLogout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
    }

    @Override
    public AuthInfoResult adminInfo() {
        Subject subject = SecurityUtils.getSubject();
        AdminData adminData = (AdminData)subject.getPrincipal();
        return getAuthInfoResult(adminData);
    }


    @Cacheable(key = "#adminData.username")
    public AuthInfoResult getAuthInfoResult(AdminData adminData) {
        AuthInfoResult authInfoResult = new AuthInfoResult();
        authInfoResult.setAvatar(adminData.getAvatar());
        authInfoResult.setName(adminData.getUsername());
        List<Long> roleIds = adminData.getRoleIds();
        Set<String> roleNames = roleDao.listRoleByIds(roleIds, false)
                .stream()
                .map(RoleData::getName)
                .collect(Collectors.toSet());
        authInfoResult.setRoles(roleNames);
        Set<String> permissionNames = permissionDao.listPremissionByRoleIds(roleIds, false).stream()
                .map(PermissionData::getPermission)
                .collect(Collectors.toSet());
        Set<String> apis = new HashSet<>();
        List<Permission> systemPermissions = PermissionUtil.listPermission(context, basicPackage);
        Map<String, String> systemPermissionsMap = new HashMap<>();
        for (Permission permission : systemPermissions) {
            String perm = permission.getRequiresPermissions().value()[0];
            String api = permission.getApi();
            systemPermissionsMap.put(perm, api);
        }
        for (String perm : permissionNames) {
            String api = systemPermissionsMap.get(perm);
            apis.add(api);
            if (perm.equals("*")) {
                apis.clear();
                apis.add("*");
                break;

            }
        }
        authInfoResult.setParams(apis);
        return authInfoResult;

    }


}

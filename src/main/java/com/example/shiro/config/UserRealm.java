package com.example.shiro.config;

import com.example.shiro.dto.UserDto;
import com.example.shiro.service.IUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description TODO
 * @Author Ricky
 * @Date 2022/3/23 23:59
 */
public class UserRealm extends AuthorizingRealm {

    @Resource
    private IUserService userService;
    //@Resource
    //private IPermissionService permissionService;

    /**
     * 执行授权逻辑
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行授权逻辑");
        /**
         * 给资源授权
         */
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        //添加授权字符串
        //simpleAuthorizationInfo.addStringPermission("user:add");

        //--------------------认证账号
        Subject subject = SecurityUtils.getSubject();
        UserDto subjectUser = (UserDto) subject.getPrincipal();
        UserDto user = userService.findById(subjectUser.getId());
        if (user == null) {
            //用户名不存在
            return null;
        }
        //-------------------开始授权
//        List<Permission> permissions =permissionService.getPermissionByUserId(user1.getId());
//        for (Permission per : permissions) {
//            simpleAuthorizationInfo.addStringPermission(per.getName());
//            System.out.println("拥有权限："+per.getName());
//        }
        return simpleAuthorizationInfo;
    }

    /**
     * 执行认证逻辑
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行认证逻辑");
        /**
         * 判断ShiroRealm逻辑UsernamePasswordToken是否正确
         */
        //1判断用户名
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        UserDto user = userService.findByUserName(usernamePasswordToken.getUsername());
        if (user == null) {
            //用户名不存在
            return null;
        }
        //判断密码是否正确
        return new SimpleAuthenticationInfo(user, user.getPassword(), "");
    }
}

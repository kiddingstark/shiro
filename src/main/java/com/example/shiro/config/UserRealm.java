package com.example.shiro.config;

import com.example.shiro.dto.JwtToken;
import com.example.shiro.dto.UserDto;
import com.example.shiro.service.IUserService;
import com.example.shiro.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @Description TODO
 * @Author Ricky
 * @Date 2022/3/23 23:59
 */
public class UserRealm extends AuthorizingRealm {

    @Resource
    private IUserService userService;

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
        //4、获取拦截到的token实体类并认证
        JwtToken jwtToken = (JwtToken) authenticationToken;
        Claims claims = JwtUtil.parseToken(jwtToken.getToken());
        if (Objects.isNull(claims)) {
            throw new AuthenticationException("无效的token");
        }

        Long userId = Long.parseLong(claims.getSubject());
        UserDto userDto = UserDto.builder()
                .id(userId)
                .build();
        //5、判断密码是否正确并放行
        return new SimpleAuthenticationInfo(userDto, jwtToken.getToken(), "");
    }

}

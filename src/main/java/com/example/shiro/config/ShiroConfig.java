package com.example.shiro.config;

import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Description TODO
 * @Author Ricky
 * @Date 2022/3/23 23:59
 */

@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //添加Shiro拦截器
        /**
         * Shiro 内置过滤器，可以实现权限相关的拦截器
         *     anon:无需认证（登录）可以直接访问
         *     authc:必须认证才能访问
         *     user:如果使用rememberMe的功能才可以访问
         *     perms:该资源得到资源权限才可以访问
         *     role:该资源必须得到角色权限才可以访问
         */
        Map<String, String> filterMap = new LinkedHashMap<>();
             /*    filterMap.put("/add","authc");
         filterMap.put("/update","authc");*/
        // filterMap.put("/test","anon");
        filterMap.put("/login", "anon");
        //添加Shiro授权拦截器
        filterMap.put("/add", "perms[添加]");
        filterMap.put("/foresee", "perms[预言未来]");
        filterMap.put("/update", "perms[修改]");
        filterMap.put("/delete", "perms[删除]");
        //filterMap.put("/update","perms[]");
        //filterMap.put("/delete","perms[]");
        //filterMap.put("/getAll","perms[]");
        filterMap.put("/*", "authc");
        //跳转到登陆的页面
        shiroFilterFactoryBean.setLoginUrl("/tologin");
        //设置未授权的页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/unAuth");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);

        return shiroFilterFactoryBean;
    }

    /**
     * 创建DefaultWebSecurityManager
     */
    @Bean("securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联Realm
        securityManager.setRealm((Realm) userRealm);
        return securityManager;
    }

    /**
     * 创建Realm
     */
    @Bean("userRealm")
    public UserRealm getRealm() {
        UserRealm userRealm = new UserRealm();
        return userRealm;
    }
}

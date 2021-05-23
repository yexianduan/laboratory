/**
 * @file: ShiroConfiguration.java
 * @author: yolanda
 * @date: 2021/5/22 13:32
 */

package com.yexianduan.laboratory.auth.config;


import com.yexianduan.laboratory.auth.pojo.UserRealm;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author yolanda
 * @version 1.0
 * @className ShiroConfiguration
 * @date 2021/5/22  13:32
 * @see
 * @since
 */
@Configuration
public class ShiroConfiguration {


    //权限管理，配置主要是Realm的管理认证
    @Bean("securityManager")
    public DefaultWebSecurityManager securityManager(@Qualifier("userRealm") UserRealm userRealm) {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager(userRealm);
        return  manager;
    }

    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean=new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        return shiroFilterFactoryBean;
    }

}

/**
 * @file: UserRealm.java
 * @author: yolanda
 * @date: 2021/5/21 20:44
 */

package com.yexianduan.laboratory.auth.pojo;

import com.yexianduan.laboratory.auth.mapper.UserMapper;
import com.yexianduan.laboratory.auth.service.UserService;
import com.yexianduan.laboratory.common.pojo.Perms;
import com.yexianduan.laboratory.common.pojo.Role;
import com.yexianduan.laboratory.common.pojo.User;
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


import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author yolanda
 * @version 1.0
 * @className UserRealm
 * @date 2021/5/21  20:44
 * @see
 * @since
 */
@Component("userRealm")
public class UserRealm extends AuthorizingRealm {



    @Autowired
    UserMapper userMapper;


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
//        //-----------------角色权限认证-----------------
//        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
//        String userid = (String) principalCollection.getPrimaryPrincipal();
//        List<Role> roles = userService.findRoles(Integer.valueOf(userid));
//        Set<String> roleNames = new HashSet<>(roles.size());
//        for (Role role : roles) {
//            roleNames.add(role.getRoleName());
//        }
//        //此处把当前subject对应的所有角色信息交给shiro，调用hasRole的时候就根据这些role信息判断
//        authorizationInfo.setRoles(roleNames);
//        List<String> permissions = userService.findPermissions(userid);
//        Set<String> permissionNames = new HashSet<>(permissions.size());
//        for (String permission : permissions) {
//            permissionNames.add(permission);
//        }
//        //此处把当前subject对应的权限信息交给shiro，当调用hasPermission的时候就会根据这些信息判断
//        authorizationInfo.setStringPermissions(permissionNames);
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String userName = (String)authenticationToken.getPrincipal();
        String password = new String((char[])authenticationToken.getCredentials());
        User user = userMapper.selectByName(userName);
        if(user == null){
            SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(userName, 1, this.getName());
            return authenticationInfo;
        }
        String userPassword=user.getPassword();
        // 传入用户名和密码进行身份认证，并返回认证信息,调用login的时候会自动比较这里的token和authenticationInfo
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(userName, userPassword, this.getName());
        return authenticationInfo;
    }
}

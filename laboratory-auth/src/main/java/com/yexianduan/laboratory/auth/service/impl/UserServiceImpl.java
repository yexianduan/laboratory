/**
 * @file: UserServiceImpl.java
 * @author: yolanda
 * @date: 2021/3/29 20:10
 */

package com.yexianduan.laboratory.auth.service.impl;

import com.yexianduan.laboratory.auth.mapper.UserMapper;
import com.yexianduan.laboratory.common.pojo.Perms;
import com.yexianduan.laboratory.common.pojo.Role;
import com.yexianduan.laboratory.common.pojo.User;
import com.yexianduan.laboratory.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.Permissions;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yolanda
 * @version 1.0
 * @className UserServiceImpl
 * @date 2021/3/29  20:10
 * @see
 * @since
 */
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public User login(String nickName, String password) {
        return null;
    }

    @Override
    public User findUserById(String userid) {
        return userMapper.selectById(userid);
    }

    @Override
    public List<Role> findRoles(Integer userid) {
        List<Role> list = userMapper.selectRole(userid);
        return list;
    }

    @Override
    public List<String> findPermissions(String userid) {
        return userMapper.selectPermsById(Integer.parseInt(userid));
    }
}

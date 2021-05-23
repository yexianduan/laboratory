package com.yexianduan.laboratory.auth.service;

import com.yexianduan.laboratory.common.pojo.Perms;
import com.yexianduan.laboratory.common.pojo.Role;
import com.yexianduan.laboratory.common.pojo.User;

import java.security.Permissions;
import java.util.List;

/**
 * @author yolanda
 */
public interface UserService {

    /**
     *登录
     * @param nickName
     * @param password
     * @return: com.yexianduan.laboratory.common.pojo.User
     * @see
     * @since
     */
    public User login(String nickName, String password);

    /**
     *   通过id查询用户
     * @param userid
     * @return: com.yexianduan.laboratory.common.pojo.User
     * @see
     * @since
     */

    User findUserById(String userid);

    /**
     * 根据用户id获取用户所有角色
     * @param userid
     * @return: java.util.List<com.yexianduan.laboratory.common.pojo.Role>
     * @see
     * @since
     */
    List<Role> findRoles(Integer userid);

    /**
     *    根据用户id获取用户所有权限
     * @param userid
     * @return: java.util.List<java.security.Permissions>
     * @see
     * @since
     */
    List<String> findPermissions(String userid);
}

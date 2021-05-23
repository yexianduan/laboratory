/**
 * @file: UserMapper.java
 * @author: yolanda
 * @date: 2021/4/21 20:11
 */

package com.yexianduan.laboratory.student.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yexianduan.laboratory.common.pojo.Device;
import com.yexianduan.laboratory.common.pojo.Perms;
import com.yexianduan.laboratory.common.pojo.Role;
import com.yexianduan.laboratory.common.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yolanda
 * @version 1.0
 * @className UserMapper
 * @date 2021/4/21  20:11
 * @see
 * @since
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**
     * 通过名字查询学生Id
     * @param name
     * @return: com.yexianduan.laboratory.common.pojo.User
     * @see
     * @since
     */
    public Integer queryUserId(String name);

    /**
     * 查询角色
     * @param page
     * @param roleName
     * @param labName
     * @param status
     * @param userName
     * @return: com.baomidou.mybatisplus.core.metadata.IPage<com.yexianduan.laboratory.common.pojo.User>
     * @see
     * @since
     */
    IPage<User> queryUserRole(Page<?> page, String roleName,String labName,Integer status,String userName);

    /**
     * 停用角色
     * @param id
     * @return: java.lang.Integer
     * @see
     * @since
     */
    Integer stopRole(Integer id,Integer status);


    /**
     * 编辑权限
     * @param id
     * @param arrayList
     * @return: java.lang.Boolean
     * @see
     * @since
     */
    Integer editMenu(Integer id, ArrayList<Perms> arrayList);

    /**
     * 插入角色
     * @param id
     * @param roleId
     * @return: java.lang.Integer
     * @see
     * @since
     */
    Integer  insertUserRole(Integer id,Integer roleId);

    /**
     * 查询用户
     * @param id
     * @return: com.yexianduan.laboratory.common.pojo.User
     * @see
     * @since
     */
    User selectUser(Integer id);

    /**
     * 修改密码
     * @param id
     * @param pwd
     * @return: java.lang.Integer
     * @see
     * @since
     */
    Integer updateUserPwd(Integer id,String pwd);

    /**
     * 修改信息
     * @param user
     * @return: java.lang.Integer
     * @see
     * @since
     */
    Integer updateUser(User user);


}

/**
 * @file: UserMapper.java
 * @author: yolanda
 * @date: 2021/3/17 11:57
 */

package com.yexianduan.laboratory.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yexianduan.laboratory.common.pojo.Role;
import com.yexianduan.laboratory.common.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author yolanda
 * @version 1.0
 * @className UserMapper
 * @date 2021/3/17  11:57
 * @see
 * @since
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**
     *根据nick_name查询用户
     * @param name
     * @return: com.yexianduan.laboratory.common.pojo.User
     * @see
     * @since
     */
    public User selectByName(@Param("name") String name);

    /**
     * 查找用户权限
     * @param id
     * @return: java.util.List<java.lang.String>
     * @see
     * @since
     */
    public List<String> selectPermsById(Integer id);

    /**
     * 查找角色
     * @param id
     * @return: java.util.List<com.yexianduan.laboratory.common.pojo.Role>
     * @see
     * @since
     */
    List<Role> selectRole(Integer id);
}

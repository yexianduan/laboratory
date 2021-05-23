/**
 * @file: DeviceService.java
 * @author: yolanda
 * @date: 2021/4/21 19:00
 */

package com.yexianduan.laboratory.teacher.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yexianduan.laboratory.common.pojo.*;
import com.yexianduan.laboratory.common.util.ResponsePageResult;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yolanda
 * @version 1.0
 * @className DeviceService
 * @date 2021/4/21  19:00
 * @see
 * @since
 */
public interface VerifyService {

    /**
     * 查询待审核设备
     * @param page
     * @param verifyParam
     * @return: com.baomidou.mybatisplus.core.metadata.IPage<com.yexianduan.laboratory.common.pojo.Verify>
     * @see
     * @since
     */
    public IPage<Verify> queryVerify(Page<Verify> page, VerifyParam verifyParam,Integer labId);

    /**
     * 设备审核
     * @param deviceId
     * @param status
     * @return: java.lang.Boolean
     * @see
     * @since
     */
    public Boolean verifyDevice(Integer deviceId,Integer status);


    /**
     * 查询租借待审核设备
     * @param page
     * @param verifyParam
     * @return: com.baomidou.mybatisplus.core.metadata.IPage<com.yexianduan.laboratory.common.pojo.Verify>
     * @see
     * @since
     */
    public IPage<Verify> queryVerifyBorrow(Page<Verify> page, VerifyParam verifyParam,Integer labId);

    /**
     * 设备审核
     * @param deviceId
     * @param status
     * @param userId
     * @return: java.lang.Boolean
     * @see
     * @since
     */
    public Boolean verifyBorrow(Integer deviceId,Integer status,Integer userId);

    /**
     * 查询角色
     * @param page
     * @param role
     * @return: com.baomidou.mybatisplus.core.metadata.IPage<com.yexianduan.laboratory.common.pojo.User>
     * @see
     * @since
     */
    public IPage<User> queryUserRole(Page<User> page, Role role);


    /**
     * 停用角色
     * @param id
     * @return: java.lang.Boolean
     * @see
     * @since
     */
    public Boolean stopRole(Integer id,Integer status);

    /**
     * 插入角色
     * @param user
     * @return: java.lang.Boolean
     * @see
     * @since
     */
    public Integer insertRole(User user);

    /**
     * 删除角色
     * @param id
     * @return: java.lang.Boolean
     * @see
     * @since
     */
    public Boolean deleteRole(Integer id);

    /**
     * 编辑权限
     * @param id
     * @param arrayList
     * @return: java.lang.Integer
     * @see
     * @since
     */
    Boolean editMenu(Integer id, ArrayList<Perms> arrayList);

    /**
     * 插入角色
     * @param id
     * @param roleId
     * @return: java.lang.Boolean
     * @see
     * @since
     */
    Boolean insertUserRole(Integer id,Integer roleId);

    /**
     * 租借统计
     * @return: java.util.ArrayList<java.lang.Integer>
     * @see
     * @since
     */
    ArrayList<Integer> statisticsBorrow(Integer labId);


    /**
     * 归还统计
     * @return: java.util.ArrayList<java.lang.Integer>
     * @see
     * @since
     */
    ArrayList<Integer> statisticsReturn(Integer labId);


    /**
     * 查询实验室
     * @param name
     * @param page
     * @return: java.util.List<com.yexianduan.laboratory.common.pojo.Device>
     * @see
     * @since
     */
     IPage<Lab> queryLabByName(Page<Lab> page, String name);

     /**
      * 删除实验室
      * @param id
      * @return: java.lang.Integer
      * @see
      * @since
      */
     public Integer deleteLab(Integer id);

    /**
     * 增加实验室
     * @param lab
     * @return: java.lang.Integer
     * @see
     * @since
     */
    public Integer addLab(Lab lab);


    /**
     * 修改实验室
     * @param lab
     * @return: java.lang.Integer
     * @see
     * @since
     */
    public Integer updateLab(Lab lab);
}

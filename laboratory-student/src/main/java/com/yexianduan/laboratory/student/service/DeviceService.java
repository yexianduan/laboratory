/**
 * @file: DeviceService.java
 * @author: yolanda
 * @date: 2021/4/6 16:29
 */

package com.yexianduan.laboratory.student.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yexianduan.laboratory.common.pojo.Borrow;
import com.yexianduan.laboratory.common.pojo.BorrowVO;
import com.yexianduan.laboratory.common.pojo.Device;
import com.yexianduan.laboratory.common.pojo.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yolanda
 * @version 1.0
 * @className DeviceService
 * @date 2021/4/6  16:29
 * @see
 * @since
 */
public interface DeviceService {

    /**
     *查询设备
     * @param name
     * @return: java.util.List<com.yexianduan.laboratory.common.pojo.Device>
     * @see
     * @since
     */
    public IPage<Device> queryDeviceByName(Page<Device> page, String name,Integer id);

    /**
     * 借设备
     * @return: java.lang.Boolean
     * @see
     * @since
     */
    public Boolean borrowDevice(Borrow borrow);

    /**
     * 入库
     * @param id
     * @return: java.lang.Boolean
     * @see
     * @since
     */
    public Boolean storeDevice(Integer id);

    /**
     * 查询租借记录
     * @param page
     * @param deviceName
     * @param id
     * @return: com.baomidou.mybatisplus.core.metadata.IPage<com.yexianduan.laboratory.common.pojo.BorrowVO>
     * @see
     * @since
     */
    public IPage<BorrowVO> queryPageBorrow(Page<BorrowVO> page, String deviceName,String userName,Integer id,Integer labId);

    /**
     * 归还设备
     * @param deviceId
     * @param userId
     * @return:
     * @see
     * @since
     */
    public Boolean returnDevice(Integer deviceId,Integer userId);

    /**
     * 购买申请
     * @param device
     * @return: com.yexianduan.laboratory.common.pojo.Device
     * @see
     * @since
     */
    public Device insertDevice(Device device);

    /**
     * 修改设备信息
     * @param device
     * @return: com.yexianduan.laboratory.common.pojo.Device
     * @see
     * @since
     */
    public Device updateDevice(Device device);

    /**
     * 删除设备信息
     * @param id
     * @return: com.yexianduan.laboratory.common.pojo.Device
     * @see
     * @since
     */
    public Device deleteDevice(Integer id);


    /**
     * 修改信息
     * @param user
     * @return: java.lang.Boolean
     * @see
     * @since
     */
    public Boolean updateUser(User user);

    /**
     * 修改密码
     * @param id
     * @param pwd
     * @return: java.lang.Boolean
     * @see
     * @since
     */
    public Boolean updateUserPwd(Integer id,String pwd);
}

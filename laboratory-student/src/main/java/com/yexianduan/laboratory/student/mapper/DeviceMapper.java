/**
 * @file: DeviceMapper.java
 * @author: yolanda
 * @date: 2021/4/6 16:40
 */

package com.yexianduan.laboratory.student.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yexianduan.laboratory.common.pojo.Device;
import com.yexianduan.laboratory.common.pojo.Verify;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author yolanda
 * @version 1.0
 * @className DeviceMapper
 * @date 2021/4/6  16:40
 * @see
 * @since
 */
@Mapper
public interface DeviceMapper extends BaseMapper<Device> {


    /**
     *查询设备
     * @param page
     * @param name
     * @return: com.baomidou.mybatisplus.core.metadata.IPage<com.yexianduan.laboratory.common.pojo.Device>
     * @see
     * @since
     */
    IPage<Device> queryDeviceByName(Page<?> page, String name,Integer id);

    /**
     *修改设备数量
     * @param id
     * @return: java.lang.Integer
     * @see
     * @since
     */
    Integer downDeviceNum(Integer id);

    /**
     *修改设备数量
     * @param id
     * @return: java.lang.Integer
     * @see
     * @since
     */
    Integer upDeviceNum(Integer id);

    /**
     *获取设备数量
     * @param id
     * @return: java.lang.Integer
     * @see
     * @since
     */
    Integer queryDeviceNum(Integer id);

    /**
     * 修改设备状态
     * @param id
     * @param status
     * @return: java.lang.Integer
     * @see
     * @since
     */
    Integer updateDeviceStatus(Integer id,Integer status );

    /**
     * 设备查询
     * @param page
     * @param deviceName
     * @param userName
     * @return: com.baomidou.mybatisplus.core.metadata.IPage<com.yexianduan.laboratory.common.pojo.Verify>
     * @see
     * @since
     */
    IPage<Verify> queryVerify(Page<?> page, String deviceName,String  userName,Integer labId);

}

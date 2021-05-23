/**
 * @file: DeviceServiceImpl.java
 * @author: yolanda
 * @date: 2021/4/6 16:31
 */

package com.yexianduan.laboratory.student.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yexianduan.laboratory.common.pojo.Borrow;
import com.yexianduan.laboratory.common.pojo.BorrowVO;
import com.yexianduan.laboratory.common.pojo.Device;
import com.yexianduan.laboratory.common.pojo.User;
import com.yexianduan.laboratory.student.mapper.BorrowMapper;
import com.yexianduan.laboratory.student.mapper.DeviceMapper;
import com.yexianduan.laboratory.student.mapper.UserMapper;
import com.yexianduan.laboratory.student.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;


/**
 * @author yolanda
 * @version 1.0
 * @className DeviceServiceImpl
 * @date 2021/4/6  16:31
 * @see
 * @since
 */
@Service
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    private DeviceMapper deviceMapper;

    @Autowired
    private BorrowMapper borrowMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public IPage<Device> queryDeviceByName(Page<Device> page, String name,Integer id) {
        IPage<Device> deviceIPage = deviceMapper.queryDeviceByName(page, name,id);
        return deviceIPage;
    }

    @Override
    public Boolean borrowDevice(Borrow borrow) {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
        borrow.setStatus(1);
        borrow.setBorrowTime(sdf.format(d));
        int insert = borrowMapper.insert(borrow);
        deviceMapper.downDeviceNum(borrow.getDeviceId());
        return insert>0? true:false;
    }

    @Override
    public Boolean storeDevice(Integer id){
        Integer num = deviceMapper.updateDeviceStatus(id, 3);

        return num>0? true:false;
    }

    @Override
    public IPage<BorrowVO> queryPageBorrow(Page<BorrowVO> page, String deviceName,String userName, Integer id,Integer labId) {
        IPage<BorrowVO> borrowVOIPage = borrowMapper.queryBorrowPage(page, deviceName, userName,id,labId);
        return  borrowVOIPage;
    }

    @Override
    public Boolean returnDevice(Integer deviceId,Integer userId) {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
        String format = sdf.format(d);
        Integer num = borrowMapper.returnDevice(deviceId,userId,format);
        deviceMapper.upDeviceNum(deviceId);
        return num>0? true:false;
    }

    @Override
    public Device insertDevice(Device device) {
        deviceMapper.insert(device);
        return device;
    }

    @Override
    public Device updateDevice(Device device) {
        deviceMapper.updateById(device);
        return device;
    }

    @Override
    public Device deleteDevice(Integer id) {
        deviceMapper.deleteById(id);
        return null;
    }

    @Override
    public Boolean updateUser(User user) {
        int i = userMapper.updateUser(user);
        return true;
    }

    @Override
    public Boolean updateUserPwd(Integer id, String pwd) {
        userMapper.updateUserPwd(id,pwd);
        return true;
    }
}

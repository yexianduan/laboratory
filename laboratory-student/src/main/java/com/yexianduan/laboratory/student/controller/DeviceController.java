/**
 * @file: DeviceController.java
 * @author: yolanda
 * @date: 2021/4/6 16:50
 */

package com.yexianduan.laboratory.student.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yexianduan.laboratory.common.pojo.*;
import com.yexianduan.laboratory.common.util.ResponsePageResult;
import com.yexianduan.laboratory.common.util.ResponseResult;
import com.yexianduan.laboratory.student.mapper.BorrowMapper;
import com.yexianduan.laboratory.student.mapper.DeviceMapper;
import com.yexianduan.laboratory.student.mapper.UserMapper;
import com.yexianduan.laboratory.student.service.impl.DeviceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yolanda
 * @version 1.0
 * @className DeviceController
 * @date 2021/4/6  16:50
 * @see
 * @since
 */
@Controller
@RequestMapping("/student/device")

public class DeviceController {
    private final String salt="yexianduan";
    @Autowired
    private DeviceServiceImpl deviceService;

    @Autowired
    private DeviceMapper deviceMapper;

    @Autowired
    private BorrowMapper borrowMapper;

    @Autowired
    private UserMapper userMapper;

    /**
     *查询设备
     * @param pagination
     * @return: com.yexianduan.laboratory.common.util.ResponsePageResult<java.util.List<com.yexianduan.laboratory.common.pojo.Device>>
     * @see
     * @since
     */
    @PostMapping("/query/{id}")
    @ResponseBody
    public ResponsePageResult<List<Device>> queryDeviceByName(@RequestBody Pagination<String> pagination,@PathVariable Integer id){
        ResponsePageResult<List<Device>> responsePageResult=new ResponsePageResult<>();
        Page<Device> page=new Page<>();
        page.setCurrent(pagination.getPageCount());
        page.setSize(pagination.getPageSize());
        IPage<Device> deviceIPage = deviceService.queryDeviceByName(page, pagination.getParam(),id);
        responsePageResult.setData(deviceIPage.getRecords());
        responsePageResult.setTotal(deviceIPage.getTotal());
        responsePageResult.setPageCount(deviceIPage.getCurrent());
        responsePageResult.setPageSize(deviceIPage.getSize());
        responsePageResult.setStatus(200);
        responsePageResult.setMessage("success");

        return responsePageResult;
    }

    /**
     * 借设备
     * @param borrow
     * @return: com.yexianduan.laboratory.common.util.ResponseResult<java.lang.Boolean>
     * @see
     * @since
     */
    @PostMapping("/borrow")
    @ResponseBody
    public ResponseResult<Boolean> borrowDevice(@RequestBody Borrow borrow){
        ResponseResult<Boolean> responseResult=new ResponseResult<>();
        Integer number = deviceMapper.queryDeviceNum(borrow.getDeviceId());
        Borrow bw = borrowMapper.queryBorrow(borrow.getDeviceId(), borrow.getUserId());
        if(number==0){
            responseResult.setData(false);
            responseResult.setMessage("设备数量为0，无法借出");
            responseResult.setStatus(200);
            return responseResult;
        }
        if(bw!=null){
            responseResult.setData(false);
            responseResult.setMessage("已申请租借，无法再次申请");
            responseResult.setStatus(200);
            return responseResult;
        }
        borrow.setDuration(borrow.getDuration());
        Boolean aBoolean = deviceService.borrowDevice(borrow);
        responseResult.setData(aBoolean);
        responseResult.setMessage("申请成功，待老师审核");
        responseResult.setStatus(200);
        return responseResult;
    }

    /**
     * 入库
     * @param id
     * @return: com.yexianduan.laboratory.common.util.ResponseResult<java.lang.Boolean>
     * @see
     * @since
     */
    @PutMapping("/store/{id}")
    @ResponseBody
    public ResponseResult<Boolean> storeDevice(@PathVariable Integer id){
        ResponseResult<Boolean> responseResult=new ResponseResult<>();
        Boolean aBoolean = deviceService.storeDevice(id);
        if(aBoolean){
            responseResult.setData(true);
            responseResult.setMessage("入库成功");
        }else {
            responseResult.setData(false);
            responseResult.setMessage("入库失败");
        }
        responseResult.setStatus(200);
        return responseResult;

    }


    /**
     * 查询租借记录
     * @param pagination
     * @param id
     * @return: com.yexianduan.laboratory.common.util.ResponsePageResult<java.util.List<com.yexianduan.laboratory.common.pojo.BorrowVO>>
     * @see
     * @since
     */
    @PostMapping("/queryBorrow/{id}/{labId}")
    @ResponseBody
    public ResponsePageResult<List<BorrowVO>> queryPageBorrow(@RequestBody Pagination<String> pagination, @PathVariable Integer id,@PathVariable Integer labId){
        ResponsePageResult<List<BorrowVO>> responsePageResult=new ResponsePageResult<>();
        Page<BorrowVO> page=new Page<>();
        page.setCurrent(pagination.getPageCount());
        page.setSize(pagination.getPageSize());
        String[] str = pagination.getParam().split(",");
        String deviceName="";
        String userName="";
        if(str.length==2){
            deviceName=str[0];
            userName=str[1];
        }
        if(str.length==1){
            deviceName=str[0];
        }
        Integer userId=id;
        if(id==0){
            userId=null;
        }
        IPage<BorrowVO> deviceIPage = deviceService.queryPageBorrow(page, deviceName,userName,userId,labId);
        responsePageResult.setData(deviceIPage.getRecords());
        responsePageResult.setTotal(deviceIPage.getTotal());
        responsePageResult.setPageCount(deviceIPage.getCurrent());
        responsePageResult.setPageSize(deviceIPage.getSize());
        responsePageResult.setStatus(200);
        responsePageResult.setMessage("success");

        return responsePageResult;
    }

    /**
     * 归还设备
     * @param id
     * @return: com.yexianduan.laboratory.common.util.ResponseResult<java.lang.Boolean>
     * @see
     * @since
     */
    @PostMapping("/return/{id}/{userId}")
    @ResponseBody
    public ResponseResult<Boolean> returnDevice(@PathVariable Integer id,@PathVariable Integer userId){
        ResponseResult<Boolean> responseResult=new ResponseResult<>();
        Boolean aBoolean = deviceService.returnDevice(id,userId);
        if(aBoolean){
            responseResult.setData(true);
            responseResult.setMessage("归还成功");
        }else {
            responseResult.setData(false);
            responseResult.setMessage("归还失败");
        }
        responseResult.setStatus(200);
        return responseResult;

    }

    /**
     * 购买申请
     * @param device
     * @return: com.yexianduan.laboratory.common.util.ResponseResult<com.yexianduan.laboratory.common.pojo.Device>
     * @see
     * @since
     */
    @PostMapping("/apply")
    @ResponseBody
    public ResponseResult<Device> insertDevice(@RequestBody Device device){
        ResponseResult<Device> responseResult=new ResponseResult<>();

        Integer createId = device.getCreateId();
        User user = userMapper.selectUser(createId);
        if(user.getRemark().equals("学生")){
            device.setStatus(1);
            Device device1 = deviceService.insertDevice(device);
            responseResult.setData(device1);
            responseResult.setStatus(200);
            responseResult.setMessage("购买申请成功，等待老师审核");
        }else{
            device.setStatus(3);
            Device device1 = deviceService.insertDevice(device);
            responseResult.setData(device1);
            responseResult.setStatus(200);
            responseResult.setMessage("设备申请成功");
        }
        return responseResult;
    }

    /**
     * 修改设备
     * @param device
     * @return: com.yexianduan.laboratory.common.util.ResponseResult<com.yexianduan.laboratory.common.pojo.Device>
     * @see
     * @since
     */
    @PutMapping("/update")
    @ResponseBody
    public ResponseResult<Device> updateDevice(@RequestBody Device device){
        ResponseResult<Device> responseResult=new ResponseResult<>();
        Device device1 = deviceService.updateDevice(device);
        responseResult.setData(device1);
        responseResult.setStatus(200);
        responseResult.setMessage("修改成功");
        return responseResult;
    }

    /**
     * 删除成功
     * @param id
     * @return: com.yexianduan.laboratory.common.util.ResponseResult<com.yexianduan.laboratory.common.pojo.Device>
     * @see
     * @since
     */
    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public ResponseResult<Device> deleteDevice(@PathVariable Integer id){
        ResponseResult<Device> responseResult=new ResponseResult<>();
        Device device1 = deviceService.deleteDevice(id);
        responseResult.setData(device1);
        responseResult.setStatus(200);
        responseResult.setMessage("删除成功");
        return responseResult;
    }

    /**
     * 更改用户信息
     * @return: com.yexianduan.laboratory.common.util.ResponseResult<com.yexianduan.laboratory.common.pojo.User>
     * @see
     * @since
     */
    @PostMapping("/update/user")
    @ResponseBody
    public ResponseResult<User> updateUser(@RequestBody User user){
        ResponseResult<User> responseResult=new ResponseResult<>();
        deviceService.updateUser(user);
        responseResult.setData(user);
        responseResult.setStatus(200);
        responseResult.setMessage("修改成功");
        return responseResult;
    }


    /**
     * 修改密码
     * @return: com.yexianduan.laboratory.common.util.ResponseResult<com.yexianduan.laboratory.common.pojo.User>
     * @see
     * @since
     */
    @PostMapping("/updatePwd/{id}/{pwd}")
    @ResponseBody
    public ResponseResult<Boolean> updateUserPwd(@PathVariable Integer id,@PathVariable String pwd){
        ResponseResult<Boolean> responseResult=new ResponseResult<>();
        String password= DigestUtils.md5DigestAsHex((pwd+salt).getBytes());
        deviceService.updateUserPwd(id,password);
        responseResult.setData(true);
        responseResult.setStatus(200);
        responseResult.setMessage("修改成功");
        return responseResult;
    }

    /**
     * 获取密码
     * @return: com.yexianduan.laboratory.common.util.ResponseResult<com.yexianduan.laboratory.common.pojo.User>
     * @see
     * @since
     */
    @PostMapping("/getPwd/{id}/{pwd}")
    @ResponseBody
    public ResponseResult<Boolean> getPwd(@PathVariable Integer id,@PathVariable String pwd){
        ResponseResult<Boolean> responseResult=new ResponseResult<>();
        User user = userMapper.selectUser(id);
        String password= DigestUtils.md5DigestAsHex((pwd+salt).getBytes());
        if(user.getPassword().equals(password)){
            responseResult.setData(true);
        }else{
            responseResult.setData(false);
        }
        responseResult.setStatus(200);
        responseResult.setMessage("修改成功");
        return responseResult;
    }


}

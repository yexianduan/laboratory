/**
 * @file: DeviceServiceImpl.java
 * @author: yolanda
 * @date: 2021/4/21 19:01
 */

package com.yexianduan.laboratory.teacher.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yexianduan.laboratory.common.pojo.*;
import com.yexianduan.laboratory.student.mapper.BorrowMapper;
import com.yexianduan.laboratory.student.mapper.DeviceMapper;
import com.yexianduan.laboratory.student.mapper.LabMapper;
import com.yexianduan.laboratory.student.mapper.UserMapper;
import com.yexianduan.laboratory.teacher.service.VerifyService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.java2d.pipe.AAShapePipe;

import java.util.ArrayList;

/**
 * @author yolanda
 * @version 1.0
 * @className DeviceServiceImpl
 * @date 2021/4/21  19:01
 * @see
 * @since
 */
@Service
public class VerifyServiceImpl implements VerifyService {

    @Autowired
    private DeviceMapper deviceMapper;

    @Autowired
    private BorrowMapper borrowMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private LabMapper labMapper;
    @Override
    public IPage<Verify> queryVerify(Page<Verify> page, VerifyParam verifyParam,Integer labId) {
        IPage<Verify> verifyIPage = deviceMapper.queryVerify(page, verifyParam.getDeviceName(), verifyParam.getUserName(), labId);
        return verifyIPage;
    }

    @Override
    public Boolean verifyDevice(Integer deviceId, Integer status) {
        Integer flag = deviceMapper.updateDeviceStatus(deviceId, status);
        return flag>0?true:false;
    }

    @Override
    public IPage<Verify> queryVerifyBorrow(Page<Verify> page, VerifyParam verifyParam,Integer labId) {
        IPage<Verify> verifyIPage = borrowMapper.queryVerify(page, verifyParam.getDeviceName(), verifyParam.getUserName(),labId);
        return verifyIPage;
    }

    @Override
    public Boolean verifyBorrow(Integer deviceId, Integer status, Integer userId) {
        Integer flag = borrowMapper.updateBorrowStatus(deviceId, status,userId);
        return flag>0?true:false;
    }

    @Override
    public IPage<User> queryUserRole(Page<User> page, Role role) {
        IPage<User> userIPage = userMapper.queryUserRole(page, role.getRoleName(), role.getLabName(), role.getStatus(), role.getUserName());

        return userIPage;
    }

    @Override
    public Boolean stopRole(Integer id,Integer status) {
        Integer flag = userMapper.stopRole(id, status);

        return flag>0?true:false;
    }

    @Override
    public Integer insertRole(User user) {
        int insert = userMapper.insert(user);
        return user.getId();
    }

    @Override
    public Boolean deleteRole(Integer id) {
        int flag = userMapper.deleteById(id);
        return flag>0?true:false;
    }

    @Override
    public Boolean editMenu(Integer id, ArrayList<Perms> arrayList) {
        Integer flag = userMapper.editMenu(id, arrayList);
        return flag>0?true:false;
    }

    @Override
    public Boolean insertUserRole(Integer id, Integer roleId) {
        Integer flag = userMapper.insertUserRole(id, roleId);
        return flag>0?true:false;
    }

    @Override
    public ArrayList<Integer> statisticsBorrow(Integer labId) {
        ArrayList<Borrow> borrowArrayList = borrowMapper.statisticsBorrow(labId);
        ArrayList<Integer> arrayList=new ArrayList<>();
        //初始化链表
        for(int i=0;i<12;i++){
            arrayList.add(i,0);
        }
        for(Borrow borrow:borrowArrayList){
            //获取归还时间
            String borrowTime = borrow.getBorrowTime();
            //判断该人员是否归还设备
            if(!StringUtils.isEmpty(borrowTime)){
                String[] split = borrowTime.split("-");
                int i = Integer.parseInt(split[1]);
                Integer integer = arrayList.get(i - 1);
                //归还人数+1
                arrayList.set(i-1,integer+1);
            }

        }
        return arrayList;
    }

    @Override
    public ArrayList<Integer> statisticsReturn(Integer labId) {
        //查询租借记录
        ArrayList<Borrow> borrowArrayList = borrowMapper.statisticsBorrow(labId);
        ArrayList<Integer> arrayList=new ArrayList<>();
        //初始化链表
        for(int i=0;i<12;i++){
            arrayList.add(i,0);
        }
        for(Borrow borrow:borrowArrayList){
            //获取归还时间
            String returnTime = borrow.getReturnTime();
            //判断该人员是否归还设备
            if(!StringUtils.isEmpty(returnTime)){
                String[] split = returnTime.split("-");
                int i = Integer.parseInt(split[1]);
                Integer integer = arrayList.get(i - 1);
                //归还人数+1
                arrayList.set(i-1,integer+1);
            }

        }
        return arrayList;
    }

    @Override
    public IPage<Lab> queryLabByName(Page<Lab> page, String name) {
        IPage<Lab> labIPage = labMapper.queryLabByName(page, name);

        return labIPage;
    }

    @Override
    public Integer deleteLab(Integer id) {
        labMapper.deleteById(id);
        return null;
    }

    @Override
    public Integer addLab(Lab lab) {
        return labMapper.insert(lab);
    }

    @Override
    public Integer updateLab(Lab lab) {
        labMapper.updateLab(lab);
        return null;
    }
}

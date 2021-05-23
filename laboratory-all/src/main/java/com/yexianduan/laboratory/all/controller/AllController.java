/**
 * @file: AllController.java
 * @author: yolanda
 * @date: 2021/5/22 16:21
 */

package com.yexianduan.laboratory.all.controller;

import com.yexianduan.laboratory.all.service.AllService;
import com.yexianduan.laboratory.all.service.impl.AllServiceImpl;
import com.yexianduan.laboratory.common.pojo.User;
import com.yexianduan.laboratory.common.util.ResponseResult;
import com.yexianduan.laboratory.student.mapper.UserMapper;
import com.yexianduan.laboratory.student.service.impl.DeviceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

/**
 * @author yolanda
 * @version 1.0
 * @className AllController
 * @date 2021/5/22  16:21
 * @see
 * @since
 */
@Controller
@RequestMapping("/all")
public class AllController {

    @Autowired
    private AllServiceImpl allService;

    @Autowired
    private DeviceServiceImpl deviceService;
    @Autowired
    private UserMapper userMapper;

    private final String salt="yexianduan";
    /**
     * 租借统计
     * @return: com.yexianduan.laboratory.common.util.ResponseResult<java.util.ArrayList<java.lang.Integer>>
     * @see
     * @since
     */
    @PostMapping("/chart/borrow")
    @ResponseBody
    public ResponseResult<ArrayList<Integer>> statisticsBorrow(){
        ArrayList<Integer> arrayList = allService.statisticsBorrow();
        ResponseResult<ArrayList<Integer>> responseResult=new ResponseResult<>();
        responseResult.setData(arrayList);
        responseResult.setMessage("查询成功");
        responseResult.setStatus(200);
        return responseResult;
    }

    /**
     * 归还统计
     * @return: com.yexianduan.laboratory.common.util.ResponseResult<java.util.ArrayList<java.lang.Integer>>
     * @see
     * @since
     */
    @PostMapping("/chart/return")
    @ResponseBody
    public  ResponseResult<ArrayList<Integer>> statisticsReturn(){
        ArrayList<Integer> arrayList = allService.statisticsReturn();
        ResponseResult<ArrayList<Integer>> responseResult=new ResponseResult<>();
        responseResult.setData(arrayList);
        responseResult.setMessage("查询成功");
        responseResult.setStatus(200);
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
    public ResponseResult<Boolean> updateUserPwd(@PathVariable Integer id, @PathVariable String pwd){
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

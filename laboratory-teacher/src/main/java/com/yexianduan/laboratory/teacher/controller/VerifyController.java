/**
 * @file: DeviceController.java
 * @author: yolanda
 * @date: 2021/4/21 19:00
 */

package com.yexianduan.laboratory.teacher.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yexianduan.laboratory.common.pojo.*;
import com.yexianduan.laboratory.common.util.ResponsePageResult;
import com.yexianduan.laboratory.common.util.ResponseResult;
import com.yexianduan.laboratory.student.mapper.BorrowMapper;
import com.yexianduan.laboratory.student.mapper.LabMapper;
import com.yexianduan.laboratory.student.mapper.UserMapper;
import com.yexianduan.laboratory.teacher.service.impl.PhoneServiceImpl;
import com.yexianduan.laboratory.teacher.service.impl.VerifyServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * @author yolanda
 * @version 1.0
 * @className DeviceController
 * @date 2021/4/21  19:00
 * @see
 * @since
 */
@EnableScheduling
@Controller
@RequestMapping("/teacher/verify")
public class VerifyController {


    private final String salt="yexianduan";
    @Autowired
    private VerifyServiceImpl verifyService;

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private BorrowMapper borrowMapper;
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private LabMapper labMapper;

    @Autowired
    private PhoneServiceImpl phoneService;
    private String apiUrl = "https://sms_developer.zhenzikj.com";
    //榛子云系统上获取
    private String appId = "108960";
    private String appSecret = "bc57accd-5ba2-4099-8987-17bb8c190b37";

    /**
     * 查询购买审核记录
     * @param pagination
     * @return: com.yexianduan.laboratory.common.util.ResponsePageResult<java.util.List<com.yexianduan.laboratory.common.pojo.Verify>>
     * @see
     * @since
     */
    @PostMapping("/query/{labId}")
    @ResponseBody
    public ResponsePageResult<List<Verify>> queryDeviceByName(@RequestBody Pagination<VerifyParam> pagination,@PathVariable Integer labId) {
        ResponsePageResult<List<Verify>> responsePageResult=new ResponsePageResult<>();
        Page<Verify> page=new Page<>();
        page.setCurrent(pagination.getPageCount());
        page.setSize(pagination.getPageSize());
        IPage<Verify> verifyIPage = verifyService.queryVerify(page, pagination.getParam(),labId);
        responsePageResult.setData(verifyIPage.getRecords());
        responsePageResult.setTotal(verifyIPage.getTotal());
        responsePageResult.setPageCount(verifyIPage.getCurrent());
        responsePageResult.setPageSize(verifyIPage.getSize());
        responsePageResult.setStatus(200);
        responsePageResult.setMessage("success");

        return responsePageResult;
    }


    /**
     * 审核购买申请
     * @param deviceId
     * @return: com.yexianduan.laboratory.common.util.ResponsePageResult<java.util.List<com.yexianduan.laboratory.common.pojo.Verify>>
     * @see
     * @since
     */
    @PostMapping("/{deviceId}/{status}/{phone}}")
    @ResponseBody
    public ResponseResult<Boolean> verifyDevice(@PathVariable Integer deviceId ,@PathVariable Integer status,@PathVariable String phone) {
        ResponseResult<Boolean> responseResult=new ResponseResult<>();
        Boolean aBoolean = verifyService.verifyDevice(deviceId, status);
        if(aBoolean){
            responseResult.setData(true);
            responseResult.setMessage("审核成功");
            if(status==2){
                phoneService.sendMessage(5096,phone);
            }else{
                phoneService.sendMessage(5221, phone);
            }
        }else {
            responseResult.setData(false);
            responseResult.setMessage("审核失败");
        }
        responseResult.setStatus(200);
        return responseResult;
    }


    /**
     * 查询租借待审核记录
     * @param pagination
     * @return: com.yexianduan.laboratory.common.util.ResponsePageResult<java.util.List<com.yexianduan.laboratory.common.pojo.Verify>>
     * @see
     * @since
     */
    @PostMapping("/queryBorrow/{labId}")
    @ResponseBody
    public ResponsePageResult<List<Verify>> queryDeviceBorrow(@RequestBody Pagination<VerifyParam> pagination,@PathVariable Integer labId) {
        ResponsePageResult<List<Verify>> responsePageResult=new ResponsePageResult<>();
        Page<Verify> page=new Page<>();
        page.setCurrent(pagination.getPageCount());
        page.setSize(pagination.getPageSize());
        IPage<Verify> verifyIPage = verifyService.queryVerifyBorrow(page, pagination.getParam(),labId);
        responsePageResult.setData(verifyIPage.getRecords());
        responsePageResult.setTotal(verifyIPage.getTotal());
        responsePageResult.setPageCount(verifyIPage.getCurrent());
        responsePageResult.setPageSize(verifyIPage.getSize());
        responsePageResult.setStatus(200);
        responsePageResult.setMessage("success");

        return responsePageResult;
    }


    /**
     * 审核租借申请
     * @param deviceId
     * @return: com.yexianduan.laboratory.common.util.ResponsePageResult<java.util.List<com.yexianduan.laboratory.common.pojo.Verify>>
     * @see
     * @since
     */
    @PostMapping("borrow/{deviceId}/{status}/{userId}")
    @ResponseBody
    public ResponseResult<Boolean> verifyBorrow(@PathVariable Integer deviceId ,@PathVariable Integer status,@PathVariable Integer userId) {
        ResponseResult<Boolean> responseResult=new ResponseResult<>();
        Boolean aBoolean = verifyService.verifyBorrow(deviceId, status,userId);
        User user = userMapper.selectUser(userId);
        if(aBoolean){
            responseResult.setData(true);
            responseResult.setMessage("审核成功");
            if(status==2){
             phoneService.sendMessage(5096, user.getPhone());
            }else{
                phoneService.sendMessage(5221, user.getPhone());
            }

        }else {
            responseResult.setData(false);
            responseResult.setMessage("审核失败");
        }
        responseResult.setStatus(200);
        return responseResult;
    }

    /**
     * 查询角色
     * @param pagination
     * @return: com.yexianduan.laboratory.common.util.ResponsePageResult<java.util.List<com.yexianduan.laboratory.common.pojo.Verify>>
     * @see
     * @since
     */
    @PostMapping("/queryRole")
    @ResponseBody
    public ResponsePageResult<List<User>> queryUserRole(@RequestBody Pagination<Role> pagination) {
        ResponsePageResult<List<User>> responsePageResult=new ResponsePageResult<>();
        Page<User> page=new Page<>();
        page.setCurrent(pagination.getPageCount());
        page.setSize(pagination.getPageSize());
        IPage<User> userIPage = verifyService.queryUserRole(page, pagination.getParam());
        responsePageResult.setData(userIPage.getRecords());
        responsePageResult.setTotal(userIPage.getTotal());
        responsePageResult.setPageCount(userIPage.getCurrent());
        responsePageResult.setPageSize(userIPage.getSize());
        responsePageResult.setStatus(200);
        responsePageResult.setMessage("success");
        return responsePageResult;
    }

    /**
     * 停用角色
     * @param id
     * @return: com.yexianduan.laboratory.common.util.ResponseResult<java.lang.Boolean>
     * @see
     * @since
     */
    @PostMapping("/stopRole/{id}/{status}")
    @ResponseBody
    public ResponseResult<Boolean> stopRole(@PathVariable Integer id,@PathVariable Integer status){
        ResponseResult<Boolean> responseResult=new ResponseResult<>();
        Boolean aBoolean = verifyService.stopRole(id, status);
        if(aBoolean){
            responseResult.setData(true);
            responseResult.setMessage("操作成功");
        }else {
            responseResult.setData(false);
            responseResult.setMessage("操作失败");
        }
        responseResult.setStatus(200);
        return responseResult;
    }


    /**
     * 插入角色
     * @param user
     * @return: com.yexianduan.laboratory.common.util.ResponseResult<java.lang.Boolean>
     * @see
     * @since
     */
    @PostMapping("/insertRole")
    @ResponseBody
    public ResponseResult<Integer> insertRole(@RequestBody User user){
        ResponseResult<Integer> responseResult=new ResponseResult<>();
        String password= DigestUtils.md5DigestAsHex((user.getPassword()+salt).getBytes());
        user.setPassword(password);
        Integer id = verifyService.insertRole(user);
        responseResult.setData(id);
        responseResult.setMessage("插入成功");
        responseResult.setStatus(200);
        return responseResult;
    }


    /**
     * 删除角色
     * @param id
     * @return: com.yexianduan.laboratory.common.util.ResponseResult<java.lang.Boolean>
     * @see
     * @since
     */
    @PostMapping("/deleteRole/{id}")
    @ResponseBody
    public ResponseResult<Boolean> deleteRole(@PathVariable Integer id){
        ResponseResult<Boolean> responseResult=new ResponseResult<>();
        Boolean aBoolean = verifyService.deleteRole(id);
        if(aBoolean){
            responseResult.setData(true);
            responseResult.setMessage("删除成功");
        }else {
            responseResult.setData(false);
            responseResult.setMessage("删除失败");
        }
        responseResult.setStatus(200);
        return responseResult;
    }

    /**
     * 编辑权限
     * @param perms
     * @return: java.lang.Boolean
     * @see
     * @since
     */
    @PostMapping("/menu/{id}")
    @ResponseBody
    public ResponseResult<Boolean> editMenu(@RequestBody Perms perms,@PathVariable Integer id){
        ResponseResult<Boolean> responseResult=new ResponseResult<>();
        ArrayList<Perms> arrayList=new ArrayList<>();
        if(perms.getPerm().size()==0){
            return responseResult;
        }

        if(perms.getPerm().contains(("has:device:edit"))){
            Perms perms1=new Perms();
            perms1.setId(1);
            arrayList.add(perms1);
        }

        if(perms.getPerm().contains("has:device:delete")){
            Perms perms1=new Perms();
            perms1.setId(2);
            arrayList.add(perms1);
        }
        Boolean aBoolean = verifyService.editMenu(id, arrayList);
        if(aBoolean){
            responseResult.setData(true);
            responseResult.setMessage("编辑成功");
        }else {
            responseResult.setData(false);
            responseResult.setMessage("编辑失败");
        }
        responseResult.setStatus(200);
        return responseResult;
    }


    /**
     * 插入角色
     * @param id
     * @param roleId
     * @return: com.yexianduan.laboratory.common.util.ResponseResult<java.lang.Boolean>
     * @see
     * @since
     */
    @PostMapping("/insertUserRole/{id}/{roleId}")
    @ResponseBody
    public ResponseResult<Integer> insertUserRole(@PathVariable Integer id,@PathVariable Integer roleId){
        ResponseResult<Integer> responseResult=new ResponseResult<>();
        verifyService.insertUserRole(id,roleId);
        responseResult.setData(id);
        responseResult.setMessage("插入成功");
        responseResult.setStatus(200);
        return responseResult;
    }


    /**
     * 租借统计
     * @return: com.yexianduan.laboratory.common.util.ResponseResult<java.util.ArrayList<java.lang.Integer>>
     * @see
     * @since
     */
    @PostMapping("/chart/borrow/{labId}")
    @ResponseBody
    public  ResponseResult<ArrayList<Integer>> statisticsBorrow(@PathVariable Integer labId){
        ArrayList<Integer> arrayList = verifyService.statisticsBorrow(labId);
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
    @PostMapping("/chart/return/{labId}")
    @ResponseBody
    public  ResponseResult<ArrayList<Integer>> statisticsReturn(@PathVariable Integer labId){
        ArrayList<Integer> arrayList = verifyService.statisticsReturn(labId);
        ResponseResult<ArrayList<Integer>> responseResult=new ResponseResult<>();
        responseResult.setData(arrayList);
        responseResult.setMessage("查询成功");
        responseResult.setStatus(200);
        return responseResult;
    }

    /**
     * 校验验证码
     * @param code
     * @return: com.yexianduan.laboratory.common.util.ResponseResult<java.lang.String>
     * @see
     * @since
     */
    @PostMapping("/test/{code}")
    @ResponseBody
    public ResponseResult<Boolean> verifyCode(@PathVariable String code){
        ResponseResult<Boolean> responseResult=new ResponseResult<>();
        Boolean aBoolean = phoneService.verifyCode(code);
        if(aBoolean){
            responseResult.setData(true);
            responseResult.setMessage("验证成功");
        }else {
            responseResult.setData(false);
            responseResult.setMessage("验证失败");
        }
        responseResult.setStatus(200);
        return responseResult;
    }


    /**
     * 提醒用户
     * @param phone
     * @return: com.yexianduan.laboratory.common.util.ResponseResult<java.lang.String>
     * @see
     * @since
     */
    @PostMapping("/remind/{phone}")
    @ResponseBody
    public ResponseResult<String> remindReturn(@PathVariable String phone){
        ResponseResult<String> responseResult=new ResponseResult<>();
        String s = phoneService.sendMessage(5219, phone);
        JSONObject jsonObject = JSONObject.parseObject(s);
        responseResult.setMessage(jsonObject.getString("data"));
        responseResult.setStatus(Integer.parseInt(jsonObject.getString("code")));
        return responseResult;
    }


    /**
     *查询设备
     * @param pagination
     * @return: com.yexianduan.laboratory.common.util.ResponsePageResult<java.util.List<com.yexianduan.laboratory.common.pojo.Device>>
     * @see
     * @since
     */
    @PostMapping("/queryLab")
    @ResponseBody
    public ResponsePageResult<List<Lab>> queryLabByName(@RequestBody Pagination<String> pagination){
        ResponsePageResult<List<Lab>> responsePageResult=new ResponsePageResult<>();
        Page<Lab> page=new Page<>();
        page.setCurrent(pagination.getPageCount());
        page.setSize(pagination.getPageSize());
        IPage<Lab> deviceIPage = verifyService.queryLabByName(page, pagination.getParam());
        responsePageResult.setData(deviceIPage.getRecords());
        responsePageResult.setTotal(deviceIPage.getTotal());
        responsePageResult.setPageCount(deviceIPage.getCurrent());
        responsePageResult.setPageSize(deviceIPage.getSize());
        responsePageResult.setStatus(200);
        responsePageResult.setMessage("success");
        return responsePageResult;
    }

    /**
     * 删除成功
     * @param id
     * @return: com.yexianduan.laboratory.common.util.ResponseResult<com.yexianduan.laboratory.common.pojo.Device>
     * @see
     * @since
     */
    @DeleteMapping("/deleteLab/{id}")
    @ResponseBody
    public ResponseResult<Boolean> deleteLab(@PathVariable Integer id){
        ResponseResult<Boolean> responseResult=new ResponseResult<>();
        verifyService.deleteLab(id);
        responseResult.setData(true);
        responseResult.setStatus(200);
        responseResult.setMessage("删除成功");
        return responseResult;
    }
    /**
     * 修改实验室
     * @param lab
     * @return: com.yexianduan.laboratory.common.util.ResponseResult<com.yexianduan.laboratory.common.pojo.Device>
     * @see
     * @since
     */
    @PostMapping("/updateLab")
    @ResponseBody
    public ResponseResult<Boolean> deleteLab(@RequestBody Lab lab){
        ResponseResult<Boolean> responseResult=new ResponseResult<>();
        verifyService.updateLab(lab);
        responseResult.setData(true);
        responseResult.setStatus(200);
        responseResult.setMessage("更新成功");
        return responseResult;
    }
    /**
     * 增加实验室
     * @param lab
     * @return: com.yexianduan.laboratory.common.util.ResponseResult<com.yexianduan.laboratory.common.pojo.Device>
     * @see
     * @since
     */
    @PostMapping("/insertLab")
    @ResponseBody
    public ResponseResult<Boolean> addLab(@RequestBody Lab lab){
        ResponseResult<Boolean> responseResult=new ResponseResult<>();
        verifyService.addLab(lab);
        responseResult.setData(true);
        responseResult.setStatus(200);
        responseResult.setMessage("添加成功");
        return responseResult;
    }

    /**
     * 获取实验室信息
     * @return: com.yexianduan.laboratory.common.util.ResponseResult<com.yexianduan.laboratory.common.pojo.Device>
     * @see
     * @since
     */
    @PostMapping("/getLab")
    @ResponseBody
    public ResponseResult<ArrayList<Lab>> getLab(){
        ResponseResult<ArrayList<Lab>> responseResult=new ResponseResult<>();
        ArrayList<Lab> lab = labMapper.getLab();
        responseResult.setData(lab);
        responseResult.setStatus(200);
        responseResult.setMessage("添加成功");
        return responseResult;
    }

    /**
     * 定时任务 超时通知
     * @return: void
     * @see
     * @since
     */
//    @Scheduled(cron ="0/5 * * * * ?")
    @Scheduled(cron ="0 0 23 * * ?")
    private void remind() throws ParseException {
        ArrayList<Borrow> borrows = borrowMapper.statisticsBorrow(0);
        for(Borrow borrow:borrows){
            if(StringUtils.isEmpty(borrow.getReturnTime())){
                String borrowTime=borrow.getBorrowTime();
                Integer duration=borrow.getDuration();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // 日期格式
                Date date = dateFormat.parse(borrowTime); // 指定日期

                long time = date.getTime(); // 得到指定日期的毫秒数
                duration = duration * 24 * 60 * 60 * 1000; // 要加上的天数转换成毫秒数
                time += duration; // 相加得到新的毫秒数
                Date newDate = new Date(time);
                long l = System.currentTimeMillis();
                Date nowDate = new Date(l);
                if(newDate.before(nowDate))
                {
                    System.out.println("超了");
//                phoneService.sendMessage(5219,borrow.getPhone());
                }
            }

        }

    }
}

/**
 * @file: UserController.java
 * @author: yolanda
 * @date: 2021/3/17 11:58
 */

package com.yexianduan.laboratory.auth.controller;

import com.alibaba.nacos.common.utils.UuidUtils;
import com.yexianduan.laboratory.auth.mapper.UserMapper;
import com.yexianduan.laboratory.common.pojo.User;
import com.yexianduan.laboratory.common.util.ResponseResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 * @author yolanda
 * @version 1.0
 * @className UserController
 * @date 2021/3/17  11:58
 * @see
 * @since
 */
@Controller
@RequestMapping("/auth")
public class UserController {
    @Autowired
    UserMapper userMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    //盐
    private final String salt="yexianduan";

    @PostMapping("login")
    @ResponseBody
    public ResponseResult<User> login(@RequestBody  User loginUser){
        ResponseResult<User> responseResult=new ResponseResult<>();
        User user = userMapper.selectByName(loginUser.getNickName());
        String password= DigestUtils.md5DigestAsHex((loginUser.getPassword()+salt).getBytes());
        try{
            // 从SecurityUtils里边创建一个 subject
            Subject subject=SecurityUtils.getSubject();
            // 在认证提交前准备 token（令牌）
            AuthenticationToken token = new UsernamePasswordToken(loginUser.getNickName(), password);
            // 执行认证登陆
            subject.login(token);

        }  catch (Exception e){
            responseResult.setStatus(201);
            responseResult.setMessage("密码错误!!");
            return responseResult;
        }
        if(user!=null&&user.getPassword().equals(password)&&user.getStatus().equals("2")){
            List<String> perms = userMapper.selectPermsById(user.getId());
            user.setPerms(perms);
            String token = UuidUtils.generateUuid();
            redisTemplate.opsForValue().set(token,user,10, TimeUnit.MINUTES);
            responseResult.setData(user);
            responseResult.setStatus(200);
            responseResult.setMessage(token);
        }
        else if(Integer.parseInt(user.getStatus())==1){
            responseResult.setStatus(201);
            responseResult.setMessage("该用户状态停用");
        }else{
            responseResult.setStatus(201);
            responseResult.setMessage("密码错误");
        }
        return responseResult;
    }


}

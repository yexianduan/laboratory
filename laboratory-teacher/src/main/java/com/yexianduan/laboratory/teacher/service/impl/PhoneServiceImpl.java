/**
 * @file: PhoneServiceImpl.java
 * @author: yolanda
 * @date: 2021/5/17 9:52
 */

package com.yexianduan.laboratory.teacher.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.yexianduan.laboratory.teacher.service.PhoneService;
import com.zhenzi.sms.ZhenziSmsClient;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author yolanda
 * @version 1.0
 * @className PhoneServiceImpl
 * @date 2021/5/17  9:52
 * @see
 * @since
 */
@Service
public class PhoneServiceImpl implements PhoneService {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Override
    public String sendMessage(Integer templateId, String phone) {
        String result="";
        try {
            JSONObject json = null;
            //随机生成验证码
            String code = String.valueOf(new Random().nextInt(999999));
            //将验证码通过榛子云接口发送至手机
            ZhenziSmsClient client = new ZhenziSmsClient(apiUrl, appId, appSecret);
            HashMap<String, Object> map = new HashMap<>();
            //这个是榛子云短信平台用户中心下的短信管理的短信模板的模板id
            map.put("templateId", templateId);
            String[] templateParams = new String[1];
            templateParams[0] = code;
            map.put("templateParams", templateParams);
            map.put("number", phone);
            result = client.send(map);
            stringRedisTemplate.opsForValue().set(code,"1",1, TimeUnit.DAYS);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Boolean verifyCode(String code) {
        String s = stringRedisTemplate.opsForValue().get(code);
        if(StringUtils.isEmpty(s)){
            return false;
        }
        return true;
    }
}

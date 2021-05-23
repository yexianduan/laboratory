/**
 * @file: PhoneService.java
 * @author: yolanda
 * @date: 2021/5/17 9:51
 */

package com.yexianduan.laboratory.teacher.service;

/**
 * @author yolanda
 * @version 1.0
 * @className PhoneService
 * @date 2021/5/17  9:51
 * @see
 * @since
 */
public interface PhoneService {

     String apiUrl = "https://sms_developer.zhenzikj.com";
    //榛子云系统上获取
     String appId = "108960";
     String appSecret = "bc57accd-5ba2-4099-8987-17bb8c190b37";

    /**
     * 发送短信
     * @param templateId
     * @param phone
     * @return: java.lang.String
     * @see
     * @since
     */
    String sendMessage(Integer templateId,String phone);


    /**
     * 检验验证码
     * @param code
     * @return: java.lang.Boolean
     * @see
     * @since
     */
    Boolean verifyCode(String code);
}

/**
 * @file: TestController.java
 * @author: yolanda
 * @date: 2021/3/16 13:49
 */

package com.yexianduan.laboratory.gateway.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yolanda
 * @version 1.0
 * @className TestController
 * @date 2021/3/16  13:49
 * @see
 * @since
 */
@RestController
@RefreshScope
public class TestController {
    @Value("${test.a}")
	String abc;
    @RequestMapping(value = "/echo/{string}", method = RequestMethod.GET)
        public String echo(@PathVariable String string) {
            return "Hello Nacos Discovery " + string+abc;
        }
}

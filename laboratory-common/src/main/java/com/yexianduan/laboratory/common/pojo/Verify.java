/**
 * @file: Verify.java
 * @author: yolanda
 * @date: 2021/4/21 19:42
 */

package com.yexianduan.laboratory.common.pojo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author yolanda
 * @version 1.0
 * @className Verify
 * @date 2021/4/21  19:42
 * @see
 * @since
 */
@Data
public class Verify {
    private Integer deviceId;
    private String deviceName;
    private String buyTime;
    private Integer number;
    private BigDecimal price;
    private String remark;
    private String userName;
    private Integer userId;
    private String phone;
    private String labName;
}

/**
 * @file: Borrow.java
 * @author: yolanda
 * @date: 2021/4/7 18:57
 */

package com.yexianduan.laboratory.common.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author yolanda
 * @version 1.0
 * @className Borrow
 * @date 2021/4/7  18:57
 * @see
 * @since
 */
@Data
public class Borrow implements Serializable {

    private Integer userId;
    private Integer deviceId;
    private Integer status;
    private Integer duration;
    private String borrowTime;
    private String phone;
    private String returnTime;

}

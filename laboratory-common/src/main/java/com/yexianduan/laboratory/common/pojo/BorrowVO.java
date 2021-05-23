/**
 * @file: BorrowVO.java
 * @author: yolanda
 * @date: 2021/4/12 19:21
 */

package com.yexianduan.laboratory.common.pojo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author yolanda
 * @version 1.0
 * @className BorrowVO
 * @date 2021/4/12  19:21
 * @see
 * @since
 */
@Data
public class BorrowVO implements Serializable {

    private Integer userId;
    private String userName;
    private Integer deviceId;
    private Integer duration;
    private Integer status;
    private String borrowTime;
    private BigDecimal price;
    private String name;
    private String remark;
    private String phone;
    private Integer labId;
    private String labName;
}

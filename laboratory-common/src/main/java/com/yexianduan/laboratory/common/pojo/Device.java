/**
 * @file: Device.java
 * @author: yolanda
 * @date: 2021/4/6 16:25
 */

package com.yexianduan.laboratory.common.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author yolanda
 * @version 1.0
 * @className Device
 * @date 2021/4/6  16:25
 * @see
 * @since
 */
@Data
@TableName("device")
public class Device implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private Integer number;
    private String buyTime;

    private BigDecimal price;
    private Integer status;
    private String remark;
    private Integer createId;
    private Integer labId;
    private String labName;
}

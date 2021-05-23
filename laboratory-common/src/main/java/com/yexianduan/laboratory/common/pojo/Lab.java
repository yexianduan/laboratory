/**
 * @file: Lab.java
 * @author: yolanda
 * @date: 2021/5/22 17:56
 */

package com.yexianduan.laboratory.common.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author yolanda
 * @version 1.0
 * @className Lab
 * @date 2021/5/22  17:56
 * @see
 * @since
 */
@Data
public class Lab {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String name;
    private String adress;
    private String phone;
    private String remark;
}

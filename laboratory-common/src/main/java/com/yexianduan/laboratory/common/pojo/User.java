/**
 * @file: User.java
 * @author: yolanda
 * @date: 2021/3/17 10:51
 */

package com.yexianduan.laboratory.common.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author yolanda
 * @version 1.0
 * @className User
 * @date 2021/3/17  10:51
 * @see
 * @since
 */
@Data
public class User implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer sId;
    private String userName;
    private String nickName;
    private String password;
    private String phone;
    private String remark;
    private String college;
    private String major;
    private List<String> perms;
    private String role;
    private String status;
    private Integer honesty;
    private Integer labId;
    private String labName;

}

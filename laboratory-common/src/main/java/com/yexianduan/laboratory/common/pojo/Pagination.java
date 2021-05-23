/**
 * @file: PageCount.java
 * @author: yolanda
 * @date: 2021/4/6 16:55
 */

package com.yexianduan.laboratory.common.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author yolanda
 * @version 1.0
 * @className PageCount
 * @date 2021/4/6  16:55
 * @see
 * @since
 */
@Data
public class Pagination<T> implements Serializable {

    private Integer pageCount;
    private Integer pageSize;
    private T param;
}

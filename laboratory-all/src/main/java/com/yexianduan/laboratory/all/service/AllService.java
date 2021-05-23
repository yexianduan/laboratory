/**
 * @file: AllSerivce.java
 * @author: yolanda
 * @date: 2021/5/22 16:23
 */

package com.yexianduan.laboratory.all.service;

import java.util.ArrayList;

/**
 * @author yolanda
 * @version 1.0
 * @className AllSerivce
 * @date 2021/5/22  16:23
 * @see
 * @since
 */
public interface AllService {

    /**
     * 租借统计
     * @return: java.util.ArrayList<java.lang.Integer>
     * @see
     * @since
     */
    ArrayList<Integer> statisticsBorrow();


    /**
     * 归还统计
     * @return: java.util.ArrayList<java.lang.Integer>
     * @see
     * @since
     */
    ArrayList<Integer> statisticsReturn();
}

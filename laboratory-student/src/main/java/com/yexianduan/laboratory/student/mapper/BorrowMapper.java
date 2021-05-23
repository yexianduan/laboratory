/**
 * @file: BorrowMapper.java
 * @author: yolanda
 * @date: 2021/4/7 19:00
 */

package com.yexianduan.laboratory.student.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yexianduan.laboratory.common.pojo.Borrow;
import com.yexianduan.laboratory.common.pojo.BorrowVO;
import com.yexianduan.laboratory.common.pojo.Device;
import com.yexianduan.laboratory.common.pojo.Verify;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

/**
 * @author yolanda
 * @version 1.0
 * @className BorrowMapper
 * @date 2021/4/7  19:00
 * @see
 * @since
 */
@Mapper
public interface BorrowMapper extends BaseMapper<Borrow> {

    /**
     *查询租借记录
     * @param deviceId
     * @param userId
     * @return: com.yexianduan.laboratory.common.pojo.Borrow
     * @see
     * @since
     */
    Borrow queryBorrow(Integer deviceId,Integer userId);

    /**
     * 查询租借记录(分页)
     * @param page
     * @param deviceName
     * @param userId
     * @return: com.baomidou.mybatisplus.core.metadata.IPage<com.yexianduan.laboratory.common.pojo.BorrowVO>
     * @see
     * @since
     */
    IPage<BorrowVO> queryBorrowPage(Page<?> page, String deviceName,String userName,Integer userId,Integer labId);

    /**
     * 归还设备
     * @param deviceId
     * @return: java.lang.Integer
     * @see
     * @since
     */
    Integer returnDevice(Integer deviceId,Integer userId,String time);

    /**
     * 租借查询
     * @param page
     * @param deviceName
     * @param userName
     * @return: com.baomidou.mybatisplus.core.metadata.IPage<com.yexianduan.laboratory.common.pojo.Verify>
     * @see
     * @since
     */
    IPage<Verify> queryVerify(Page<?> page, String deviceName, String  userName,Integer labId);

    /**
     * 修改设备状态
     * @param deviceId
     * @param status
     * @param userId
     * @return: java.lang.Integer
     * @see
     * @since
     */
    Integer updateBorrowStatus(Integer deviceId,Integer status,Integer userId);

    /**
     * 查询租借记录
     * @return: com.yexianduan.laboratory.common.pojo.Borrow
     * @see
     * @since
     */
    ArrayList<Borrow> statisticsBorrow(Integer labId);



}

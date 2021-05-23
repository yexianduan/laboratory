/**
 * @file: AllServiceImpl.java
 * @author: yolanda
 * @date: 2021/5/22 16:23
 */

package com.yexianduan.laboratory.all.service.impl;

import com.yexianduan.laboratory.all.service.AllService;
import com.yexianduan.laboratory.common.pojo.Borrow;
import com.yexianduan.laboratory.student.mapper.BorrowMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @author yolanda
 * @version 1.0
 * @className AllServiceImpl
 * @date 2021/5/22  16:23
 * @see
 * @since
 */
@Service
public class AllServiceImpl implements AllService {
    @Autowired
    private BorrowMapper borrowMapper;

    @Override
    public ArrayList<Integer> statisticsBorrow() {
        ArrayList<Borrow> borrowArrayList = borrowMapper.statisticsBorrow(0);
        ArrayList<Integer> arrayList=new ArrayList<>();
        //初始化链表
        for(int i=0;i<borrowArrayList.size();i++){
            arrayList.add(i,0);
        }
        for(Borrow borrow:borrowArrayList){
            //获取归还时间
            String borrowTime = borrow.getBorrowTime();
            //判断该人员是否归还设备
            if(!StringUtils.isEmpty(borrowTime)){
                String[] split = borrowTime.split("-");
                int i = Integer.parseInt(split[1]);
                Integer integer = arrayList.get(i - 1);
                //归还人数+1
                arrayList.set(i-1,integer+1);
            }

        }
        return arrayList;
    }

    @Override
    public ArrayList<Integer> statisticsReturn() {
        //查询租借记录
        ArrayList<Borrow> borrowArrayList = borrowMapper.statisticsBorrow(0);
        ArrayList<Integer> arrayList=new ArrayList<>();
        //初始化链表
        for(int i=0;i<borrowArrayList.size();i++){
            arrayList.add(i,0);
        }
        for(Borrow borrow:borrowArrayList){
            //获取归还时间
            String returnTime = borrow.getReturnTime();
            //判断该人员是否归还设备
            if(!StringUtils.isEmpty(returnTime)){
                String[] split = returnTime.split("-");
                int i = Integer.parseInt(split[1]);
                Integer integer = arrayList.get(i - 1);
                //归还人数+1
                arrayList.set(i-1,integer+1);
            }

        }
        return arrayList;
    }
}

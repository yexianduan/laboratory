/**
 * @file: LabMapper.java
 * @author: yolanda
 * @date: 2021/5/22 17:58
 */

package com.yexianduan.laboratory.student.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yexianduan.laboratory.common.pojo.Device;
import com.yexianduan.laboratory.common.pojo.Lab;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

/**
 * @author yolanda
 * @version 1.0
 * @className LabMapper
 * @date 2021/5/22  17:58
 * @see
 * @since
 */
@Mapper
public interface LabMapper extends BaseMapper<Lab> {


    /**
     * 查询实验室
     * @param page
     * @param name
     * @return: com.baomidou.mybatisplus.core.metadata.IPage<com.yexianduan.laboratory.common.pojo.Device>
     * @see
     * @since
     */
    IPage<Lab> queryLabByName(Page<?> page, String name);

    /**
     * 更新实验室
     * @param lab
     * @return: java.lang.Integer
     * @see
     * @since
     */
    Integer updateLab(Lab lab);

    /**
     *
     * @return: java.util.ArrayList<com.yexianduan.laboratory.common.pojo.Lab>
     * @see
     * @since
     */
    ArrayList<Lab> getLab();
}

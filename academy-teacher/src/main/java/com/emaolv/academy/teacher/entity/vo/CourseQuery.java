package com.emaolv.academy.teacher.entity.vo;

import lombok.Data;

/**
 * @author: liu jia
 * @description: 定义课程搜索对象 - 输入VO
 * @date: Created in 2021/5/24 20:51
 */
@Data
public class CourseQuery {

    private static final long serialVersionUID = 1L;

    private String title;
    private String teacherId;
    // 一级分类ID
    private String courseTypeParentId;
    // 二级分类ID
    private String courseTypeId;

}

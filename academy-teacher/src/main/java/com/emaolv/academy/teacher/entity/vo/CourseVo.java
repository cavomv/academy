package com.emaolv.academy.teacher.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: liu jia
 * @description: 定义课程信息展示对象 - 输出VO
 * @date: Created in 2021/5/24 20:54
 */

@Data
public class CourseVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String title;
    // 一级分类标题
    private String courseTypeParentTitle;
    // 二级分类标题
    private String courseTypeTitle;
    private String teacherName;
    private Integer lessonNum;
    private String price;
    private String cover;
    private Long buyCount;
    private Long viewCount;
    private String status;
    private String createTime;
    private String updateTime;
}

package com.emaolv.academy.teacher.entity.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author: liu jia
 * @description: 课程详情页查询对象
 * @date: Created in 2021/6/6 14:40
 */
@Data
public class WebCourseVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String title;
    private BigDecimal price;
    private Integer lessonNum;
    private String cover;
    private Long buyCount;
    private Long viewCount;
    private String description;
    private String teacherId;
    private String teacherName;
    private String intro;
    private String avatar;
    private String courseTypeParentId;
    private String courseTypeParent;
    private String courseTypeId;
    private String courseType;
}

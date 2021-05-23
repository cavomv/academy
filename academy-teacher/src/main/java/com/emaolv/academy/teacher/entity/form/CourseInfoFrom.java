package com.emaolv.academy.teacher.entity.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author: liu jia
 * @description: 课程基本信息
 * @date: Created in 2021/5/23 12:11
 */
@ApiModel("课程基本信息")
@Data
public class CourseInfoFrom implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "课程ID")
    private String id;

    @ApiModelProperty(value = "课程讲师ID")
    private String teacherId;

    @ApiModelProperty(value = "课程专业ID")
    private String courseTypeId;

    @ApiModelProperty(value = "课程专业父ID")
    private String courseTypeParentId;

    @ApiModelProperty(value = "课程标题")
    private String title;

    @ApiModelProperty(value = "课程售价 设置为0 则免费观看")
    private BigDecimal price;

    @ApiModelProperty(value = "总课时")
    private String lessonNum;

    @ApiModelProperty(value = "课程封面图片路径")
    private String cover;

    @ApiModelProperty(value = "课程简介")
    private String description;
}

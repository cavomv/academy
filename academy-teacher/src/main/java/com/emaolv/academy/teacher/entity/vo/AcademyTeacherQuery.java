package com.emaolv.academy.teacher.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author: liu jia
 * @description: 讲师查询条件封装
 * @date: Created in 2021/5/12 09:13
 */
@Data
public class AcademyTeacherQuery implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "讲师名称， 模糊查询")
    private String name;
    @ApiModelProperty(value = "头衔 1 高级讲师 2 首席讲师")
    private Integer level;
    @ApiModelProperty(value = "查询开始时间", example = "2021-01-01 10:10:10")
    private String begin;
    @ApiModelProperty(value = "查询结束时间", example = "2021-12-01 10:10:10")
    private String end;
}

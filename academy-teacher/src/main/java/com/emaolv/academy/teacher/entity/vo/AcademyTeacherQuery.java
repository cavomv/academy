package com.emaolv.academy.teacher.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author: liu jia
 * @description: 讲师查询条件封装
 * @date: Created in 2021/5/12 09:13
 */
@ApiModel("讲师查询对象")
@Data
public class AcademyTeacherQuery implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "讲师名称， 模糊查询")
    private String name;
    @ApiModelProperty(value = "讲师级别")
    private Integer level;
    @ApiModelProperty(value = "查询开始时间", example = "2021-01-01 10:10:10")
    private String joinDateBegin;
    @ApiModelProperty(value = "查询结束时间", example = "2021-12-01 10:10:10")
    private String joinDateEnd;
}

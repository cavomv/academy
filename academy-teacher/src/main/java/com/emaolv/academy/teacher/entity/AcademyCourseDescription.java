package com.emaolv.academy.teacher.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author Jia
 * @since 2021-05-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="AcademyCourseDescription对象", description="")
public class AcademyCourseDescription implements Serializable {

    private static final long serialVersionUID = 1L;

    // 课程ID与课程简介ID是一对一关系
    @ApiModelProperty(value = "课程ID")
    @TableId(value = "id", type = IdType.NONE)
    private String id;

    @ApiModelProperty(value = "课程简介")
    private String description;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill= FieldFill.INSERT)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill=FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private LocalDateTime updateTime;


}

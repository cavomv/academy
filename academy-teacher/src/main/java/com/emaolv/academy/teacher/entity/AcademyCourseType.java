package com.emaolv.academy.teacher.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 课程分类
 * </p>
 *
 * @author Jia
 * @since 2021-05-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="AcademyCourseType对象", description="课程分类")
public class AcademyCourseType implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "课程分类ID")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty(value = "课程分类名称")
    private String title;

    @ApiModelProperty(value = "上一级类目ID")
    private String parentId;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "创建日期")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新日期")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;


}

package com.emaolv.academy.teacher.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 课程视频
 * </p>
 *
 * @author Jia
 * @since 2021-05-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("academy_course_video")
@ApiModel(value="AcademyCourseVideo对象", description="课程视频")
public class AcademyCourseVideo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "视频ID")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty(value = "课程ID")
    private String courseId;

    @ApiModelProperty(value = "章节ID")
    private String chapterId;

    @ApiModelProperty(value = "节点名称")
    private String title;

    @ApiModelProperty(value = "云端视频资源")
    private String videoSourceId;

    @ApiModelProperty(value = "原始文件名称")
    private String videoOriginalName;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "播放次数")
    private Long playCount;

    @ApiModelProperty(value = "是否可以视听：0收费 1免费")
    @TableField("is_free")
    private Boolean free;

    @ApiModelProperty(value = "视频时长(秒)")
    private Float duration;

    @ApiModelProperty(value = "状态")
    private String status;

    @ApiModelProperty(value = "视频源文件大小(字节)")
    private Long size;

    @ApiModelProperty(value = "乐观锁")
    private Long version;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill= FieldFill.INSERT)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill=FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private LocalDateTime updateTime;


}

package com.emaolv.academy.teacher.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: liu jia
 * @description: 基于课时的VideoVo
 * @date: Created in 2021/5/26 21:13
 */
@Data
public class VideoVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String title;
    private Boolean free;
    private Integer sort;

    private String videoSourceId;
}

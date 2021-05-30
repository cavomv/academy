package com.emaolv.academy.teacher.entity.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: liu jia
 * @description: 基于章节的chapterVo
 * @date: Created in 2021/5/26 21:14
 */
@Data
public class ChapterVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String title;
    private Integer sort;
    private List<VideoVo> children = new ArrayList<>();

}

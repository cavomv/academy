package com.emaolv.academy.teacher.entity.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: liu jia
 * @description: 课程分类树形展示内容
 * @date: Created in 2021/5/21 19:38
 */
@Data
public class CourseCategoryQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String title;
    private Integer sort;
    private List<CourseCategoryQuery> children = new ArrayList<>();

}

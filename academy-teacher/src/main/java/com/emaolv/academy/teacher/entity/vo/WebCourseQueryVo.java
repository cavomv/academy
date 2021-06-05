package com.emaolv.academy.teacher.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: liu jia
 * @description: 课程列表展示
 * @date: Created in 2021/6/5 21:18
 */
@Data
public class WebCourseQueryVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 课程一级分类
      */
    private String courseTypeParentId;
    /**
     * 课程二级分类
      */
    private String courseTypeId;
    private String buyCountSort;
    private String createTimeSort;
    private String priceSort;


}

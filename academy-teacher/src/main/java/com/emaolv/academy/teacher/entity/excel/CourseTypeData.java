package com.emaolv.academy.teacher.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author: liu jia
 * @description: 课程分类数据 创建与excel 对应的实体类
 * @date: Created in 2021/5/20 17:17
 */
//CourseTypeData 课程分类数据
@Data
public class CourseTypeData {


    // 一级分类名称
    @ExcelProperty(index = 0)
    private String firstCategoryName;
    // 二级分类名称
    @ExcelProperty(index = 1)
    private String secondCategoryName;


}

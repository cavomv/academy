package com.emaolv.academy.teacher.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.emaolv.academy.common.exception.CustomizeException;
import com.emaolv.academy.common.result.R;
import com.emaolv.academy.teacher.entity.AcademyCourseType;
import com.emaolv.academy.teacher.entity.excel.CourseTypeData;
import com.emaolv.academy.teacher.service.AcademyCourseTypeService;

/**
 * @author: liu jia
 * @description: Excel文件上传监听器
 * @date: Created in 2021/5/20 17:28
 */

public class CourseTypeExcelListener extends AnalysisEventListener<CourseTypeData> {

    // 因为CourseTypeExcelListener不能交给Spring进行管理,需要自己new 不能注入其他对象
    // 不能实现数据库操作
    public AcademyCourseTypeService academyCourseTypeService;
    // 生成有参构造函数用于service引用
    public CourseTypeExcelListener(AcademyCourseTypeService academyCourseTypeService) {
        this.academyCourseTypeService = academyCourseTypeService;
    }
    public CourseTypeExcelListener() {}

    // 读取Excel 内容，逐行读取
    @Override
    public void invoke(CourseTypeData courseTypeData, AnalysisContext analysisContext) {
        System.out.println("解析到一条数据:{}"+ courseTypeData);
        // 如果excel 中课程数据为空则提示 数据内容为空
        if(null == courseTypeData){
            throw new CustomizeException(20001,"courseTypeData内容为空");
        }

        // 逐行读取 第一个值是一级分类 第二个值是二级分类
        AcademyCourseType existFirstCourseType = this.existFirstCourseType(
                courseTypeData.getFirstCategoryName(),
                academyCourseTypeService);
        // 如果没有相同的一级分类则继续添加 否则就停止添加一级分类
        if(existFirstCourseType == null){
//             没有相同的一级分类
            existFirstCourseType = new AcademyCourseType();
            existFirstCourseType.setParentId("0");
            // 设置一级分类名称
            System.out.println("courseTypeData>>>"+courseTypeData);
            System.out.println("courseTypeData toString>>>"+courseTypeData.toString());
            existFirstCourseType.setTitle(courseTypeData.getFirstCategoryName());
            academyCourseTypeService.save(existFirstCourseType);
        }
        // 获取一级分类ID
        String parentId = existFirstCourseType.getId();
        AcademyCourseType existSecondCourseType = this.existSecondCourseType(
                courseTypeData.getSecondCategoryName(),
                academyCourseTypeService,
                parentId);

        // 如果没有相同的二级分类则继续添加 否则就停止添加二级分类
        if(existSecondCourseType == null){
            existSecondCourseType = new AcademyCourseType();
            existSecondCourseType.setParentId(parentId);
            // 设置二级分类名称
            existSecondCourseType.setTitle(courseTypeData.getSecondCategoryName());
            academyCourseTypeService.save(existSecondCourseType);
        }

    }


    // 判断一级分类不能重复添加
    private AcademyCourseType existFirstCourseType(String name, AcademyCourseTypeService academyCourseTypeService){
        QueryWrapper<AcademyCourseType> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title", name);
        queryWrapper.eq("parent_id", "0");
        AcademyCourseType firstCategory = academyCourseTypeService.getOne(queryWrapper);
        return firstCategory;
    }

    // 判断二级分类不能重复添加
    private AcademyCourseType existSecondCourseType(String name, AcademyCourseTypeService academyCourseTypeService, String parentId){
        QueryWrapper<AcademyCourseType> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title", name);
        queryWrapper.eq("parent_id", parentId);
        AcademyCourseType secondCategory = academyCourseTypeService.getOne(queryWrapper);
        return secondCategory;
    }
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}

package com.emaolv.academy.teacher.controller;


import com.emaolv.academy.common.result.R;
import com.emaolv.academy.teacher.entity.vo.CourseCategoryQuery;
import com.emaolv.academy.teacher.service.AcademyCourseTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程分类 前端控制器
 * </p>
 *
 * @author Jia
 * @since 2021-05-20
 */
@Api(tags="课程分类管理")
@CrossOrigin
@RestController
@RequestMapping("/teacher")
public class AcademyCourseTypeController {

    @Autowired
    private AcademyCourseTypeService academyCourseTypeService;

    // 添加课程分类
    @ApiOperation("Excel批量导入课程分类")
    @PostMapping("/addsubject")
    public R addCourseType(
            @ApiParam(value = "文件", required = true) @RequestPart("file") MultipartFile file) {
        // 获取上传Excel文件
       academyCourseTypeService.saveCourseType(file, academyCourseTypeService);
        return R.success();
    }

    @ApiOperation("嵌套数据列表")
    @GetMapping("/nestedlist")
    public R nestedList(){
        List<CourseCategoryQuery> CourseCategoryQueryList = academyCourseTypeService.nestedList();
        return R.success().data("items",CourseCategoryQueryList);
    }
}


package com.emaolv.academy.teacher.controller;


import com.emaolv.academy.common.result.R;
import com.emaolv.academy.teacher.service.AcademyCourseTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

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
@RequestMapping("/academyCourseType")
public class AcademyCourseTypeController {

    @Autowired
    private AcademyCourseTypeService academyCourseTypeService;

    // 添加课程分类
    @PostMapping("import")
    public R addCourseType(
            @ApiParam(value = "文件", required = true) @RequestPart("file") MultipartFile file) {
        // 获取上传Excel文件
       academyCourseTypeService.saveCourseType(file, academyCourseTypeService);
        return R.success();
    }
}


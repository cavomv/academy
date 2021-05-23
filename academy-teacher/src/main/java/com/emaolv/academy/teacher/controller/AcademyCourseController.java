package com.emaolv.academy.teacher.controller;


import com.emaolv.academy.common.result.R;
import com.emaolv.academy.teacher.entity.form.CourseInfoFrom;
import com.emaolv.academy.teacher.service.AcademyCourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Jia
 * @since 2021-05-23
 */
@Api("课程管理")
@CrossOrigin
@RestController
@RequestMapping("/teacher")
public class AcademyCourseController {

    @Autowired
    private AcademyCourseService academyCourseService;

    @ApiOperation("新增课程")
    @PostMapping("course/save")
    public R saveCourseInfo(
            @ApiParam(value = "课程基本信息", required = true)
            @RequestBody CourseInfoFrom courseInfoFrom){
        String courseId = academyCourseService.saveCourseInfo(courseInfoFrom);
        return R.success().data("courseId", courseId).message("保存成功");
    }


    @ApiOperation("根据ID查询课程")
    @GetMapping("course/{id}")
    public R getById(
            @ApiParam(value = "课程ID", required = true)
            @PathVariable String id){
        CourseInfoFrom courseInfoFrom = academyCourseService.getCourseInfoById(id);
        if(courseInfoFrom != null) {
            return R.success().data("item", courseInfoFrom);
        } else {
            return R.fail().message("数据不存在");
        }
    }


}


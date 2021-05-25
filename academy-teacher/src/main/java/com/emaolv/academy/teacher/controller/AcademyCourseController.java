package com.emaolv.academy.teacher.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.emaolv.academy.common.result.R;
import com.emaolv.academy.teacher.entity.form.CourseInfoFrom;
import com.emaolv.academy.teacher.entity.vo.CourseQuery;
import com.emaolv.academy.teacher.entity.vo.CourseVo;
import com.emaolv.academy.teacher.service.AcademyCourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @ApiOperation("更新课程信息")
    @PutMapping("course/update")
    public R updateById(
            @ApiParam(value = "课程信息", required = true)
            @RequestBody CourseInfoFrom courseInfoFrom){
        academyCourseService.updateCourseInfoById(courseInfoFrom);
        return R.success().message("修改成功");
    }

    /**
     * 根据课程条件分页查询
     * @param current
     * @param size
     * @param courseQuery 课程查询对象
     * @return
     */
    @ApiOperation(value = "根据课程条件分页查询")
    @GetMapping(value="/course/{current}/{size}")
    public R selectCourseByPage(
            @ApiParam(name = "current", value = "当前页", required = true)
            @PathVariable(value = "current") long  current,
            @ApiParam(name = "size", value = "每页记录数", required = true)
            @PathVariable(value = "size") long  size,
            @ApiParam(name="CourseQuery", value="课程查询列表对象", required = false)
                    CourseQuery courseQuery) {
        // 参数一，查询页详情 参数二，查询对象
        IPage<CourseVo> pageCourse = academyCourseService.selectPage(current, size, courseQuery);
        // 总记录数
        long total = pageCourse.getTotal();
        // 查询结果
        List<CourseVo> records = pageCourse.getRecords();
        return R.success().data("total", total).data("rows", records);
    }

    @ApiOperation(value = "根据ID逻辑删除课程", notes = "逻辑删除课程记录")
    @DeleteMapping("course/remove/{id}")
    public R removeById(
            @ApiParam(value = "课程ID", required = true)
            @PathVariable String id) {

        // 删除课程封面
//        academyCourseService.removeCoverById(id);
        // 删除课程
        boolean result = academyCourseService.removeById(id);
        if(result) {
            return R.success().message("删除成功");
        }
        else {
            return R.fail().message("数据不存在");
        }
    }
}
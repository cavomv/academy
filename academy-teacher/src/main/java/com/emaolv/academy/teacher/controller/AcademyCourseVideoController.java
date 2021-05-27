package com.emaolv.academy.teacher.controller;


import com.emaolv.academy.common.result.R;
import com.emaolv.academy.teacher.entity.AcademyCourseVideo;
import com.emaolv.academy.teacher.service.AcademyCourseVideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author Jia
 * @since 2021-05-26
 */
@Api(tags="课时管理")
@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/teacher/video")
public class AcademyCourseVideoController {

    @Autowired
    private AcademyCourseVideoService academyCourseVideoService;

    @ApiOperation("新增课时")
    @PostMapping("save")
    public R save(
            @ApiParam(value = "课时对象")
            @RequestBody AcademyCourseVideo academyCourseVideo){
        boolean result = academyCourseVideoService.save(academyCourseVideo);
        if(result) {
            return R.success().message("保存成功");
        }
        else {
            return R.fail().message("保存失败");
        }
    }

    @ApiOperation("根据ID查询课时")
    @GetMapping("get/{id}")
    public R getById (
            @ApiParam(value = "课时ID", required = true)
            @PathVariable String id) {
        AcademyCourseVideo video = academyCourseVideoService.getById(id);
        if(video != null) {
            return R.success().data("items", video);
        } else {
            return R.fail().message("数据不存在");
        }
    }

    @ApiOperation("更新课时")
    @PutMapping("update")
    public R update(
            @ApiParam(value = "课时对象", required = true)
            @RequestBody AcademyCourseVideo academyCourseVideo
    ){
        boolean result = academyCourseVideoService.updateById(academyCourseVideo);
        if(result) {
            return R.success().message("修改成功");
        }
        else {
            return R.fail().message("数据不存在");
        }
    }

    @ApiOperation("根据ID删除课时")
    @DeleteMapping("remove/{id}")
    public R removeById(
            @ApiParam(value = "课时ID", required = true)
            @PathVariable String id) {
        // TODO 删除视频
        // 在此处调用vod中的删除视频文件的接口

        boolean result = academyCourseVideoService.removeById(id);
        if(result) {
            return R.success().message("删除成功");
        } else {
            return R.fail().message("数据不存在");
        }
    }
}


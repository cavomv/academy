package com.emaolv.academy.teacher.controller;


import com.emaolv.academy.common.result.R;
import com.emaolv.academy.teacher.entity.AcademyCourseChapter;
import com.emaolv.academy.teacher.entity.vo.ChapterVo;
import com.emaolv.academy.teacher.service.AcademyCourseChapterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器 章节管理
 * </p>
 *
 * @author Jia
 * @since 2021-05-26
 */
@Api(tags="章节管理")
@CrossOrigin
@RestController
@RequestMapping("/teacher/chapter")
@Slf4j
public class AcademyCourseChapterController {
    @Autowired
    private AcademyCourseChapterService academyCourseChapterService;

    @ApiOperation("新增章节")
    @PostMapping("save")
    public R save(
            @ApiParam(value = "章节对象", required = true)
            @RequestBody AcademyCourseChapter academyCourseChapter){
        boolean result = academyCourseChapterService.save(academyCourseChapter);
        if (result){
            return R.success().message("保存成功");
        } else {
            return R.fail().message("保存失败");
        }
    }


    @ApiOperation("根据ID查询章节")
    @GetMapping("get/{courseId}")
    public R getById(
            @ApiParam(value = "章节ID", required = true)
            @PathVariable("courseId") String chapterId){
        AcademyCourseChapter academyCourseChapter = academyCourseChapterService.getById(chapterId);
        if (academyCourseChapter != null){
            return R.success().data("items",academyCourseChapter);
        } else {
            return R.fail().message("数据不存在!");
        }
    }

    @ApiOperation("根据ID修改章节")
    @PutMapping("update")
    public R updateById(
            @ApiParam(value = "章节ID", required = true)
            @RequestBody AcademyCourseChapter academyCourseChapter){
        boolean result = academyCourseChapterService.updateById(academyCourseChapter);
        if (result){
            return R.success().message("修改成功");
        } else {
            return R.fail().message("数据不存在");
        }
    }

    @ApiOperation("根据ID删除章节")
    @DeleteMapping("remove/{id}")
    public R removeById(
            @ApiParam(value = "章节ID", required = true)
            @PathVariable String id){
        // TODO 删除课程视频
        // 此处调用VOD中删除视频文件的接口

        // 删除章节
        boolean result = academyCourseChapterService.removeChapterById(id);
        if (result){
            return R.success().message("删除成功");
        } else {
            return R.fail().message("数据不存在");
        }
    }

    @ApiOperation("嵌套章节数据列表")
    @GetMapping("nestedList/{courseId}")
    public R nestedListByCourseId(
            @ApiParam(value = "课程ID", required = true)
            @PathVariable("courseId") String courseId){
        List<ChapterVo> chapterVoList = academyCourseChapterService.nestedList(courseId);
        return R.success().data("items", chapterVoList);
    }
}


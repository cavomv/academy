package com.emaolv.academy.teacher.controller.site;

import com.emaolv.academy.common.result.R;
import com.emaolv.academy.teacher.entity.AcademyCourse;
import com.emaolv.academy.teacher.entity.AcademyCourseChapter;
import com.emaolv.academy.teacher.entity.vo.ChapterVo;
import com.emaolv.academy.teacher.entity.vo.WebCourseQueryVo;
import com.emaolv.academy.teacher.entity.vo.WebCourseVo;
import com.emaolv.academy.teacher.service.AcademyCourseChapterService;
import com.emaolv.academy.teacher.service.AcademyCourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: liu jia
 * @description: 展示课程信息
 * @date: Created in 2021/6/5 21:25
 */
@RestController
@Api(tags="网站课程管理")
@CrossOrigin
@Slf4j
@RequestMapping("api/v1/course")
public class AcademyCourseControllerApi {

    @Autowired
    private AcademyCourseService academyCourseService;
    @Autowired
    private AcademyCourseChapterService academyCourseChapterService;

    @ApiOperation("课程列表")
    @GetMapping("pageList")
    public R pageList(
            @ApiParam(value="课程信息查询对象", required = true)
            WebCourseQueryVo webCourseQueryVo){
        List<AcademyCourse> academyCourseList = academyCourseService.webPageList(webCourseQueryVo);
        return R.success().data("courseList", academyCourseList);

    }

    @ApiOperation("根据ID查询课程信息详情")
    @GetMapping("get/{courseId}")
    public R getById(
            @ApiParam(value = "课程ID", required = true)
                    @PathVariable(value = "courseId") String courseId){
        // 查询课程信息和讲师信息
        WebCourseVo webCourseVo = academyCourseService.selectWebCourseVoById(courseId);

        // 查询章节嵌套信息
        List<ChapterVo> chapterVoList = academyCourseChapterService.nestedList(courseId);

        return R.success().data("course", webCourseVo).data("chapterVoList", chapterVoList);


    }

}

package com.emaolv.academy.teacher.controller.site;

import com.emaolv.academy.common.result.R;
import com.emaolv.academy.teacher.entity.AcademyCourse;
import com.emaolv.academy.teacher.entity.vo.WebCourseQueryVo;
import com.emaolv.academy.teacher.service.AcademyCourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: liu jia
 * @description: 展示课程信息
 * @date: Created in 2021/6/5 21:25
 */
@RestController
@Api(tags="网站课程")
@CrossOrigin
@Slf4j
@RequestMapping("api/v1/course")
public class AcademyCourseControllerApi {

    @Autowired
    private AcademyCourseService academyCourseService;

    @ApiOperation("课程列表")
    @GetMapping("pageList")
    public R pageList(
            @ApiParam(value="课程信息查询对象", required = true)
            WebCourseQueryVo webCourseQueryVo){
        List<AcademyCourse> academyCourseList = academyCourseService.webPageList(webCourseQueryVo);
        return R.success().data("courseList", academyCourseList);

    }

}

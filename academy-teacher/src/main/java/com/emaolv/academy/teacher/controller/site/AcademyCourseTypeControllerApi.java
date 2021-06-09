package com.emaolv.academy.teacher.controller.site;

import com.emaolv.academy.common.result.R;
import com.emaolv.academy.teacher.entity.vo.CourseCategoryQuery;
import com.emaolv.academy.teacher.service.AcademyCourseTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: liu jia
 * @description: 网站课程分类
 * @date: Created in 2021/6/6 10:46
 */
@CrossOrigin
@RestController
@RequestMapping("api/v1/course/type/")
@Slf4j
@Api(tags="网站课程分类")
public class AcademyCourseTypeControllerApi {

    @Autowired
    private AcademyCourseTypeService academyCourseTypeService;

    @ApiOperation("网站课程分类嵌套数据列表")
    @GetMapping("nestedList")
    public R nestedList(){
        List<CourseCategoryQuery> courseCategoryQueries = academyCourseTypeService.nestedList();
        return R.success().data("items",courseCategoryQueries);

    }
}

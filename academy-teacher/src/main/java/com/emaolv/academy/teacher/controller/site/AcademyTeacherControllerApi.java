package com.emaolv.academy.teacher.controller.site;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.emaolv.academy.common.result.R;
import com.emaolv.academy.teacher.entity.AcademyTeacher;
import com.emaolv.academy.teacher.service.AcademyTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author: liu jia
 * @description: 网站讲师API
 * @date: Created in 2021/6/4 19:02
 */

@RestController
@RequestMapping("/api/v1/teacher/")
@Slf4j
@Api(tags="网站讲师")
@CrossOrigin
public class AcademyTeacherControllerApi {

    @Autowired
    private AcademyTeacherService academyTeacherService;

    @ApiOperation("所有讲师列表")
    @GetMapping("all")
    public R getAllTeacher(){
        List<AcademyTeacher> list = academyTeacherService.list(null);
        return R.success().data("items", list).message("网站成功获取讲师列表");
    }

    @ApiOperation(value="获取讲师")
    @GetMapping("get/{id}")
    public R get(
            @ApiParam(value = "讲师ID", required = true)
            @PathVariable String id) {
        Map<String, Object> map = academyTeacherService.selectTeacherInfoById(id);
        return R.success().data(map);
    }


}

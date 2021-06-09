package com.emaolv.academy.teacher.site;

import com.emaolv.academy.common.result.R;
import com.emaolv.academy.teacher.entity.AcademyTeacher;
import com.emaolv.academy.teacher.service.AcademyTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: liu jia
 * @description: 网站讲师接口
 * @date: Created in 2021/5/31 12:42
 */
@CrossOrigin
@Api("网站讲师管理")
@RestController
@RequestMapping("/api/v1/teacher")
public class TeacherController {

    @Autowired
    private AcademyTeacherService academyTeacherService;

    @ApiOperation("所有讲师列表")
    @GetMapping("list")
    public R listAll(){
        List<AcademyTeacher> list = academyTeacherService.list();
        return R.success().data("items", list).message("获取讲师列表");
    }
}

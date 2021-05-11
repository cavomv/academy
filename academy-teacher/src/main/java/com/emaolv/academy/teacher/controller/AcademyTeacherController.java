package com.emaolv.academy.teacher.controller;


import com.emaolv.academy.teacher.entity.AcademyTeacher;
import com.emaolv.academy.teacher.mapper.AcademyTeacherMapper;
import com.emaolv.academy.teacher.service.AcademyTeacherService;
import com.emaolv.academy.teacher.service.impl.AcademyTeacherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author Jia
 * @since 2021-05-11
 */
@RestController
@RequestMapping("/academyTeacher")
public class AcademyTeacherController {

    // 注入Service
    @Autowired
    private AcademyTeacherService academyTeacherService;

    // 查询讲师表所有数据
    @GetMapping("findAll")
    public List<AcademyTeacher> findAllTeacher(){
//        调用service中的方法查询所有的操作
        List<AcademyTeacher> list = academyTeacherService.list(null);
        list.forEach(System.out::println);
        return list;
    }
}


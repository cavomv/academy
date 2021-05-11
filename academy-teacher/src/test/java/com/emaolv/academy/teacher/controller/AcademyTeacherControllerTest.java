package com.emaolv.academy.teacher.controller;

import com.emaolv.academy.teacher.entity.AcademyTeacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author: liu jia
 * @description:
 * @date: Created in 2021/5/11 10:38
 */
class AcademyTeacherControllerTest {

    @Autowired
    private AcademyTeacherController academyTeacherController;

    @Test
    void findAllTeacher() {
        List<AcademyTeacher> allTeacher = academyTeacherController.findAllTeacher();
        allTeacher.forEach(System.out::println);
    }
}
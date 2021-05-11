package com.emaolv.academy.teacher.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author: liu jia
 * @description: AcademyTeacher配置类
 * @date: Created in 2021/5/11 12:15
 */

@Configuration
@MapperScan(value = "com.emaolv.academy.teacher.mapper")
public class AcademyTeacherConfig {
}

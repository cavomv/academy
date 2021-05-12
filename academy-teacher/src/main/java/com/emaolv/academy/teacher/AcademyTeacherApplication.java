package com.emaolv.academy.teacher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.oas.annotations.EnableOpenApi;

/**
 * @author: liu jia
 * @description: AcademyTeacher启动类
 * @date: Created in 2021/5/11 10:48
 */
@EnableOpenApi
@SpringBootApplication
@ComponentScan({"com.emaolv.academy","com.emaolv.academy.common"})
public class AcademyTeacherApplication {
    public static void main(String[] args) {
        SpringApplication.run(AcademyTeacherApplication.class,args);
    }
}

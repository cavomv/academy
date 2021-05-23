package com.emaolv.academy.teacher.service;

import com.emaolv.academy.teacher.entity.AcademyCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.emaolv.academy.teacher.entity.form.CourseInfoFrom;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Jia
 * @since 2021-05-23
 */
public interface AcademyCourseService extends IService<AcademyCourse> {

    String saveCourseInfo(CourseInfoFrom courseInfoFrom);
}

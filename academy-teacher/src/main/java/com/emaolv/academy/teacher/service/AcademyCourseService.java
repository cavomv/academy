package com.emaolv.academy.teacher.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.emaolv.academy.teacher.entity.AcademyCourse;
import com.emaolv.academy.teacher.entity.form.CourseInfoFrom;
import com.emaolv.academy.teacher.entity.vo.CourseQuery;
import com.emaolv.academy.teacher.entity.vo.CourseVo;
import com.emaolv.academy.teacher.entity.vo.WebCourseQueryVo;

import java.util.List;

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

    CourseInfoFrom getCourseInfoById(String id);

    void updateCourseInfoById(CourseInfoFrom courseInfoFrom);

    IPage<CourseVo> selectPage(long current, long size, CourseQuery courseQuery);

    List<AcademyCourse> webPageList(WebCourseQueryVo webCourseQueryVo);
}

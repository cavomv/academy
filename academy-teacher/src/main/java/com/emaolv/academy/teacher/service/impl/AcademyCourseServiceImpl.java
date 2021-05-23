package com.emaolv.academy.teacher.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.emaolv.academy.teacher.entity.AcademyCourse;
import com.emaolv.academy.teacher.entity.AcademyCourseDescription;
import com.emaolv.academy.teacher.entity.form.CourseInfoFrom;
import com.emaolv.academy.teacher.mapper.AcademyCourseDescriptionMapper;
import com.emaolv.academy.teacher.mapper.AcademyCourseMapper;
import com.emaolv.academy.teacher.service.AcademyCourseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Jia
 * @since 2021-05-23
 */
@Service
public class AcademyCourseServiceImpl extends ServiceImpl<AcademyCourseMapper, AcademyCourse> implements AcademyCourseService {

    @Autowired
    private AcademyCourseDescriptionMapper academyCourseDescriptionMapper;
    // 一旦发生异常 即进行代码回滚
    @Transactional(rollbackFor = Exception.class)
    @Override
    public String saveCourseInfo(CourseInfoFrom courseInfoFrom) {

        //  保存academyCourse
        AcademyCourse academyCourse = new AcademyCourse();
        // copyProperties 参数一 原对象 参数二 目的对象
        BeanUtils.copyProperties(courseInfoFrom, academyCourse);
        academyCourse.setStatus(AcademyCourse.COURSE_DRAFT);
        baseMapper.insert(academyCourse);

        //  保存courseDescription
        AcademyCourseDescription academyCourseDescription = new AcademyCourseDescription();
        academyCourseDescription.setDescription(courseInfoFrom.getDescription());
        academyCourseDescription.setId(academyCourse.getId());
        academyCourseDescriptionMapper.insert(academyCourseDescription);

        return academyCourse.getId();
    }

    @Override
    public CourseInfoFrom getCourseInfoById(String id) {

        // 根据id获取Course
        AcademyCourse academyCourse = baseMapper.selectById(id);
        if(academyCourse == null){
            return null;
        }
        // 根据id获取CourseDescription
        AcademyCourseDescription academyCourseDescription = academyCourseDescriptionMapper.selectById(id);
        // 组装courseInfoFrom
        CourseInfoFrom courseInfoFrom = new CourseInfoFrom();
        BeanUtils.copyProperties(academyCourse, courseInfoFrom);
        courseInfoFrom.setDescription(academyCourseDescription.getDescription());
        return courseInfoFrom;
    }
}

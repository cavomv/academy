package com.emaolv.academy.teacher.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.emaolv.academy.teacher.entity.AcademyCourse;
import com.emaolv.academy.teacher.entity.AcademyCourseDescription;
import com.emaolv.academy.teacher.entity.form.CourseInfoFrom;
import com.emaolv.academy.teacher.entity.vo.CourseQuery;
import com.emaolv.academy.teacher.entity.vo.CourseVo;
import com.emaolv.academy.teacher.entity.vo.WebCourseQueryVo;
import com.emaolv.academy.teacher.entity.vo.WebCourseVo;
import com.emaolv.academy.teacher.mapper.AcademyCourseDescriptionMapper;
import com.emaolv.academy.teacher.mapper.AcademyCourseMapper;
import com.emaolv.academy.teacher.service.AcademyCourseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

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

    @Override
    public void updateCourseInfoById(CourseInfoFrom courseInfoFrom) {

        // 更新course
        AcademyCourse course = new AcademyCourse();
        BeanUtils.copyProperties(courseInfoFrom, course);
        baseMapper.updateById(course);

        // 更新CourseDescription
        AcademyCourseDescription courseDescription = new AcademyCourseDescription();
        courseDescription.setDescription(courseInfoFrom.getDescription());
        courseDescription.setId(courseInfoFrom.getId());
        academyCourseDescriptionMapper.updateById(courseDescription);


    }

    @Override
    public IPage<CourseVo> selectPage(long current, long size, CourseQuery courseQuery) {

        QueryWrapper<AcademyCourse> academyCourseQueryWrapper = new QueryWrapper<>();
        academyCourseQueryWrapper.orderByDesc("c.create_time");


        String title = courseQuery.getTitle();
        String teacherId = courseQuery.getTeacherId();
        // 一级分类ID
        String courseTypeParentId = courseQuery.getCourseTypeParentId();
        // 二级分类ID
        String courseTypeId = courseQuery.getCourseTypeId();

        if(!StringUtils.isEmpty(title)){
            academyCourseQueryWrapper.like("c.title", title);
        }

        if(!StringUtils.isEmpty(teacherId)){
            academyCourseQueryWrapper.eq("c.teacher_id", teacherId);
        }
        // 一级分类
        if(!StringUtils.isEmpty(courseTypeParentId)){
            academyCourseQueryWrapper.eq("ct1.course_type_parent_id", courseTypeParentId);
        }
        // 二级分类
        if(!StringUtils.isEmpty(courseTypeId)){
            academyCourseQueryWrapper.eq("ct2.course_type_id", courseTypeId);
        }

        // 组装分页  courseVoPage page对象
        Page<CourseVo> courseVoPage = new Page<>(current, size);
         // 执行查询
        List<CourseVo> records = baseMapper.selectPageByCourseQuery(courseVoPage, academyCourseQueryWrapper);
        courseVoPage.setRecords(records);
        // 将records 设置到 courseVoPage
        return courseVoPage;
    }

    @Override
    public List<AcademyCourse> webPageList(WebCourseQueryVo webCourseQueryVo) {

        QueryWrapper<AcademyCourse> academyCourseQueryWrapper = new QueryWrapper<>();
        // 查询已发布的课程
        academyCourseQueryWrapper.eq("status", AcademyCourse.COURSE_NORMAL);

        if(!StringUtils.isEmpty(webCourseQueryVo.getCourseTypeParentId())){
            academyCourseQueryWrapper.eq("course_type_parent_id", webCourseQueryVo.getCourseTypeParentId());
        }
        if(!StringUtils.isEmpty(webCourseQueryVo.getCourseTypeId())){
            academyCourseQueryWrapper.eq("course_type_id", webCourseQueryVo.getCourseTypeId());
        }
        if(!StringUtils.isEmpty(webCourseQueryVo.getBuyCountSort())){
            academyCourseQueryWrapper.orderByDesc("buy_count");
        }
        if(!StringUtils.isEmpty(webCourseQueryVo.getCreateTimeSort())){
            academyCourseQueryWrapper.orderByDesc("create_time");
        }
        if(!StringUtils.isEmpty(webCourseQueryVo.getPriceSort())){
            academyCourseQueryWrapper.orderByDesc("price");
        }

        return baseMapper.selectList(academyCourseQueryWrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public WebCourseVo selectWebCourseVoById(String id) {

        // 获取课程基本信息
        AcademyCourse academyCourse = baseMapper.selectById(id);
        // 更新课程浏览量
        academyCourse.setViewCount(academyCourse.getViewCount()+1);
        baseMapper.updateById(academyCourse);
        // 获取课程信息
       return baseMapper.selectWebCourseVoById(id);
    }

}

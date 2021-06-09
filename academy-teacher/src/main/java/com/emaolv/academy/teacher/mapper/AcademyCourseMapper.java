package com.emaolv.academy.teacher.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.emaolv.academy.teacher.entity.AcademyCourse;
import com.emaolv.academy.teacher.entity.vo.CourseVo;
import com.emaolv.academy.teacher.entity.vo.WebCourseVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Jia
 * @since 2021-05-23
 */
public interface AcademyCourseMapper extends BaseMapper<AcademyCourse> {

    List<CourseVo> selectPageByCourseQuery(
            Page<CourseVo> courseVoPage,
            @Param(Constants.WRAPPER)
            QueryWrapper<AcademyCourse> academyCourseQueryWrapper);

    WebCourseVo selectWebCourseVoById(String courseId);
}

package com.emaolv.academy.teacher.mapper;

import com.emaolv.academy.teacher.entity.AcademyCourseType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.emaolv.academy.teacher.entity.vo.CourseCategoryQuery;

import java.util.List;

/**
 * <p>
 * 课程分类 Mapper 接口
 * </p>
 *
 * @author Jia
 * @since 2021-05-20
 */
public interface AcademyCourseTypeMapper extends BaseMapper<AcademyCourseType> {

    List<CourseCategoryQuery> selectNestedListByParentId(String parentId);
}

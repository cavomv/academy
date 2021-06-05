package com.emaolv.academy.teacher.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.emaolv.academy.teacher.entity.AcademyTeacher;
import com.emaolv.academy.teacher.entity.vo.AcademyTeacherQuery;

import java.util.Map;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author Jia
 * @since 2021-05-11
 */
public interface AcademyTeacherService extends IService<AcademyTeacher> {

    IPage<AcademyTeacher> selectPage(Page<AcademyTeacher> pageTeacher, AcademyTeacherQuery academyTeacherQuery);
    // 根据id 删除讲师头像

    /**
     * 删除讲师头像
     * @param id
     * @return
     */
    boolean removeAvatarById(String id);

    /**
     * 讲师分页查询接口
     * @param id 讲师ID
     * @return 讲师信息
     */
    Map<String, Object> selectTeacherInfoById(String id);

}

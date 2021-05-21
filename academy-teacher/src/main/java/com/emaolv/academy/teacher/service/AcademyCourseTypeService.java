package com.emaolv.academy.teacher.service;

import com.emaolv.academy.teacher.entity.AcademyCourseType;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 课程分类 服务类
 * Service 处理业务逻辑 读取上传的文件 上传至数据库
 * </p>
 *
 * @author Jia
 * @since 2021-05-20
 */
public interface AcademyCourseTypeService extends IService<AcademyCourseType> {

    /**
     * 添加课程分类
     * @param file 上传文件
     */
    void saveCourseType(MultipartFile file, AcademyCourseTypeService academyCourseTypeService);
}

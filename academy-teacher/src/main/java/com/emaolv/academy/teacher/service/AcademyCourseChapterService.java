package com.emaolv.academy.teacher.service;

import com.emaolv.academy.teacher.entity.AcademyCourseChapter;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Jia
 * @since 2021-05-26
 */
public interface AcademyCourseChapterService extends IService<AcademyCourseChapter> {

    boolean removeChapterById(String chapterId);
}

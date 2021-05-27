package com.emaolv.academy.teacher.service;

import com.emaolv.academy.teacher.entity.AcademyCourseChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.emaolv.academy.teacher.entity.vo.ChapterVo;

import java.util.List;

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
    // 定义章节嵌套列表接口
    List<ChapterVo> nestedList(String courseId);
}

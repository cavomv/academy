package com.emaolv.academy.teacher.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.emaolv.academy.teacher.entity.AcademyCourseChapter;
import com.emaolv.academy.teacher.entity.AcademyCourseVideo;
import com.emaolv.academy.teacher.entity.vo.ChapterVo;
import com.emaolv.academy.teacher.entity.vo.VideoVo;
import com.emaolv.academy.teacher.mapper.AcademyCourseChapterMapper;
import com.emaolv.academy.teacher.mapper.AcademyCourseVideoMapper;
import com.emaolv.academy.teacher.service.AcademyCourseChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Jia
 * @since 2021-05-26
 */
@Service
@Slf4j
public class AcademyCourseChapterServiceImpl extends ServiceImpl<AcademyCourseChapterMapper, AcademyCourseChapter> implements AcademyCourseChapterService {

    @Autowired
    private AcademyCourseVideoMapper academyCourseVideoMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean removeChapterById(String id) {

        // 根据courseId 删除video课时
        QueryWrapper<AcademyCourseVideo> videoQueryWrapper = new QueryWrapper<>();
        videoQueryWrapper.eq("chapter_id", id);
        academyCourseVideoMapper.delete(videoQueryWrapper);
        // 删除章节
        return this.removeById(id);
    }

    @Override
    public List<ChapterVo> nestedList(String courseId) {

        // 方案1 效率低 1+n sql
        // 通过course_id获取章节列表信息 List<Chapter>
        // 遍历章节列表信息
        // 通过ChapterId 找到video 信息

        // 方案2 效率高 1+1 sql
        // 通过course_id获取章节列表信息 List<Chapter>
        // 根据course_id获取所有的List<video> sql

        // 获取章节信息List
        QueryWrapper<AcademyCourseChapter> academyCourseChapterQueryWrapper = new QueryWrapper<>();
        academyCourseChapterQueryWrapper.eq("course_id", courseId);
        academyCourseChapterQueryWrapper.orderByAsc("sort", "id");
        List<AcademyCourseChapter> chapterList = baseMapper.selectList(academyCourseChapterQueryWrapper);


        // 获取课时信息List
        QueryWrapper<AcademyCourseVideo> academyCourseVideoQueryWrapper = new QueryWrapper<>();
        academyCourseVideoQueryWrapper.eq("course_id", courseId);
        academyCourseVideoQueryWrapper.orderByAsc("sort", "id");
        List<AcademyCourseVideo> videoList = academyCourseVideoMapper.selectList(academyCourseVideoQueryWrapper);


        // 填充章节列表 List<ChapterVo>
        List<ChapterVo> chapterVoList = new ArrayList<>();

        for (int i = 0; i < chapterList.size(); i++) {
            AcademyCourseChapter chapter = chapterList.get(i);
            // 创建chapterVo对象
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(chapter, chapterVo);
            chapterVoList.add(chapterVo);

            // 填充章节列表 List<VideoVo>
            List<VideoVo> videoVoList = new ArrayList<>();
            for (int j = 0; j < videoList.size(); j++) {
                AcademyCourseVideo video = videoList.get(j);
                if(chapter.getId().equals(video.getChapterId())){
                    VideoVo videoVo = new VideoVo();
                    BeanUtils.copyProperties(video, videoVo);
                    videoVoList.add(videoVo);
                }
            }
            chapterVo.setChildren(videoVoList);
        }
        return chapterVoList;
    }
}

package com.emaolv.academy.teacher.service.impl;

import com.alibaba.excel.EasyExcel;
import com.emaolv.academy.teacher.entity.AcademyCourseType;
import com.emaolv.academy.teacher.entity.excel.CourseTypeData;
import com.emaolv.academy.teacher.entity.vo.CourseCategoryQuery;
import com.emaolv.academy.teacher.listener.CourseTypeExcelListener;
import com.emaolv.academy.teacher.mapper.AcademyCourseTypeMapper;
import com.emaolv.academy.teacher.service.AcademyCourseTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

/**
 * <p>
 * 课程分类 服务实现类
 * </p>
 *
 * @author Jia
 * @since 2021-05-20
 */
@Service
public class AcademyCourseTypeServiceImpl extends ServiceImpl<AcademyCourseTypeMapper, AcademyCourseType> implements AcademyCourseTypeService {


    // 添加课程分类
    @Override
    public void saveCourseType(MultipartFile file, AcademyCourseTypeService academyCourseTypeService) {

            /**
             * inputStream 使用流进行文件读取
             * Class Excel中对应字段的实体类
             * ExcelListener 监听器 用于读取数据
             */
        try{
            // 文件输入流
            InputStream inputStream = file.getInputStream();
            EasyExcel.read(inputStream, CourseTypeData.class, new CourseTypeExcelListener(academyCourseTypeService)).sheet().doRead();
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     * 获取一级分类嵌套列表
     * @return
     */
    @Override
    public List<CourseCategoryQuery> nestedList() {
        return baseMapper.selectNestedListByParentId("0");
    }
}

package com.emaolv.academy.teacher.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.emaolv.academy.common.result.R;
import com.emaolv.academy.teacher.entity.AcademyTeacher;
import com.emaolv.academy.teacher.entity.vo.AcademyTeacherQuery;
import com.emaolv.academy.teacher.feign.OssFeignService;
import com.emaolv.academy.teacher.mapper.AcademyTeacherMapper;
import com.emaolv.academy.teacher.service.AcademyTeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author Jia
 * @since 2021-05-11
 */
@Service
public class AcademyTeacherServiceImpl extends ServiceImpl<AcademyTeacherMapper, AcademyTeacher> implements AcademyTeacherService {

    @Autowired
    private OssFeignService ossFeignService;

    @Override
    public IPage<AcademyTeacher> selectPage(Page<AcademyTeacher> pageTeacher, AcademyTeacherQuery academyTeacherQuery) {
        // 显示分页查询列表
        // 排序
        QueryWrapper<AcademyTeacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("sort");
        // 分页查询 - 如果没有符合条件的对象 则返回分页查询结果
        if(academyTeacherQuery == null){
            return baseMapper.selectPage(pageTeacher, queryWrapper);
        }
        // 条件查询
        String name = academyTeacherQuery.getName();
        Integer level = academyTeacherQuery.getLevel();
        String joinDateBegin = academyTeacherQuery.getJoinDateBegin();
        String joinDateEnd = academyTeacherQuery.getJoinDateEnd();

        if(!StringUtils.isEmpty(name)){
            queryWrapper.likeLeft("name", name);
        }
        if(!StringUtils.isEmpty(level)){
            queryWrapper.eq("level", level);
        }
        if(!StringUtils.isEmpty(joinDateBegin)){
            queryWrapper.ge("create_time", joinDateBegin);
        }
        if(!StringUtils.isEmpty(joinDateEnd)){
            queryWrapper.le("create_time", joinDateEnd);
        }

        return baseMapper.selectPage(pageTeacher, queryWrapper);
    }

    @Override
    public boolean removeAvatarById(String id) {
        // 根据id 获取讲师头像地址
        AcademyTeacher academyTeacher = baseMapper.selectById(id);
        if(academyTeacher != null) {
            String avatar = academyTeacher.getAvatar();
            if(!StringUtils.isEmpty(avatar)){
                // 删除讲师头像
                R r = ossFeignService.removeAvatar(avatar);
                System.out.println("远程删除：" + r);
                return r.getSuccess();
            }
        }
        return false;
    }
}

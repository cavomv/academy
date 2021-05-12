package com.emaolv.academy.teacher.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.emaolv.academy.common.result.R;
import com.emaolv.academy.teacher.entity.AcademyTeacher;
import com.emaolv.academy.teacher.entity.vo.AcademyTeacherQuery;
import com.emaolv.academy.teacher.service.AcademyTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.oas.annotations.EnableOpenApi;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author Jia
 * @since 2021-05-11
 */
@EnableOpenApi
@Api(description = "讲师管理")
@RestController
@RequestMapping("/academyTeacher")
public class AcademyTeacherController {

    // 注入Service
    @Autowired
    private AcademyTeacherService academyTeacherService;

    @ApiOperation(value="获取所有讲师列表")
    @GetMapping("findAll")
    public R findAllTeacher(){
//        调用service中的方法查询所有的操作
        List<AcademyTeacher> list = academyTeacherService.list(null);
        return R.success().data("list", list).message("获取列表成功");
    }

    @ApiOperation(value = "根据ID逻辑删除", notes = "逻辑删除数据记录")
    @DeleteMapping("{id}")
    public R removeById(
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable String id){
        boolean result = academyTeacherService.removeById(id);
        if(result){
            return R.success().message("删除成功");
        }
        else{
            return R.fail().message("删除失败");
        }
    }

    @ApiOperation(value = "新建讲师")
    @PostMapping("/save")
    public R save(
            @ApiParam(value = "讲师对象", required = true)
            @RequestBody AcademyTeacher academyTeacher){
        boolean result = academyTeacherService.save(academyTeacher);
        if(result){
            return R.success().message("保存成功");
        }
        else{
            return R.fail().message("保存失败");
        }
    }

    @ApiOperation(value = "讲师分页查询")
    @GetMapping("page/{current}/{size}")
    public R pageListTeacher(
            @ApiParam(value = "当前页", required = true)
            @PathVariable Long current,
            @ApiParam(value ="每页记录数", required = true)
            @PathVariable Long size
    ){
        // 创建Page对象
        Page<AcademyTeacher> pageListTeacher = new Page<>(current,size);
        // 调用方法实现分页
        academyTeacherService.page(pageListTeacher, null);
        // 总记录数
        long total = pageListTeacher.getTotal();
        // 每页记录数
        List<AcademyTeacher> records = pageListTeacher.getRecords();
        return R.success().data("total", total).data("rows", records);
    }

    @ApiOperation(value = "条件查询带分页的方法")
    @GetMapping("pageWithCondition/{current}/{size}")
    public R pageListTeacherWithCondition(
            @ApiParam(value = "当前页", required = true)
            @PathVariable Long current,
            @ApiParam(value ="每页记录数", required = true)
            @PathVariable Long size,
            AcademyTeacherQuery academyTeacherQuery
    )
    {
        // 创建page对象
        Page<AcademyTeacher> pageListTeacherWithCondition = new Page<>(current, size);
        // 构建条件
        QueryWrapper<AcademyTeacher> queryWrapper = new QueryWrapper<>();
        // 查询的条件
        String name = academyTeacherQuery.getName();
        Integer level = academyTeacherQuery.getLevel();
        String begin = academyTeacherQuery.getBegin();
        String end = academyTeacherQuery.getEnd();
        // 判断条件值是否为空 如果不为可空拼接条件
        if(!StringUtils.isEmpty(name)){
            // 拼接条件
            queryWrapper.like("name",name);
        }
        if(!(level == null)){
            queryWrapper.like("level",level);
        }
        if(!StringUtils.isEmpty(begin)){
            // 拼接条件
            queryWrapper.ge("create_time",begin);
        }
        if(!StringUtils.isEmpty(end)){
            // 拼接条件
            queryWrapper.le("update_time",end);
        }
        // 实现条件查询分页功能
        academyTeacherService.page(pageListTeacherWithCondition, queryWrapper);
        // 总记录数
        long total = pageListTeacherWithCondition.getTotal();
        // 每页记录数
        List<AcademyTeacher> records = pageListTeacherWithCondition.getRecords();
        return R.success().data("total", total).data("rows", records);
    }

    @ApiOperation(value ="根据讲师ID进行查询")
    @GetMapping("getInfo/{id}")
    public R getById(
            @ApiParam(value = "讲师ID", required = true)
            @PathVariable String id){
        AcademyTeacher academyTeacher = academyTeacherService.getById(id);
        return R.success().data("teacher",academyTeacher);
    }

    @ApiOperation(value ="修改讲师")
    @PostMapping("/update")
    public R update(
            @ApiParam(value = "讲师")
            @RequestBody AcademyTeacher academyTeacher)
    {
        boolean flag = academyTeacherService.updateById(academyTeacher);
        if(flag){
            return R.success().message("更新成功");
        }
        return R.fail().message("更新失败");
    }

}


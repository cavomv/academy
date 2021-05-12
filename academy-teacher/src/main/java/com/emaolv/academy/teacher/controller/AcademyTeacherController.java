package com.emaolv.academy.teacher.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.emaolv.academy.common.result.R;
import com.emaolv.academy.teacher.entity.AcademyTeacher;
import com.emaolv.academy.teacher.mapper.AcademyTeacherMapper;
import com.emaolv.academy.teacher.service.AcademyTeacherService;
import com.emaolv.academy.teacher.service.impl.AcademyTeacherServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import jdk.nashorn.internal.ir.CallNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.oas.annotations.EnableOpenApi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @GetMapping("pageAcademyTeacher/{current}/{size}")
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
}


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
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
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
@Api(tags = "讲师管理")
@RestController
@RequestMapping( "/teacher")
@Slf4j
@CrossOrigin
public class AcademyTeacherController {

    // 注入Service
    @Autowired
    private AcademyTeacherService academyTeacherService;

    @ApiOperation(value="获取所有讲师列表")
    @GetMapping(value = "/list")
    public R getTeacherList(){
        try {
            // 调用service中的方法查询所有的操作
            List<AcademyTeacher> list = academyTeacherService.list(null);
            return R.success().data("items", list).message("获取列表成功");
        } catch (Exception e) {
            e.printStackTrace();
            return R.fail();
        }
    }

    @ApiOperation(value = "根据ID逻辑删除", notes = "逻辑删除数据记录")
    @DeleteMapping("{id}")
    public R removeById(
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable String id) {
        try{
            academyTeacherService.removeById(id);
            return R.success().message("删除成功");
        } catch(Exception e) {
            e.printStackTrace();
            return R.fail().message("删除失败");
        }
    }

    /**
     * 新增讲师
     * @param academyTeacher
     * @return
     */
    @ApiOperation(value = "新建讲师")
    @PostMapping("/save")
    public R save(
            @ApiParam(name = "teacher", value = "讲师对象", required = true)
            @RequestBody AcademyTeacher academyTeacher) {
        try{
            academyTeacherService.save(academyTeacher);
            return R.success().message("保存成功");
        } catch (Exception e) {
            e.printStackTrace();
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

    /**
     * 根据讲师条件分页查询
     * @param current
     * @param size
     * @param academyTeacherQuery
     * @return
     */
    @ApiOperation(value = "根据讲师条件分页查询")
    @RequestMapping(value="/{current}/{size}")
    public R selectTeacherByPage(
            @ApiParam(name = "current", value = "当前页", required = true) @PathVariable(value = "current") long  current,
            @ApiParam(name = "size", value = "当前页", required = true) @PathVariable(value = "size") long  size,
            @RequestBody(required = false) AcademyTeacherQuery academyTeacherQuery) {
               // 构建page对象
                Page<AcademyTeacher> pageTeacher = new Page<>(current, size);
                // 构建条件
                QueryWrapper<AcademyTeacher> queryWrapper = new QueryWrapper<>();
                // ASC表示升序排序，DESC表示降序排序
                queryWrapper.orderByDesc("create_time");
                if(academyTeacherQuery == null){
                    academyTeacherService.page(pageTeacher, null);
                    // 总记录数
                    long total = pageTeacher.getTotal();
                    // 每页记录数
                    List<AcademyTeacher> records = pageTeacher.getRecords();
                    return R.success().data("total", total).data("rows", records);
                }
                // 获取对象属性
                String name = academyTeacherQuery.getName();
                System.out.println("academyTeacherQuery.getName();"+academyTeacherQuery.getName());
                Integer level = academyTeacherQuery.getLevel();
                String begin = academyTeacherQuery.getBegin();
                String end = academyTeacherQuery.getEnd();
                // 判断条件值是否为空 如果不为空 则拼接条件
                if (StringUtils.hasText(name)) {
                    queryWrapper.like("name", name);
                }
                if ((String.valueOf(level) == null)){
                    System.out.println((String.valueOf(level) == null));
                    if(StringUtils.hasText(String.valueOf(level))){
                        queryWrapper.eq("level", String.valueOf(level));
                    }
                }

                if(StringUtils.hasText(begin)){
                    // ge 大于等于 >=
                    queryWrapper.ge("create_time",begin);
                }
                if(StringUtils.hasText(end)){
                    // 小于等于 <=
                    queryWrapper.le("update_time",end);
                }
                academyTeacherService.page(pageTeacher, queryWrapper);
                long total = pageTeacher.getTotal();
                List<AcademyTeacher> records = pageTeacher.getRecords();
                return R.success().data("total", total).data("rows", records);
    }

    /**
     * 根据ID 查询讲师
     * @param id
     * @return
     */
    @ApiOperation(value ="根据讲师ID进行查询")
    @GetMapping("{id}")
    public R getById(
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable String id) {
        try{
            AcademyTeacher academyTeacher= academyTeacherService.getById(id);
            return R.success().data("teacher", academyTeacher);
        } catch (Exception e) {
            e.printStackTrace();
            return R.fail();
        }
    }

    @ApiOperation(value ="根据讲师ID修改讲师信息")
    @RequestMapping("/update")
    public R update(
            @ApiParam(name = "teacher", value = "讲师", required = true)
            @RequestBody AcademyTeacher academyTeacher)
    {
        try{
            academyTeacherService.updateById(academyTeacher);
            return R.success().message("更新成功");
        } catch (Exception e){
            e.printStackTrace();
            return R.fail().message("更新失败");
        }
    }
}


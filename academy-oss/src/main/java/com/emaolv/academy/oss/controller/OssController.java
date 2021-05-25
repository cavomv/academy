package com.emaolv.academy.oss.controller;

import com.emaolv.academy.common.result.R;
import com.emaolv.academy.oss.service.OssService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

/**
 * @author: liu jia
 * @description: 阿里云文件管理
 * @date: Created in 2021/5/18 20:00
 */
@Api(tags = "阿里云文件管理")
@CrossOrigin
@RestController
@RequestMapping("/oss")
public class OssController {

    @Autowired
    private OssService ossService;

    @ApiOperation(value = "头像上传")
    @PostMapping(value = "/avatar/upload")
    public R UploadOssFile(@ApiParam(value = "文件", required = true) @RequestPart("file") MultipartFile file,
                           @ApiParam(value = "模块", required = true) @RequestParam("module") String module)
            throws IOException {
        InputStream inputStream = file.getInputStream();
        String originalFilename = file.getOriginalFilename();
        String url = ossService.upload(inputStream, module, originalFilename);
        return R.success().data("url", url).message("文件上传成功");

    }

    @ApiOperation(value = "调用了远程删除oss文件")
    @DeleteMapping(value = "/remove/file")
    public R removeFile(
            @ApiParam(value = "文件路径", required = true)
            @RequestBody String url){
        ossService.removeFile(url);
        return R.success().message("文件删除成功");
    }


    @ApiOperation(value = "模拟长流程业务")
    @GetMapping("test")
    public R test() {
        System.out.println("终于可以调用到远程的OSS方法");
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return R.success();
    }
}

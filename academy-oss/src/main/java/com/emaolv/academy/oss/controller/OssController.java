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

}

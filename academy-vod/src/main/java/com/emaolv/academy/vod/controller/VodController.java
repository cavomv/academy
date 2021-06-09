package com.emaolv.academy.vod.controller;

import com.emaolv.academy.common.exception.CustomizeException;
import com.emaolv.academy.common.exception.UnifiedException;
import com.emaolv.academy.common.result.R;
import com.emaolv.academy.common.result.ResponseEnum;
import com.emaolv.academy.vod.service.VodService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author: liu jia
 * @description: t
 * @date: Created in 2021/6/7 18:46
 */
@Api(tags="VOD管理")
@RestController
@CrossOrigin
@RequestMapping("/vod/")
@Slf4j
public class VodController {

    @Autowired
    private VodService vodService;

    @ApiOperation("上传视频")
    @PostMapping("/upload")
    public R uploadVideo(
            @ApiParam(value = "文件", required = true)
            @RequestPart("file") MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();
            String originalFilename = file.getOriginalFilename();
            log.info("inputStream"+ inputStream);
            log.info("originalFilename"+ originalFilename);
            String videoId = vodService.uploadVideo(inputStream, originalFilename);
            return R.success().message("视频上传成功").data("videoId", videoId);
        } catch (IOException e) {
            log.error(ExceptionUtils.getMessage(e));
            throw new CustomizeException(ResponseEnum.VIDEO_UPLOAD_TOMCAT_ERROR);
        }
    }
}

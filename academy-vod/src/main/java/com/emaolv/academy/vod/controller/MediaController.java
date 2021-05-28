package com.emaolv.academy.vod.controller;

import com.emaolv.academy.common.exception.CustomizeException;
import com.emaolv.academy.common.result.R;
import com.emaolv.academy.common.result.ResponseEnum;
import com.emaolv.academy.vod.service.VideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author: liu jia
 * @description:
 * @date: Created in 2021/5/28 08:35
 */

@Api(tags="阿里云视频点播")
@CrossOrigin
@RestController
@RequestMapping("teacher/vod")
@Slf4j
public class MediaController {

    @Autowired
    private VideoService videoService;

    @PostMapping("upload")
    public R uploadVideo(
            @ApiParam(name = "file", value = "文件", required = true)
            @RequestPart("file") MultipartFile file
            ){
        try{
            InputStream inputStream = file.getInputStream();
            String originalFileName = file.getOriginalFilename();
            String videoId = videoService.uploadVideo(inputStream, originalFileName);
            return R.success().message("视频上传成功").data("videoId", videoId);
        }catch (IOException e)
        {
            throw new CustomizeException(ResponseEnum.VIDEO_UPLOAD_ALIYUN_ERROR);
        }

    }
    @ApiOperation("test")
    @GetMapping("hi")
    public String hi(){
        return "hi, vod";
    }
}

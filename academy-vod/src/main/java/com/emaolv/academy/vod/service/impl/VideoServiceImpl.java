package com.emaolv.academy.vod.service.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.emaolv.academy.common.exception.CustomizeException;
import com.emaolv.academy.common.result.ResponseEnum;
import com.emaolv.academy.vod.service.VideoService;
import com.emaolv.academy.vod.utils.VodProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.InputStream;

/**
 * @author: liu jia
 * @description: 实现服务类
 * @date: Created in 2021/5/28 08:17
 */
@Service
@Slf4j
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VodProperties vodProperties;
    @Override
    public String uploadVideo(InputStream file, String originalFilename) {
        String title = originalFilename.substring(0, originalFilename.lastIndexOf("."));
        UploadStreamRequest request = new UploadStreamRequest(
        vodProperties.getKeyid(),
        vodProperties.getKeysecret(),
        title, originalFilename, file);
        UploadVideoImpl uploader = new UploadVideoImpl();
        UploadStreamResponse response = uploader.uploadStream(request);
        String videoId = response.getVideoId();
        //没有正确的返回videoid则说明上传失败
        if(StringUtils.isEmpty(videoId)){
            log.error("阿里云上传失败：" + response.getCode() + " - " + response.getMessage());
            throw new CustomizeException(ResponseEnum.VIDEO_UPLOAD_ALIYUN_ERROR);
        }
        return videoId;
    }

}

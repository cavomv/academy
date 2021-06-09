package com.emaolv.academy.vod.service.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.emaolv.academy.common.exception.CustomizeException;
import com.emaolv.academy.common.result.ResponseEnum;
import com.emaolv.academy.vod.service.VodService;
import com.emaolv.academy.vod.utils.VodProperties;
import io.netty.util.internal.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.InputStream;

/**
 * @author: liu jia
 * @description: 实现视频业务上传
 * @date: Created in 2021/6/8 11:10
 */
@Service
@Slf4j
public class VodServiceImpl implements VodService {

    @Autowired
    private VodProperties vodProperties;

    @Override
    public String uploadVideo(InputStream inputStream, String originalFilename) {

        // 获取文件名
        String title = originalFilename.substring(0, originalFilename.lastIndexOf("."));
        UploadStreamRequest request = new UploadStreamRequest(
                vodProperties.getKeyid(),
                vodProperties.getKeysecret(),
                title, originalFilename, inputStream);
        UploadVideoImpl uploadVideo = new UploadVideoImpl();
        UploadStreamResponse response = uploadVideo.uploadStream(request);
        String videoId = response.getVideoId();
        // 没有正确的返回
        if(StringUtils.isEmpty(videoId)) {
            log.error("阿里云上传失败：" + response.getCode() + " - " + response.getMessage());
            throw new CustomizeException(ResponseEnum.VIDEO_UPLOAD_ALIYUN_ERROR);
        }
        return videoId;
    }
}

package com.emaolv.academy.vod.service;

import org.springframework.stereotype.Service;

import java.io.InputStream;

/**
 * @author: liu jia
 * @description: 视频上传业务
 * @date: Created in 2021/5/28 08:15
 */

public interface VideoService {
    String uploadVideo(InputStream file, String originalFileName);
}

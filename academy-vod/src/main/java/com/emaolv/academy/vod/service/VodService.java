package com.emaolv.academy.vod.service;

import java.io.InputStream;

/**
 * @author: liu jia
 * @description: 视频上传业务接口
 * @date: Created in 2021/6/8 11:08
 */
public interface VodService {
    String uploadVideo(InputStream file, String originalFilename);
}

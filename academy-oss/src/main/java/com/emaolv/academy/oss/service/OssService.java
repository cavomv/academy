package com.emaolv.academy.oss.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * @author: liu jia
 * @description: OssService
 * @date: Created in 2021/5/18 20:09
 */
public interface OssService {

    /**
     * 上传文件到阿里云
     * @param inputStream
     * @param module
     * @param fileName
     * @return
     */
    String upload(InputStream inputStream, String module, String fileName);
}

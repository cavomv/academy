package com.emaolv.academy.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.CannedAccessControlList;
import com.emaolv.academy.oss.service.OssService;
import com.emaolv.academy.oss.utils.ConstantPropertiesUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.UUID;

/**
 * @author: liu jia
 * @description: 实现上传头像到阿里云接口
 * @date: Created in 2021/5/18 20:10
 */
@Service
public class OssServiceImpl implements OssService {
    @Override
    public String upload(InputStream inputStream, String module, String fileName) {

        String endpoint = ConstantPropertiesUtils.END_POINT;
        String accessKeyId = ConstantPropertiesUtils.KEY_ID;
        String accessKeySecret = ConstantPropertiesUtils.KEY_SECRET;

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        // 判断BUCKET_NAME是否存在
        if(!ossClient.doesBucketExist(ConstantPropertiesUtils.BUCKET_NAME)){
            ossClient.createBucket(ConstantPropertiesUtils.BUCKET_NAME);
            ossClient.setBucketAcl(ConstantPropertiesUtils.BUCKET_NAME, CannedAccessControlList.PublicRead);
        }
        // 文件目录结构 "avatar/2021/05/18/uuid"
        // 构建日期路径
        String timeFolder = new DateTime().toString("/yyyy/MM/dd/");
        // 生成文件名
        fileName = UUID.randomUUID().toString() + fileName.substring(fileName.lastIndexOf("."));
        String key = module+timeFolder+fileName;
        // 上传文件流
        ossClient.putObject(ConstantPropertiesUtils.BUCKET_NAME, key , inputStream);
        // 关闭OSSClient。
        ossClient.shutdown();
        String url = "https://" + ConstantPropertiesUtils.BUCKET_NAME + "." + ConstantPropertiesUtils.END_POINT + "/" + key;
        return url;
    }

    @Override
    public void removeAvatarFile(String url) {
        String endpoint = ConstantPropertiesUtils.END_POINT;
        String accessKeyId = ConstantPropertiesUtils.KEY_ID;
        String accessKeySecret = ConstantPropertiesUtils.KEY_SECRET;
        String bucketName = ConstantPropertiesUtils.BUCKET_NAME;

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        //  删除讲师头像
        String host = "https://" + bucketName + "." + endpoint + "/";
        String objectName = url.substring(host.length());
        ossClient.deleteObject(bucketName, objectName);
        // 关闭OSSClient。
        ossClient.shutdown();
    }
}

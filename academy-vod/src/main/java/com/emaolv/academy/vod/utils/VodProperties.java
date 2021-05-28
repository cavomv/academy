package com.emaolv.academy.vod.utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author: liu jia
 * @description: 创建常量读取工具类
 * @date: Created in 2021/5/28 08:12
 */

@Data
@Component
@ConfigurationProperties(prefix="aliyun.vod")
public class VodProperties {

    private String keyid;
    private String keysecret;
}

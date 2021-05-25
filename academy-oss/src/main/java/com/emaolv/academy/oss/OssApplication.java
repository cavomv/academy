package com.emaolv.academy.oss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import springfox.documentation.oas.annotations.EnableOpenApi;

/**
 * @author: liu jia
 * @description: OssApplication
 * @date: Created in 2021/5/18 19:30
 */

@EnableOpenApi
@EnableDiscoveryClient
@SpringBootApplication
public class OssApplication {
    public static void main(String[] args) {
        SpringApplication.run(OssApplication.class,args);
    }
}

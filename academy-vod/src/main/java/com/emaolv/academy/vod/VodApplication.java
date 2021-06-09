package com.emaolv.academy.vod;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.oas.annotations.EnableOpenApi;

/**
 * @author: liu jia
 * @description:
 * @date: Created in 2021/6/7 14:56
 */
@EnableOpenApi
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan({"com.emaolv.academy"})
@EnableDiscoveryClient
public class VodApplication {
    public static void main(String[] args) {
        SpringApplication.run(VodApplication.class, args);
    }
}

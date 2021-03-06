package com.emaolv.academy.teacher.feign;

import com.emaolv.academy.common.result.R;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author: liu jia
 * @description: oss远程接口
 * @date: Created in 2021/5/24 09:24
 */
@Service
//@FeignClient(value = "academy-oss", fallback = OssFeignService.class)
@FeignClient("academy-oss")
public interface OssFeignService {

    @ApiOperation(value = "文件删除")
    @DeleteMapping("/oss/remove/file")
    R removeAvatar(
            @ApiParam(value = "删除文件url路径", required = true)
            @RequestBody String url);
}

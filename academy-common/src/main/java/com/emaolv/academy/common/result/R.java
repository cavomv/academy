package com.emaolv.academy.common.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: liu jia
 * @description: 统一结果类
 * @date: Created in 2021/5/11 17:54
 */
@Data
@ApiModel(value = "全局统一返回结果")
public class R {

    @ApiModelProperty(value = "是否成功")
    private Boolean success;
    @ApiModelProperty(value = "返回码")
    private Integer code;
    @ApiModelProperty(value = "返回消息")
    private String message;

    private Map<String, Object> data = new HashMap<>();

    /**
     * 构造函数私有化
     */
    private R(){}

    /**
     * 返回成功结果
     * @return
     */
    public static R success(){
        R r = new R();
        r.setCode(ResponseEnum.SUCCESS.getCode());
        r.setMessage(ResponseEnum.SUCCESS.getMessage());
        r.setSuccess(ResponseEnum.SUCCESS.getSuccess());
        return r;
    }

    /**
     * 返回失败结果
     * @return
     */
    public static R fail(){
        R r = new R();
        r.setCode(ResponseEnum.Fail.getCode());
        r.setMessage(ResponseEnum.Fail.getMessage());
        return r;
    }

    /**
     * 设置特定的结果
     * @param responseEnum
     * @return
     */
    public static R setResult(ResponseEnum responseEnum){
        R r = new R();
        r.setCode(responseEnum.getCode());
        r.setMessage(responseEnum.getMessage());
        return r;
    }

    public R data(String key, Object value){
        this.data.put(key, value);
        return this;
    }

    public R data(Map<String, Object> map){
        this.setData(map);
        return this;
    }
    /**
     * 返回特定的响应消息
     */
    public R message(String message){
        this.setMessage(message);
        return this;
    }

    /**
     * 设置特定的响应码
     * @param code
     * @return
     */
    public R code(Integer code){
        this.setCode(code);
        return this;
    }

    public R success(Boolean success){
        this.setSuccess(success);
        return this;
    }
}

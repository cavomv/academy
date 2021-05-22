package com.emaolv.academy.common.exception;

import com.emaolv.academy.common.result.ResponseEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: liu jia
 * @description: 自定义异常
 * @date: Created in 2021/5/12 13:05
 */
@Data
@NoArgsConstructor
public class CustomizeException extends RuntimeException{

    // 状态码
    private Integer code;
    // 错误消息
    private String message;

    /**
     *
     * @param messaeg 错误消息
     */
    public CustomizeException(String messaeg){
        this.message = messaeg;
    }

    /**
     *
     * @param message 错误消息
     * @param code 错误码
     */
    public CustomizeException(Integer code, String message){
        this.message = message;
        this.code = code;
    }

    /**
     *
     * @param message 错误消息
     * @param code 错误码
     * @param cause 原始异常对象
     */
    public CustomizeException(String message, Integer code, Throwable cause){
        super(cause);

        this.message = message;
        this.code = code;
    }

    /**
     *
     * @param resultCodeEnum 接收枚举类型
     */
    public CustomizeException(ResponseEnum resultCodeEnum){
        this.message = resultCodeEnum.getMessage();
        this.code = resultCodeEnum.getCode();
    }

    /**
     *
     * @param resultCodeEnum 接收枚举类型
     * @param cause 原始异常对象
     */
    public CustomizeException(ResponseEnum resultCodeEnum, Throwable cause){
        super(cause);
        this.message = resultCodeEnum.getMessage();
        this.code = resultCodeEnum.getCode();
    }

}

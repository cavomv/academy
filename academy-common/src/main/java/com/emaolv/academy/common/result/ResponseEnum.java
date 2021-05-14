package com.emaolv.academy.common.result;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;


/**
 * @author: liu jia
 * @description: 定义返回结果
 * @date: Created in 2021/5/11 17:45
 */

@Getter
@ToString
@AllArgsConstructor
public enum ResponseEnum {

    SUCCESS(20000, "成功"),
    Fail(20001,"内部错误"),

    //-1xx 服务器错误
    BAD_SQL_GRAMMAR_ERROR(-101, "sql语法错误"),
    SERVLET_ERROR(-102, "servlet请求异常"), //-2xx 参数校验
    UPLOAD_ERROR(-103, "文件上传错误"),
    EXPORT_DATA_ERROR(104, "数据导出失败"),


    //-2xx 参数校验
    MOBILE_NULL_ERROR(-202, "手机号码不能为空"),
    MOBILE_ERROR(-203, "手机号码不正确"),
    PASSWORD_NULL_ERROR(204, "密码不能为空"),
    CODE_NULL_ERROR(205, "验证码不能为空"),
    CODE_ERROR(206, "验证码错误"),
    MOBILE_EXIST_ERROR(207, "手机号已被注册"),
    LOGIN_MOBILE_ERROR(208, "用户不存在"),
    LOGIN_PASSWORD_ERROR(209, "密码错误"),
    LOGIN_LOKED_ERROR(210, "用户被锁定"),
    LOGIN_AUTH_ERROR(-211, "未登录"),


    USER_BIND_IDCARD_EXIST_ERROR(-301, "身份证号码已被其他账号绑定"),
    USER_NO_BIND_ERROR(302, "用户未绑定"),
    USER_NO_AMOUNT_ERROR(303, "用户信息未审核"),
    REMOVE_ERROR(304,"该记录已删除"),
    ARITHMETIC_EXCEPTION_ERROR(305, "除数为整数时不能为0");

//    ALIYUN_RESPONSE_ERROR(-501, "阿里云短信服务响应失败"),
//    ALIYUN_SMS_LIMIT_CONTROL_ERROR(-502, "短信发送过于频繁"),//业务限流
//    ALIYUN_SMS_ERROR(-503, "短信发送失败"),//其他失败
//
//    WEIXIN_CALLBACK_PARAM_ERROR(-601, "回调参数不正确"),
//    WEIXIN_FETCH_ACCESSTOKEN_ERROR(-602, "获取access_token失败"),
//    WEIXIN_FETCH_USERINFO_ERROR(-603, "获取用户信息失败"),;

    ;
    // 响应状态码
    private Integer code;
    // 响应信息
    private String message;

}

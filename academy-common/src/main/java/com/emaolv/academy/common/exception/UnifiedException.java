package com.emaolv.academy.common.exception;

import com.emaolv.academy.common.result.R;
import com.emaolv.academy.common.result.ResponseEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author: liu jia
 * @description: 统一异常处理器
 * @date: Created in 2021/5/12 12:11
 */
@Slf4j
@RestControllerAdvice // 相当于异常切面
public class UnifiedException {

    // 不灵活
    @ExceptionHandler(value=Exception.class)
    public R unifiedException(Exception e){
        e.printStackTrace();
        return R.fail();
    }

    // 繁琐
    @ExceptionHandler(value = BadSqlGrammarException.class)
    public R unifiedException(BadSqlGrammarException e){
        e.printStackTrace();
        return R.setResult(ResponseEnum.BAD_SQL_GRAMMAR_ERROR);
    }

    @ExceptionHandler(value = ArithmeticException.class)
    public R unifiedException(ArithmeticException e){
        e.printStackTrace();
        return R.setResult(ResponseEnum.ARITHMETIC_EXCEPTION_ERROR);
    }

    @ExceptionHandler(value = CustomizeException.class)
    public R unifiedException(CustomizeException e){
        e.printStackTrace();
        return R.setResult(ResponseEnum.REMOVE_ERROR);
    }
}

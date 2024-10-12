package com.hjc.handler;

import com.hjc.entity.resp.Result;
import com.hjc.exception.BusinessException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理类
 *
 * @author hjc
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Result<Void> error(Exception e){
        e.printStackTrace();
        return Result.fail();
    }

    @ResponseBody
    @ExceptionHandler(ArithmeticException.class)
    public Result<Object> error(ArithmeticException e){
        e.printStackTrace();
        return Result.fail().message("执行了特定异常处理");
    }

    @ResponseBody
    @ExceptionHandler(BusinessException.class)
    public Result error(BusinessException e){
        e.printStackTrace();
        return Result.fail().message(e.getMessage()).code(e.getCode());
    }
}
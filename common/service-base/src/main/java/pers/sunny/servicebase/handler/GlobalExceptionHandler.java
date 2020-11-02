package pers.sunny.servicebase.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.sunny.commonutils.Result;
import pers.sunny.servicebase.exception.GuliException;

/**
 * @Description
 * @Author Sunny
 * @Version 1.0
 * @Date 2020-09-25-19:58
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler{
    //指定出什么异常时进行处理
    @ExceptionHandler(Exception.class)
    //为了返回数据
    @ResponseBody
    public Result error(Exception e){
        e.printStackTrace();
        log.error(e.getMessage());
        return Result.error().message("出现 '" + e.getMessage() + "' 异常");
    }

    //自定义异常
    @ExceptionHandler(GuliException.class)
    @ResponseBody
    public Result error(GuliException e){
        e.printStackTrace();
        log.error(e.getMessage());
        return Result.error().message(e.getMsg());
    }
}

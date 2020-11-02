package pers.sunny.eduservice.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import pers.sunny.commonutils.Result;

/**
 * @Description
 * @Author Sunny
 * @Version 1.0
 * @Date 2020-09-26-17:52
 */
@RestController
@RequestMapping("/eduservice/user")
@Api(tags = "登录管理")
public class EduLoginController {

    @PostMapping("/login")
    public Result login(){
        return Result.ok().data("token","admin");
    }

    @GetMapping("/info")
    public Result info(){
        return Result.ok().data("roles","important").data("name","sunny").data("avater","avater");
    }

}

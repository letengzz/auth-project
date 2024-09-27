package com.hjc.controller;

import com.hjc.entity.resp.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试控制器
 * @author hjc
 */
@RestController
@RequestMapping("/test")
@Api(tags = "测试")
public class TestController {
    @ApiOperation(value = "查询所有")
    @GetMapping("findAll")
    public Result<String> findAll() {
        String name = "admin";
        return Result.ok(name);
    }
    @GetMapping("/err")
    public Result<String> err() {
        int a = 10/0;
        return Result.ok("err");
    }
}
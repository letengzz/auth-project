package com.hjc.controller;

import com.hjc.entity.resp.Result;
import com.hjc.exception.BusinessException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
//        int a = 10/0;
        try {
            int a = 10/0;
        }catch(Exception e) {
            throw new BusinessException(20001,"出现自定义异常");
        }
        return Result.ok("err");
    }
}
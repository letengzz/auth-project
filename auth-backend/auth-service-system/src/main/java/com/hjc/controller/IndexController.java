package com.hjc.controller;

import com.hjc.entity.resp.Result;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 后台登录登出
 * </p>
 * @author hjc
 */
@Api(tags = "后台登录管理")
@RestController
@RequestMapping("/api/system/index")
public class IndexController {
    /**
     * 登录
     * @return
     */
    @PostMapping("/login")
    public Result<Map<String, Object>> login() {
        Map<String, Object> map = new HashMap<>();
        map.put("token","admin");
        return Result.ok(map);
    }
    /**
     * 获取用户信息
     * @return
     */
    @GetMapping("/info")
    public Result<Map<String, Object>> info() {
        Map<String, Object> map = new HashMap<>();
        map.put("roles","[admin]");
        map.put("name","admin");
        map.put("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        return Result.ok(map);
    }
    /**
     * 退出
     * @return
     */
    @PostMapping("/logout")
    public Result<Void> logout(){
        return Result.ok();
    }
}
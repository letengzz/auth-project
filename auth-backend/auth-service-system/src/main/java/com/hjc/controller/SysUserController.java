package com.hjc.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hjc.entity.resp.Result;
import com.hjc.model.SysUser;
import com.hjc.model.vo.AssignRoleVo;
import com.hjc.model.vo.SysUserQueryVo;
import com.hjc.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 用户管理
 * </p>
 * @author hjc
 */
@RestController
@RequestMapping("/api/system/sysUser")
@Api(tags = "用户管理")
public class SysUserController {
    @Resource
    private SysUserService sysUserService;


    @ApiOperation(value = "获取用户列表")
    @GetMapping("/{page}/{limit}")
    public Result<PageInfo<SysUser>> list(
            @ApiParam(value = "当前页码", required = true)
            @PathVariable Integer page,
            @ApiParam(value = "每页记录数", required = true)
            @PathVariable Integer limit,
            @ApiParam(value = "查询条件", required = false)
            SysUserQueryVo sysUserQueryVo) {
        PageHelper.startPage(page, limit);
        List<SysUser> users =  sysUserService.selectForName(page, limit, sysUserQueryVo);
        return Result.ok(new PageInfo<>(users,5));
    }
    @ApiOperation(value = "添加用户")
    @PostMapping("/save")
    public Result<Object> save(@RequestBody SysUser sysUser) {
        boolean isSuccess = sysUserService.save(sysUser);
        if (isSuccess) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }
    @ApiOperation("根据id查询角色")
    @GetMapping("findUserById/{id}")
    public Result<SysUser> findRoleById(@PathVariable String id) {
        SysUser sysUser = sysUserService.getById(id);
        return Result.ok(sysUser);
    }
    @ApiOperation(value = "更新用户")
    @PutMapping("/update")
    public Result<Object> updateById(@RequestBody SysUser sysUser) {
        boolean isSuccess = sysUserService.updateById(sysUser);
        if (isSuccess) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }
    @ApiOperation(value = "删除用户")
    @DeleteMapping("/remove/{id}")
    public Result remove(@PathVariable String id) {
        boolean isSuccess = sysUserService.removeById(id);
        if(isSuccess){
            return Result.ok();
        }else {
            return Result.fail();
        }
    }
    @ApiOperation(value = "更新状态")
    @GetMapping("updateStatus/{id}/{status}")
    public Result updateStatus(@PathVariable String id, @PathVariable Byte status) {
        boolean isSuccess = sysUserService.updateStatus(id, status);
        if (isSuccess) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }
}

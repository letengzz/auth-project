package com.hjc.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hjc.entity.resp.Result;
import com.hjc.model.SysRole;
import com.hjc.model.vo.SysRoleQueryVo;
import com.hjc.service.SysRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 角色管理
 * @author hjc
 */
@RestController
@RequestMapping("/api/system/sysRole")
@Api(tags = "角色管理")
public class SysRoleController {
    @Resource
    private SysRoleService sysRoleService;

    @ApiOperation(value = "获取分页角色列表")
    @GetMapping("/{page}/{limit}")
    public Result<Object> list(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Integer page,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Integer limit,
            @ApiParam(name = "roleQueryVo", value = "查询对象", required = false)
            SysRoleQueryVo roleQueryVo) {
        PageHelper.startPage(page,limit);
        List<SysRole> roles = sysRoleService.selectForName(roleQueryVo);
        return Result.ok(new PageInfo<>(roles,5));
    }

    @ApiOperation("添加角色")
    @PostMapping("save")
    public Result<Object> saveRole(@RequestBody SysRole sysRole){
        boolean isSuccess = sysRoleService.save(sysRole);
        if(isSuccess) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

    @ApiOperation("根据id查询角色")
    @GetMapping("findRoleById/{id}")
    public Result<SysRole> getRoleById(@PathVariable String id){
        SysRole sysRole = sysRoleService.getById(id);
        return Result.ok(sysRole);
    }

    @ApiOperation("修改角色")
    @PutMapping("update")
    public Result<Object> updateRole(@RequestBody SysRole sysRole){
        boolean isSuccess = sysRoleService.updateById(sysRole);
        if(isSuccess) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

    @ApiOperation("根据id删除角色")
    @DeleteMapping("remove/{id}")
    public Result<Object> removeRole(@PathVariable String id){
        //调用方法删除
        boolean isSuccess = sysRoleService.removeById(id);
        if(isSuccess) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

    @ApiOperation("批量删除")
    @DeleteMapping("batchRemove")
    public Result batchRemove(@RequestBody List<String> ids) {
        boolean isSuccess = sysRoleService.removeByIds(ids);
        if(isSuccess) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

}

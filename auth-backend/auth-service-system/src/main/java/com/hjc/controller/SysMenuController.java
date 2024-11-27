package com.hjc.controller;

import com.hjc.entity.resp.Result;
import com.hjc.model.SysMenu;
import com.hjc.model.vo.AssignMenuVo;
import com.hjc.service.SysMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 菜单管理
 * </p>
 * @author hjc
 */
@RestController
@RequestMapping("/api/system/sysMenu/")
@Api(tags = "菜单管理")
public class SysMenuController {
    @Resource
    private SysMenuService sysMenuService;

    @ApiOperation(value = "获取菜单")
    @GetMapping("findNodes")
    public Result<List<SysMenu>> findNodes() {
        List<SysMenu> list = sysMenuService.findNodes();
        return Result.ok(list);
    }

    @ApiOperation("新增菜单")
    @PostMapping("save")
    public Result<Void> save(@RequestBody SysMenu permission) {
        boolean isSuccess = sysMenuService.save(permission);
        if (isSuccess) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

    @ApiOperation(value = "根据id查询菜单详情")
    @GetMapping("findNode/{id}")
    public Result<SysMenu> findNode(@PathVariable String id) {
        SysMenu sysMenu = sysMenuService.getById(id);
        return Result.ok(sysMenu);
    }

    @ApiOperation("修改菜单")
    @PutMapping("update")
    public Result<Void> update(@RequestBody SysMenu permission) {
        boolean isSuccess = sysMenuService.updateById(permission);
        if (isSuccess) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

    @ApiOperation("删除菜单")
    @DeleteMapping("remove/{id}")
    public Result<Void> remove(@PathVariable("id") String id) {
        boolean isSuccess = sysMenuService.removeById(id);
        if (isSuccess) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

    @ApiOperation("根据角色获取菜单")
    @GetMapping("/toAssign/{roleId}")
    public Result<List<SysMenu>> toAssign(@PathVariable String roleId) {
        List<SysMenu> sysMenus = sysMenuService.findSysMenuByRoleId(roleId);
        return Result.ok(sysMenus);
    }

    @ApiOperation("给角色分配菜单")
    @PostMapping("doAssign")
    public Result<Void> doAssign(@RequestBody AssignMenuVo assignMenuVo){
        boolean isSuccess = sysMenuService.doAssign(assignMenuVo);
        if (isSuccess) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }


}
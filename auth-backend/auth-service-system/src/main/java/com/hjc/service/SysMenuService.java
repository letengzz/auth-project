package com.hjc.service;

import com.hjc.model.SysMenu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hjc.model.vo.AssignMenuVo;

import java.util.List;

/**
* @author hjc
* @description 针对表【sys_menu(菜单表)】的数据库操作Service
*/
public interface SysMenuService extends IService<SysMenu> {

    List<SysMenu> findNodes();


    /**
     * 根据角色获取授权权限数据
     * @return
     */
    List<SysMenu> findSysMenuByRoleId(String roleId);

    /**
     * 保存角色权限
     * @param  assignMenuVo
     */
    boolean doAssign(AssignMenuVo assignMenuVo);
}

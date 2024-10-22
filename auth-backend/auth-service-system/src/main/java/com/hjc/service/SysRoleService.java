package com.hjc.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.hjc.model.SysRole;
import com.hjc.model.vo.AssignRoleVo;
import com.hjc.model.vo.SysRoleQueryVo;

import java.util.List;
import java.util.Map;

/**
* @author hjc
* @description 针对表【sys_role(角色)】的数据库操作Service
* @createDate 2024-10-13 16:35:09
*/
public interface SysRoleService extends IService<SysRole> {

    List<SysRole> selectForName(SysRoleQueryVo roleQueryVo);

    /**
     * 根据用户获取角色数据
     * @param userId
     * @return
     */
    Map<String, Object> getRolesByUserId(Long userId);

    /**
     * 分配角色
     * @param assignRoleVo
     */
    boolean doAssign(AssignRoleVo assignRoleVo);
}

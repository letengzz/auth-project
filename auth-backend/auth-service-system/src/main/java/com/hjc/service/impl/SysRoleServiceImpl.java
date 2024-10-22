package com.hjc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hjc.mapper.SysUserRoleMapper;
import com.hjc.model.SysRole;
import com.hjc.model.SysUserRole;
import com.hjc.model.vo.AssignRoleVo;
import com.hjc.model.vo.SysRoleQueryVo;
import com.hjc.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hjc.mapper.SysRoleMapper;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author hjc
 * @description 针对表【sys_role(角色)】的数据库操作Service实现
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {
    @Resource
    private SysRoleMapper sysRoleMapper;

    @Override
    public List<SysRole> selectForName(SysRoleQueryVo roleQueryVo) {
        return sysRoleMapper.selectForName(roleQueryVo.getRoleName());
    }

    @Resource
    private SysUserRoleMapper sysUserRoleMapper;

    @Override
    public Map<String, Object> getRolesByUserId(Long userId) {
        //获取所有角色
        List<SysRole> roles = sysRoleMapper.selectList(null);
        //根据用户id查询
        LambdaQueryWrapper<SysUserRole> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUserRole::getUserId, userId);
        //获取用户已分配的角色
        List<SysUserRole> userRoles = sysUserRoleMapper.selectList(queryWrapper);
        //获取用户已分配的角色id
        List<Long> userRoleIds = new ArrayList<>();
        for (SysUserRole userRole : userRoles) {
            userRoleIds.add(userRole.getRoleId());
        }
        //创建返回的Map
        Map<String, Object> returnMap = new HashMap<>();
        returnMap.put("allRoles", roles);
        returnMap.put("userRoleIds", userRoleIds);
        return returnMap;
    }

    @Transactional
    @Override
    public boolean doAssign(AssignRoleVo assignRoleVo) {
        boolean isOk = true;
        //根据用户id删除原来分配的角色
        LambdaQueryWrapper<SysUserRole> queryWrapper = new LambdaQueryWrapper<SysUserRole>();
        queryWrapper.eq(SysUserRole::getUserId, assignRoleVo.getUserId());
        sysUserRoleMapper.delete(queryWrapper);
        //获取所有的角色id
        List<Long> roleIdList = assignRoleVo.getRoleIdList();
        for (Long roleId : roleIdList) {
            if (roleId != null) {
                SysUserRole sysUserRole = new SysUserRole();
                sysUserRole.setRoleId(roleId);
                sysUserRole.setUserId(assignRoleVo.getUserId());
                //保存
                int insert = sysUserRoleMapper.insert(sysUserRole);
//                isOk = (isOk && sysUserRoleMapper.insert(sysUserRole) == 1);
            }
        }
        return isOk;
    }
}





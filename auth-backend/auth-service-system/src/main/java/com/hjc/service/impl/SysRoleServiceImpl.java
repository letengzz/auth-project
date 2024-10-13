package com.hjc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hjc.model.SysRole;
import com.hjc.model.vo.SysRoleQueryVo;
import com.hjc.service.SysRoleService;
import org.springframework.stereotype.Service;
import com.hjc.mapper.SysRoleMapper;

import javax.annotation.Resource;
import java.util.List;

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
}





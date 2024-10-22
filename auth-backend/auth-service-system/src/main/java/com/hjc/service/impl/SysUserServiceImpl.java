package com.hjc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hjc.model.SysUser;
import com.hjc.model.vo.SysUserQueryVo;
import com.hjc.service.SysUserService;
import com.hjc.mapper.SysUserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author hjc
 * @description 针对表【sys_user(用户表)】的数据库操作Service实现
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser>
        implements SysUserService {

    @Resource
    private SysUserMapper sysUserMapper;

    @Override
    public List<SysUser> selectForName(Integer page, Integer limit, SysUserQueryVo sysUserQueryVo) {
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        if (sysUserQueryVo.getKeyword() != null) {
            wrapper.like(SysUser::getUsername, sysUserQueryVo.getKeyword())
                    .or().like(SysUser::getName, sysUserQueryVo.getKeyword())
                    .or().like(SysUser::getPhone, sysUserQueryVo.getKeyword());
        }
        if (sysUserQueryVo.getCreateTimeBegin()!= null) {
            wrapper.gt(SysUser::getCreateTime, sysUserQueryVo.getCreateTimeBegin());
        }
        if (sysUserQueryVo.getCreateTimeEnd()!= null) {
            wrapper.lt(SysUser::getCreateTime, sysUserQueryVo.getCreateTimeEnd());
        }
        wrapper.orderByDesc(SysUser::getId);
        return this.baseMapper.selectList(wrapper);
    }

    @Override
    public boolean updateStatus(String id, Byte status) {
        SysUser sysUser = sysUserMapper.selectById(id);
        sysUser.setStatus(status);
        return sysUserMapper.updateById(sysUser) == 1;
    }
}





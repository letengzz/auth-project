package com.hjc.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.hjc.model.SysUser;
import com.hjc.model.vo.SysUserQueryVo;

import java.util.List;

/**
* @author hjc
* @description 针对表【sys_user(用户表)】的数据库操作Service
*/
public interface SysUserService extends IService<SysUser> {

    List<SysUser> selectForName(Integer page, Integer limit, SysUserQueryVo sysUserQueryVo);

    boolean updateStatus(String id, Byte status);
}

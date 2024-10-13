package com.hjc.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hjc.model.SysRole;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @author hjc
* @description 针对表【sys_role(角色)】的数据库操作Mapper
* @Entity generator.domain.SysRole
*/
public interface SysRoleMapper extends BaseMapper<SysRole> {

    List<SysRole> selectForName(@Param("roleName") String roleName);
}





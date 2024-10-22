package com.hjc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hjc.model.SysMenu;
import com.hjc.service.SysMenuService;
import com.hjc.mapper.SysMenuMapper;
import org.springframework.stereotype.Service;

/**
* @author hjc
* @description 针对表【sys_menu(菜单表)】的数据库操作Service实现
*/
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu>
    implements SysMenuService{

}





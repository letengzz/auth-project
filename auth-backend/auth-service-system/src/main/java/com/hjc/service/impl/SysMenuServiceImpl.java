package com.hjc.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hjc.entity.resp.ResultCodeEnum;
import com.hjc.exception.BusinessException;
import com.hjc.mapper.SysRoleMenuMapper;
import com.hjc.model.SysMenu;
import com.hjc.model.SysRoleMenu;
import com.hjc.model.vo.AssignMenuVo;
import com.hjc.service.SysMenuService;
import com.hjc.mapper.SysMenuMapper;
import com.hjc.utils.MenuHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
* @author hjc
* @description 针对表【sys_menu(菜单表)】的数据库操作Service实现
*/
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu>
    implements SysMenuService{

    @Override
    public List<SysMenu> findNodes() {
        //全部权限列表
        List<SysMenu> sysMenuList = baseMapper.selectList(null);
        if (CollectionUtils.isEmpty(sysMenuList)) {
            return null;
        }
        //构建树形数据
        return MenuHelper.buildTree(sysMenuList);
    }

    @Resource
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Override
    public List<SysMenu> findSysMenuByRoleId(String roleId) {
        //获取所有status为1的权限列表
        List<SysMenu> menuList = this.baseMapper.selectList(new LambdaQueryWrapper<SysMenu>().eq(SysMenu::getStatus, 1));

        //根据角色id获取角色权限
        List<SysRoleMenu> roleMenus = sysRoleMenuMapper.selectList(new LambdaQueryWrapper<SysRoleMenu>().eq(SysRoleMenu::getRoleId, roleId));

        //获取该角色已分配的所有权限id
        List<Long> roleMenuIds = new ArrayList<>();
        for (SysRoleMenu roleMenu : roleMenus) {
            roleMenuIds.add(roleMenu.getMenuId());
        }
        //遍历所有权限列表
        for (SysMenu sysMenu : menuList) {
            //设置该权限已被分配
            sysMenu.setSelect(roleMenuIds.contains(sysMenu.getId()));
        }
        //将权限列表转换为权限树
        return MenuHelper.buildTree(menuList);
    }

    @Transactional
    @Override
    public boolean doAssign(AssignMenuVo assignMenuVo) {
        //删除已分配的权限
        int deleteCount = sysRoleMenuMapper.delete(new LambdaQueryWrapper<SysRoleMenu>().eq(SysRoleMenu::getRoleId, assignMenuVo.getRoleId()));

        int insertCount = 0;
        //遍历所有已选择的权限id
        for(Long menuId : assignMenuVo.getMenuIdList()){
            if(menuId != null){
                //创建SysRoleMenu对象
                SysRoleMenu sysRoleMenu = new SysRoleMenu();
                sysRoleMenu.setMenuId(menuId);
                sysRoleMenu.setRoleId(assignMenuVo.getRoleId());
                //添加新权限
                insertCount = sysRoleMenuMapper.insert(sysRoleMenu);
            }
        }
        return deleteCount > 0 && insertCount > 0;
    }

    @Transactional
    @Override
    public boolean removeById(Serializable id) {
        long count = this.count(new LambdaQueryWrapper<SysMenu>().eq(SysMenu::getParentId, id));
        if (count > 0) {
            throw new BusinessException(ResultCodeEnum.NODE_ERROR);
        }
        return super.removeById(id);
    }
}





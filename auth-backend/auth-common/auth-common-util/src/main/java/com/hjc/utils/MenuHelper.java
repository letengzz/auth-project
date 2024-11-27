package com.hjc.utils;

import com.hjc.model.SysMenu;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 根据菜单数据构建菜单树的工具类
 * </p>
 *
 * @author hjc
 */
public class MenuHelper {
    /**
     * 使用递归方法建菜单
     * @param sysMenuList
     * @return
     */
    public static List<SysMenu> buildTree(List<SysMenu> sysMenuList) {
        ArrayList<SysMenu> trees = new ArrayList<>();
        for(SysMenu sysMenu : sysMenuList){
            if (sysMenu.getParentId().intValue() == 0) {
                trees.add(findChildren(sysMenu,sysMenuList));
            }
        }
        return trees;
    }
    /**
     * 递归查找子节点
     * @param treeNodes
     * @return
     */
    private static SysMenu findChildren(SysMenu sysMenu, List<SysMenu> treeNodes) {
        sysMenu.setChildren(new ArrayList<SysMenu>());
        for(SysMenu it : treeNodes){
            if(it.getParentId().intValue() == sysMenu.getId().intValue()){
                if (sysMenu.getChildren() == null) {
                    sysMenu.setChildren(new ArrayList<>());
                }
                sysMenu.getChildren().add(findChildren(it,treeNodes));
            }
        }
        return sysMenu;
    }
}
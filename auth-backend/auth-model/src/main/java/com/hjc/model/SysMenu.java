package com.hjc.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hjc.model.base.BaseEntity;
import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 菜单表
 * @author hjc
 * @TableName sys_menu
 */
@Data
@TableName(value ="sys_menu")
@EqualsAndHashCode(callSuper = true)
public class SysMenu extends BaseEntity implements Serializable {
    /**
     * 所属上级
     */
    private Long parentId;

    /**
     * 名称
     */
    private String name;

    /**
     * 类型(0:目录,1:菜单,2:按钮)
     */
    private Integer type;

    /**
     * 路由地址
     */
    private String path;

    /**
     * 组件路径
     */
    private String component;

    /**
     * 权限标识
     */
    private String perms;

    /**
     * 图标
     */
    private String icon;

    /**
     * 排序
     */
    private Integer sortValue;

    /**
     * 状态(0:禁止,1:正常)
     */
    private Byte status;

    // 下级列表
    @TableField(exist = false)
    private List<SysMenu> children;
    //是否选中
    @TableField(exist = false)
    private boolean isSelect;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
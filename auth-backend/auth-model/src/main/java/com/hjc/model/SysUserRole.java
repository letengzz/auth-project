package com.hjc.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hjc.model.base.BaseEntity;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户角色
 * @author hjc
 * @TableName sys_user_role
 */
@Data
@TableName(value ="sys_user_role")
@EqualsAndHashCode(callSuper = true)
public class SysUserRole extends BaseEntity implements Serializable {
    /**
     * 角色id
     */
    private Long roleId;

    /**
     * 用户id
     */
    private Long userId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
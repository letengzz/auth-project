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
 * 用户表
 * @author hjc
 * @TableName sys_user
 */
@Data
@TableName(value ="sys_user")
@EqualsAndHashCode(callSuper = true)
public class SysUser extends BaseEntity implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 姓名
     */
    private String name;

    /**
     * 手机
     */
    private String phone;

    /**
     * 头像地址
     */
    private String headUrl;

    /**
     * 部门id
     */
    private Long deptId;

    /**
     * 岗位id
     */
    private Long postId;

    /**
     * 描述
     */
    private String description;

    /**
     * 状态（1：正常 0：停用）
     */
    private Byte status;
}
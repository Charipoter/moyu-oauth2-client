package com.moyu.oauth2.client.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName role_permission
 */
@TableName(value ="role_permission")
@Data
public class RolePermission implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 角色
     */
    @TableField(value = "role_id")
    private Long roleId;

    /**
     * 权限
     */
    @TableField(value = "permission_id")
    private Long permissionId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
package com.moyu.oauth2.client.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 
 * @TableName user_auth_info
 */
@TableName(value ="user_auth_info")
@Data
public class UserAuthInfo implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 关联的用户
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 授权的类型(手机号/邮箱/第三方应用)
     */
    @TableField(value = "auth_type")
    private String authType;

    /**
     * 用户名/手机号/邮箱/第三方的唯一标识
     */
    @TableField(value = "auth_principal")
    private String authPrincipal;

    /**
     * 密码凭证 (自建账号的保存密码, 第三方的保存 token)
     */
    @TableField(value = "auth_credential")
    private String authCredential;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
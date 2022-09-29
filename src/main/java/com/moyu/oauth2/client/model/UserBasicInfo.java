package com.moyu.oauth2.client.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 
 * @TableName user_basic_info
 */
@TableName(value ="user_basic_info")
@Data
public class UserBasicInfo implements Serializable {
    /**
     * 本系统用户唯一标识
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 用户昵称
     */
    @TableField(value = "nickname")
    private String nickname;

    /**
     * 头像uri
     */
    @TableField(value = "avatar")
    private String avatar;

    /**
     * 额外信息，用于补充
     */
    @TableField(value = "more")
    private String more;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
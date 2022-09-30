package com.moyu.oauth2.client.manager.login.convert.attribute.key;

/**
 * 实现一定要仔细，否则会导致业务异常
 */
public interface UserInfoKeyProvider {

    /**
     * 唯一标识：第三方的用户 id，本地的用户名
     */
    String getPrincipalKey();
    /**
     * 昵称
     */
    String getNicknameKey();
    /**
     * 头像
     */
    String getAvatarKey();
    /**
     * 凭证，第三方的 token，本地的加密后密码
     */
    String getCredentialKey();

    UserInfoKeyProviderType getType();

}

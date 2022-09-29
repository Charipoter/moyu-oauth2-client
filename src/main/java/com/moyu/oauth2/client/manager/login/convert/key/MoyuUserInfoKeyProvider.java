package com.moyu.oauth2.client.manager.login.convert.key;

import org.springframework.stereotype.Component;

@Component
public class MoyuUserInfoKeyProvider implements UserInfoKeyProvider {
    @Override
    public String getPrincipalKey() {
        return "username";
    }

    @Override
    public String getNicknameKey() {
        return "nickname";
    }

    @Override
    public String getAvatarKey() {
        return "avatar";
    }

    @Override
    public String getCredentialKey() {
        return "password";
    }

    @Override
    public UserInfoKeyProviderType getType() {
        return UserInfoKeyProviderType.LOCAL;
    }
}

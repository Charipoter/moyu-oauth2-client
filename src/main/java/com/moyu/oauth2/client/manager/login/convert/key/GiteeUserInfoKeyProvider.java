package com.moyu.oauth2.client.manager.login.convert.key;

import org.springframework.stereotype.Component;

@Component
public class GiteeUserInfoKeyProvider extends AbstractThirdPartyUserInfoKeyProvider {
    @Override
    public String getPrincipalKey() {
        return "id";
    }

    @Override
    public String getNicknameKey() {
        return "name";
    }

    @Override
    public String getAvatarKey() {
        return "avatar_url";
    }
}

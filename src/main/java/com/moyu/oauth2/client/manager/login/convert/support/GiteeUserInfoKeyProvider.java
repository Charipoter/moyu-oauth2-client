package com.moyu.oauth2.client.manager.login.convert.support;

import com.moyu.oauth2.client.manager.login.convert.AttributesBasedUserInfoKeyProvider;
import org.springframework.stereotype.Component;

@Component
public class GiteeUserInfoKeyProvider implements AttributesBasedUserInfoKeyProvider {
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

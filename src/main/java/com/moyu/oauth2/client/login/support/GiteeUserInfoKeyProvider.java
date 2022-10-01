package com.moyu.oauth2.client.login.support;

import com.moyu.oauth2.client.login.convert.attribute.key.AbstractThirdPartyUserInfoKeyProvider;
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

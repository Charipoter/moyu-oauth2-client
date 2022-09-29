package com.moyu.oauth2.client.manager.login.convert.key;

import org.springframework.stereotype.Component;

@Component
public class GithubUserInfoKeyProvider extends AbstractThirdPartyUserInfoKeyProvider {

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

    @Override
    public UserInfoKeyProviderType getType() {
        return UserInfoKeyProviderType.THIRD_PARTY;
    }

}

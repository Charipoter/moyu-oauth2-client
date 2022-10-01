package com.moyu.oauth2.client.login.convert.attribute.key;

public abstract class AbstractThirdPartyUserInfoKeyProvider implements UserInfoKeyProvider {
    @Override
    public String getCredentialKey() {
        return "";
    }

    @Override
    public UserInfoKeyProviderType getType() {
        return UserInfoKeyProviderType.THIRD_PARTY;
    }
}

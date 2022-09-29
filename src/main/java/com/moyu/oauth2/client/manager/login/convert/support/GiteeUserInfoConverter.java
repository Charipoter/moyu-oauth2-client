package com.moyu.oauth2.client.manager.login.convert.support;

import com.moyu.oauth2.client.manager.login.convert.AbstractGeneralUserInfoConverter;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;

public class GiteeUserInfoConverter extends AbstractGeneralUserInfoConverter {

    public GiteeUserInfoConverter(OAuth2AuthorizedClientService authorizedClientService) {
        super(authorizedClientService);
    }

    @Override
    protected String getPrincipalKey() {
        return "name";
    }

    @Override
    protected String getNicknameKey() {
        return "name";
    }

    @Override
    protected String getAvatarKey() {
        return "avatar_url";
    }
}

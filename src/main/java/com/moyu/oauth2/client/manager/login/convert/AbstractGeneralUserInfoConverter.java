package com.moyu.oauth2.client.manager.login.convert;

import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;

public abstract class AbstractGeneralUserInfoConverter extends AbstractThirdPartyCredentialUserInfoConverter{

    protected AbstractGeneralUserInfoConverter(OAuth2AuthorizedClientService authorizedClientService) {
        super(authorizedClientService);
    }
}

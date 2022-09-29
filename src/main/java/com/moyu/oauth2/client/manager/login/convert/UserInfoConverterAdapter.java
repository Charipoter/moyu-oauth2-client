package com.moyu.oauth2.client.manager.login.convert;

import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;

public class UserInfoConverterAdapter extends AbstractThirdPartyCredentialUserInfoConverter {
    public UserInfoConverterAdapter(OAuth2AuthorizedClientService authorizedClientService,
                                    AttributesBasedUserInfoKeyProvider keyProvider) {

        super(authorizedClientService, keyProvider);
    }
}

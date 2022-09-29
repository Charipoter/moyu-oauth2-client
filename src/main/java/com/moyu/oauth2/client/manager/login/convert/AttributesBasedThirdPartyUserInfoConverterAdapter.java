package com.moyu.oauth2.client.manager.login.convert;

import com.moyu.oauth2.client.manager.login.convert.key.UserInfoKeyProvider;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;

public class AttributesBasedThirdPartyUserInfoConverterAdapter extends AbstractThirdPartyCredentialUserInfoConverter {
    public AttributesBasedThirdPartyUserInfoConverterAdapter(OAuth2AuthorizedClientService authorizedClientService,
                                                             UserInfoKeyProvider keyProvider) {

        super(authorizedClientService, keyProvider);
    }
}

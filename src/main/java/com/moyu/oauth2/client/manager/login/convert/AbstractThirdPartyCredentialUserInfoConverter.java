package com.moyu.oauth2.client.manager.login.convert;

import com.moyu.oauth2.client.manager.context.OAuth2LoginPostProcessorContext;
import com.moyu.oauth2.client.manager.login.convert.key.UserInfoKeyProvider;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;

public abstract class AbstractThirdPartyCredentialUserInfoConverter extends AbstractAttributesBasedUserInfoConverter {

    private OAuth2AuthorizedClientService authorizedClientService;

    AbstractThirdPartyCredentialUserInfoConverter(OAuth2AuthorizedClientService authorizedClientService,
                                                  UserInfoKeyProvider keyProvider) {

        super(keyProvider);
        this.authorizedClientService = authorizedClientService;
    }
    @Override
    protected String resolveCredential(OAuth2LoginPostProcessorContext context) {
        OAuth2AuthorizedClient client = authorizedClientService.loadAuthorizedClient(context.getAuthType(), context.getPrincipal());
        return client.getAccessToken().getTokenValue();
    }
}

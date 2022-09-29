package com.moyu.oauth2.client.manager.login.convert;

import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;

public abstract class AbstractThirdPartyCredentialUserInfoConverter extends AbstractAttributesBasedUserInfoConverter {

    private OAuth2AuthorizedClientService authorizedClientService;

    AbstractThirdPartyCredentialUserInfoConverter(OAuth2AuthorizedClientService authorizedClientService,
                                                  AttributesBasedUserInfoKeyProvider keyProvider) {

        super(keyProvider);
        this.authorizedClientService = authorizedClientService;
    }
    @Override
    protected String resolveCredential(OAuth2AuthenticationToken authenticationToken, String authType, String authPrincipal) {
        OAuth2AuthorizedClient client = authorizedClientService.loadAuthorizedClient(authType, authPrincipal);
        return client.getAccessToken().getTokenValue();
    }
}

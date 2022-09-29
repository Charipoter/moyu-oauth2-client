package com.moyu.oauth2.client.manager.login.convert;

import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;

public abstract class AbstractThirdPartyUserAuthInfoConverter extends AbstractUserAuthInfoConverter {

    private OAuth2AuthorizedClientService authorizedClientService;

    public AbstractThirdPartyUserAuthInfoConverter(String authPrincipalKey, OAuth2AuthorizedClientService service) {
        super(authPrincipalKey);
        assert service != null : "authorizedClientService 为 null";
        this.authorizedClientService = service;
    }

    @Override
    protected String doResolveCredential(String authType, String authPrincipal) {
        OAuth2AuthorizedClient client = authorizedClientService.loadAuthorizedClient(authType, authPrincipal);
        return client.getAccessToken().getTokenValue();
    }
}

package com.moyu.oauth2.client.manager.login.convert;

import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;

public class GiteeUserAuthInfoConverter extends AbstractThirdPartyUserAuthInfoConverter {

    public GiteeUserAuthInfoConverter(OAuth2AuthorizedClientService service) {
        super("name", service);
    }

}

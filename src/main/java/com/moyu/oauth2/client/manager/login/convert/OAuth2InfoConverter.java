package com.moyu.oauth2.client.manager.login.convert;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;

public interface OAuth2InfoConverter<C> {
    C convert(OAuth2AuthenticationToken authenticationToken);

}

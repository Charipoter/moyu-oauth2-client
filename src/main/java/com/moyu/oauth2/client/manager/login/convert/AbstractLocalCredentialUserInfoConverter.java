package com.moyu.oauth2.client.manager.login.convert;

import com.moyu.oauth2.client.manager.login.convert.key.UserInfoKeyProvider;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;

import java.rmi.UnexpectedException;

public class AbstractLocalCredentialUserInfoConverter extends AbstractAttributesBasedUserInfoConverter {

    AbstractLocalCredentialUserInfoConverter(UserInfoKeyProvider keyProvider) {
        super(keyProvider);
    }

    @Override
    protected String resolveCredential(OAuth2AuthenticationToken authenticationToken, String authType, String authPrincipal) throws UnexpectedException {
        return resolveFromAttributes(authenticationToken, keyProvider.getCredentialKey());
    }
}

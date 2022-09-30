package com.moyu.oauth2.client.manager.login.convert;

import com.moyu.oauth2.client.manager.context.OAuth2LoginPostProcessorContext;
import com.moyu.oauth2.client.manager.login.convert.key.UserInfoKeyProvider;

import java.rmi.UnexpectedException;

public class AbstractLocalCredentialUserInfoConverter extends AbstractAttributesBasedUserInfoConverter {

    AbstractLocalCredentialUserInfoConverter(UserInfoKeyProvider keyProvider) {
        super(keyProvider);
    }

    @Override
    protected String resolveCredential(OAuth2LoginPostProcessorContext context) throws UnexpectedException {
        return resolveFromAttributes(context, keyProvider.getCredentialKey());
    }
}

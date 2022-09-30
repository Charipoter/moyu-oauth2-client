package com.moyu.oauth2.client.manager.login.convert.attribute;

import com.moyu.oauth2.client.manager.context.OAuth2LoginPostProcessorContext;
import com.moyu.oauth2.client.manager.login.convert.attribute.key.UserInfoKeyProvider;

import java.rmi.UnexpectedException;

public class LocalAttributesBasedUserInfoConverter extends AbstractAttributesBasedUserInfoConverter {

    public LocalAttributesBasedUserInfoConverter(UserInfoKeyProvider keyProvider) {
        super(keyProvider);
    }

    @Override
    protected String resolveCredential(OAuth2LoginPostProcessorContext context) throws UnexpectedException {
        return resolveFromAttributes(context, keyProvider.getCredentialKey());
    }
}

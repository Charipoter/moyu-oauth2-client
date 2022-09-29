package com.moyu.oauth2.client.manager.login.convert;

import com.moyu.oauth2.client.manager.login.convert.key.UserInfoKeyProvider;

public class AttributesBasedLocalUserInfoConverterAdapter extends AbstractLocalCredentialUserInfoConverter {
    public AttributesBasedLocalUserInfoConverterAdapter(UserInfoKeyProvider keyProvider) {
        super(keyProvider);
    }
}

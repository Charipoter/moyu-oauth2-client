package com.moyu.oauth2.client.manager.login.convert.attribute;

import com.moyu.oauth2.client.manager.login.convert.OAuth2UserInfoConverter;
import com.moyu.oauth2.client.manager.login.convert.attribute.key.UserInfoKeyProvider;
import com.moyu.oauth2.client.manager.login.convert.attribute.key.UserInfoKeyProviderType;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;

public class AttributesBasedUserInfoConverterFactory {

    public static OAuth2UserInfoConverter get(OAuth2AuthorizedClientService authorizedClientService,
                                UserInfoKeyProvider keyProvider) {

        if (keyProvider.getType().equals(UserInfoKeyProviderType.LOCAL)) {
            return new LocalAttributesBasedUserInfoConverter(keyProvider);
        } else if (keyProvider.getType().equals(UserInfoKeyProviderType.THIRD_PARTY)) {
            return new ThirdPartyAttributesBasedUserInfoConverter(authorizedClientService, keyProvider);
        } else {
            throw new UnsupportedOperationException("找不到符合的类型构造器");
        }

    }

}

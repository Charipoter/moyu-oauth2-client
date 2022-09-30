package com.moyu.oauth2.client.manager.login;

import com.moyu.oauth2.client.manager.login.convert.OAuth2UserInfoConverter;
import com.moyu.oauth2.client.manager.login.convert.attribute.AttributesBasedUserInfoConverterFactory;
import com.moyu.oauth2.client.manager.login.convert.attribute.key.UserInfoKeyProvider;
import com.moyu.oauth2.client.service.UserAuthInfoService;
import com.moyu.oauth2.client.service.UserBasicInfoService;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;

public class AttributesBasedLoginPostProcessorFactory {

    public static OAuth2LoginPostProcessor get(UserInfoKeyProvider keyProvider,
                                               OAuth2AuthorizedClientService authorizedClientService,
                                               UserAuthInfoService userAuthInfoService,
                                               UserBasicInfoService userBasicInfoService) {

        OAuth2UserInfoConverter converter = AttributesBasedUserInfoConverterFactory.get(
                authorizedClientService,
                keyProvider
        );
        return new DefaultOAuth2LoginPostProcessor(converter, userAuthInfoService, userBasicInfoService);

    }

}

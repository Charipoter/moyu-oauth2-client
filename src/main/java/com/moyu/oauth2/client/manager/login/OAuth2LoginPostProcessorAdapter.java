package com.moyu.oauth2.client.manager.login;

import com.moyu.oauth2.client.manager.login.convert.OAuth2UserInfoConverter;
import com.moyu.oauth2.client.service.UserAuthInfoService;
import com.moyu.oauth2.client.service.UserBasicInfoService;

public class OAuth2LoginPostProcessorAdapter extends DefaultOAuth2LoginPostProcessor {

    public OAuth2LoginPostProcessorAdapter(OAuth2UserInfoConverter userInfoConverter, UserAuthInfoService userAuthInfoService, UserBasicInfoService userBasicInfoService) {
        super(userInfoConverter, userAuthInfoService, userBasicInfoService);
    }
}

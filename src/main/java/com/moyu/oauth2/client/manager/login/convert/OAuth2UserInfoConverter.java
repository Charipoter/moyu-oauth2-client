package com.moyu.oauth2.client.manager.login.convert;

import com.moyu.oauth2.client.model.UserAuthInfo;
import com.moyu.oauth2.client.model.UserBasicInfo;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;

public interface OAuth2UserInfoConverter {

    UserAuthInfo convertToUserAuthInfo(OAuth2AuthenticationToken authenticationToken);

    UserBasicInfo convertToUserBasicInfo(OAuth2AuthenticationToken authenticationToken);

}

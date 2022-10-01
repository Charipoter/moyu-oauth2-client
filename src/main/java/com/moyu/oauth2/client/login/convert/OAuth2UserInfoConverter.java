package com.moyu.oauth2.client.login.convert;

import com.moyu.oauth2.client.common.context.OAuth2LoginPostProcessorContext;
import com.moyu.oauth2.client.model.UserAuthInfo;
import com.moyu.oauth2.client.model.UserBasicInfo;

import java.rmi.UnexpectedException;

public interface OAuth2UserInfoConverter {

    UserAuthInfo convertToUserAuthInfo(OAuth2LoginPostProcessorContext context) throws UnexpectedException;

    UserBasicInfo convertToUserBasicInfo(OAuth2LoginPostProcessorContext context) throws UnexpectedException;

}

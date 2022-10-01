package com.moyu.oauth2.client.login.convert;

import com.moyu.oauth2.client.common.context.OAuth2LoginPostProcessorContext;
import com.moyu.oauth2.client.model.UserAuthInfo;
import com.moyu.oauth2.client.model.UserBasicInfo;
import lombok.extern.slf4j.Slf4j;

import java.rmi.UnexpectedException;

@Slf4j
public abstract class AbstractUserInfoConverter implements OAuth2UserInfoConverter {

    @Override
    public UserAuthInfo convertToUserAuthInfo(OAuth2LoginPostProcessorContext context) throws UnexpectedException {

        try {
            context.putAuthType(resolveAuthType(context));
            context.putPrincipal(resolvePrincipal(context));
            context.putCredential(resolveCredential(context));
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new UnexpectedException(e.getMessage());
        }

        return UserAuthInfo.atLeast(context.getAuthType(), context.getPrincipal(), context.getCredential());
    }

    @Override
    public UserBasicInfo convertToUserBasicInfo(OAuth2LoginPostProcessorContext context) throws UnexpectedException {

        try {
            context.putNickname(resolveNickname(context));
            context.putAvatar(resolveAvatar(context));
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new UnexpectedException(e.getMessage());
        }

        return UserBasicInfo.atLeast(context.getNickname(), context.getAvatar());
    }
    private String resolveAuthType(OAuth2LoginPostProcessorContext context) {
        return context.getAuthentication().getAuthorizedClientRegistrationId();
    }
    protected abstract String resolvePrincipal(OAuth2LoginPostProcessorContext context) throws UnexpectedException;

    protected abstract String resolveCredential(OAuth2LoginPostProcessorContext context) throws UnexpectedException;

    protected abstract String resolveNickname(OAuth2LoginPostProcessorContext context) throws UnexpectedException;
    protected abstract String resolveAvatar(OAuth2LoginPostProcessorContext context) throws UnexpectedException;
}

package com.moyu.oauth2.client.manager.login.convert;

import com.moyu.oauth2.client.model.UserAuthInfo;
import com.moyu.oauth2.client.model.UserBasicInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;

import java.rmi.UnexpectedException;

@Slf4j
public abstract class AbstractUserInfoConverter implements OAuth2UserInfoConverter {

    @Override
    public UserAuthInfo convertToUserAuthInfo(OAuth2AuthenticationToken authenticationToken) {

        String authType, authPrincipal, authCredential;

        try {

            authType = resolveAuthType(authenticationToken);

            authPrincipal = resolvePrincipal(authenticationToken, authType);

            authCredential = resolveCredential(authenticationToken, authType, authPrincipal);

        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }

        UserAuthInfo userAuthInfo = new UserAuthInfo();
        userAuthInfo.setAuthType(authType);
        userAuthInfo.setAuthPrincipal(authPrincipal);
        userAuthInfo.setAuthCredential(authCredential);

        return userAuthInfo;
    }

    @Override
    public UserBasicInfo convertToUserBasicInfo(OAuth2AuthenticationToken authenticationToken) {

        String nickname, avatar;

        try {

            nickname = resolveNickname(authenticationToken);

            avatar = resolveAvatar(authenticationToken);

        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }

        UserBasicInfo userBasicInfo = new UserBasicInfo();
        userBasicInfo.setNickname(nickname);
        userBasicInfo.setAvatar(avatar);

        return userBasicInfo;
    }
    private String resolveAuthType(OAuth2AuthenticationToken authenticationToken) {
        return authenticationToken.getAuthorizedClientRegistrationId();
    }
    protected abstract String resolvePrincipal(OAuth2AuthenticationToken authenticationToken,
                                               String authType) throws UnexpectedException;

    protected abstract String resolveCredential(OAuth2AuthenticationToken authenticationToken,
                                                String authType,
                                                String authPrincipal);

    protected abstract String resolveNickname(OAuth2AuthenticationToken authenticationToken) throws UnexpectedException;
    protected abstract String resolveAvatar(OAuth2AuthenticationToken authenticationToken) throws UnexpectedException;
}

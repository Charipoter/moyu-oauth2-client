package com.moyu.oauth2.client.manager.login.convert;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;

import java.rmi.UnexpectedException;
import java.util.Map;

@Slf4j
public abstract class AbstractAttributesBasedUserInfoConverter extends AbstractUserInfoConverter {
    @Override
    protected String resolvePrincipal(OAuth2AuthenticationToken authenticationToken, String authType) throws UnexpectedException {
        return resolveFromAttributes(authenticationToken, getPrincipalKey());
    }

    protected abstract String getPrincipalKey();

    @Override
    protected String resolveNickname(OAuth2AuthenticationToken authenticationToken) throws UnexpectedException {
        return resolveFromAttributes(authenticationToken, getNicknameKey());
    }

    protected abstract String getNicknameKey();

    @Override
    protected String resolveAvatar(OAuth2AuthenticationToken authenticationToken) throws UnexpectedException {
        return resolveFromAttributes(authenticationToken, getAvatarKey());
    }

    protected abstract String getAvatarKey();

    private String resolveFromAttributes(OAuth2AuthenticationToken authenticationToken, String key) throws UnexpectedException {
        Map<String, Object> attributes = authenticationToken.getPrincipal().getAttributes();
        if (!attributes.containsKey(key)) {
            log.warn("用户属性解析失败：" + key);
            throw new UnexpectedException("用户属性解析失败");
        }
        return attributes.get(key).toString();
    }
}

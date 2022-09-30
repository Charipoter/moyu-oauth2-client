package com.moyu.oauth2.client.manager.login.convert.attribute;

import com.moyu.oauth2.client.manager.context.OAuth2LoginPostProcessorContext;
import com.moyu.oauth2.client.manager.login.convert.AbstractUserInfoConverter;
import com.moyu.oauth2.client.manager.login.convert.attribute.key.UserInfoKeyProvider;
import lombok.extern.slf4j.Slf4j;

import java.rmi.UnexpectedException;
import java.util.Map;

@Slf4j
public abstract class AbstractAttributesBasedUserInfoConverter extends AbstractUserInfoConverter {

    protected UserInfoKeyProvider keyProvider;

    public AbstractAttributesBasedUserInfoConverter(UserInfoKeyProvider keyProvider) {
        this.keyProvider = keyProvider;
    }
    @Override
    protected String resolvePrincipal(OAuth2LoginPostProcessorContext context) throws UnexpectedException {
        return resolveFromAttributes(context, keyProvider.getPrincipalKey());
    }
    @Override
    protected String resolveNickname(OAuth2LoginPostProcessorContext context) throws UnexpectedException {
        return resolveFromAttributes(context, keyProvider.getNicknameKey());
    }

    @Override
    protected String resolveAvatar(OAuth2LoginPostProcessorContext context) throws UnexpectedException {
        return resolveFromAttributes(context, keyProvider.getAvatarKey());
    }

    protected String resolveFromAttributes(OAuth2LoginPostProcessorContext context, String key) throws UnexpectedException {
        Map<String, Object> attributes = context.getAuthentication().getPrincipal().getAttributes();
        if (!attributes.containsKey(key)) {
            log.warn("用户属性解析失败：" + key);
            throw new UnexpectedException("用户属性解析失败");
        }
        return attributes.get(key).toString();
    }
}

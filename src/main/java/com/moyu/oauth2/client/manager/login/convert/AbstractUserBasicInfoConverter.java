package com.moyu.oauth2.client.manager.login.convert;

import com.moyu.oauth2.client.model.UserBasicInfo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.function.Supplier;

@Slf4j
@AllArgsConstructor
public abstract class AbstractUserBasicInfoConverter implements OAuth2InfoConverter<UserBasicInfo> {

    private String nicknameKey;
    private String avatarKey;

    private final Supplier<String> DEFAULT_NICKNAME_SUPPLIER = () -> String.valueOf(System.currentTimeMillis());

    private final Supplier<String> DEFAULT_AVATAR_SUPPLIER = () -> String.valueOf(System.currentTimeMillis());

    @Override
    public UserBasicInfo convert(OAuth2AuthenticationToken authenticationToken) {

        Map<String, Object> attributes = authenticationToken.getPrincipal().getAttributes();
        String nickname, avatar;

        try {
            nickname = resolveNickname(authenticationToken);
            avatar = resolveAvatar(authenticationToken);
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage());
            return null;
        }

        UserBasicInfo userBasicInfo = new UserBasicInfo();
        userBasicInfo.setNickname(nickname);
        userBasicInfo.setAvatar(avatar);

        return userBasicInfo;
    }

    protected String resolveNickname(OAuth2AuthenticationToken authenticationToken) {
        Map<String, Object> attributes = authenticationToken.getPrincipal().getAttributes();
        String nickname = (String) attributes.get(nicknameKey);
        return StringUtils.hasLength(nickname) ? nickname : DEFAULT_NICKNAME_SUPPLIER.get();
    }

    protected String resolveAvatar(OAuth2AuthenticationToken authenticationToken) {
        Map<String, Object> attributes = authenticationToken.getPrincipal().getAttributes();
        String avatar = (String) attributes.get(avatarKey);
        return StringUtils.hasLength(avatar) ? avatar : DEFAULT_AVATAR_SUPPLIER.get();
    }

}

package com.moyu.oauth2.client.manager.login.convert;

import com.moyu.oauth2.client.model.UserAuthInfo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.function.Supplier;

@AllArgsConstructor
@Slf4j
public abstract class AbstractUserAuthInfoConverter implements OAuth2InfoConverter<UserAuthInfo> {
    private String authPrincipalKey;
    private final Supplier<String> DEFAULT_PRINCIPAL_SUPPLIER = () -> String.valueOf(System.currentTimeMillis());

    @Override
    public UserAuthInfo convert(OAuth2AuthenticationToken authenticationToken) {

        String authType, authPrincipal, authCredential;

        try {
            authType = authenticationToken.getAuthorizedClientRegistrationId();
            authPrincipal = resolvePrincipal(authenticationToken);
            authCredential = resolveCredential(authType, authPrincipal);
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage());
            return null;
        }

        UserAuthInfo userAuthInfo = new UserAuthInfo();
        userAuthInfo.setAuthType(authType);
        userAuthInfo.setAuthPrincipal(authPrincipal);
        userAuthInfo.setAuthCredential(authCredential);

        return userAuthInfo;
    }

    protected String resolvePrincipal(OAuth2AuthenticationToken authenticationToken) {
        Map<String, Object> attributes = authenticationToken.getPrincipal().getAttributes();
        String authPrincipal = (String) attributes.get(authPrincipalKey);
        return StringUtils.hasLength(authPrincipal) ? authPrincipal : DEFAULT_PRINCIPAL_SUPPLIER.get();
    }

    /**
     * 凭证的获取可能需要获取 token，因此交给子类处理
     */
    protected String resolveCredential(String authType, String authPrincipal) {
        String authCredential = doResolveCredential(authType, authPrincipal);
        if (StringUtils.hasLength(authCredential)) {
            return authCredential;
        } else {
            throw new IllegalArgumentException("缺失认证凭证（例如 token）");
        }
    }

    protected abstract String doResolveCredential(String authType, String authPrincipal);



}

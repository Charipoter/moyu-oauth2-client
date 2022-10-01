package com.moyu.oauth2.client.manager.context;

import com.moyu.oauth2.client.model.UserAuthInfo;
import com.moyu.oauth2.client.model.UserBasicInfo;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;

import java.util.List;

public class OAuth2LoginPostProcessorContext extends MapBasedContext {

    private static final String AUTH_TYPE_KEY = "authType";
    private static final String AUTHENTICATION_KEY = "authentication";
    private static final String PRINCIPAL_KEY = "principal";
    private static final String CREDENTIAL_KEY = "credential";
    private static final String NICKNAME_KEY = "nickname";
    private static final String AVATAR_KEY = "avatar";
    private static final String USER_AUTH_INFO_KEY = "userAuthInfo";
    private static final String USER_BASIC_INFO_KEY = "userBasicInfo";
    private static final String USER_DETAILS_KEY = "userDetails";

    private static final String AUTHORITIES_KEY = "scope";

    private boolean isNewUser;

    public void putAuthType(String authType) {
        put(AUTH_TYPE_KEY, authType);
    }

    public String getAuthType() {
        return (String) get(AUTH_TYPE_KEY);
    }
    public void putAuthentication(OAuth2AuthenticationToken authenticationToken) {
        put(AUTHENTICATION_KEY, authenticationToken);
    }

    public OAuth2AuthenticationToken getAuthentication() {
        return (OAuth2AuthenticationToken) get(AUTHENTICATION_KEY);
    }

    public void putPrincipal(String principal) {
        put(PRINCIPAL_KEY, principal);
    }

    public String getPrincipal() {
        return (String) get(PRINCIPAL_KEY);
    }

    public void putCredential(String credential) {
        put(CREDENTIAL_KEY, credential);
    }

    public String getCredential() {
        return (String) get(CREDENTIAL_KEY);
    }

    public void putNickname(String nickname) {
        put(NICKNAME_KEY, nickname);
    }

    public String getNickname() {
        return (String) get(NICKNAME_KEY);
    }

    public void putAvatar(String avatar) {
        put(AVATAR_KEY, avatar);
    }

    public String getAvatar() {
        return (String) get(AVATAR_KEY);
    }

    public void putUserAuthInfo(UserAuthInfo userAuthInfo) {
        put(USER_AUTH_INFO_KEY, userAuthInfo);
    }

    public UserAuthInfo getUserAuthInfo() {
        return (UserAuthInfo) get(USER_AUTH_INFO_KEY);
    }

    public void putUserBasicInfo(UserBasicInfo userBasicInfo) {
        put(USER_BASIC_INFO_KEY, userBasicInfo);
    }

    public UserBasicInfo getUserBasicInfo() {
        return (UserBasicInfo) get(USER_BASIC_INFO_KEY);
    }

    public void putAuthorities(List<String> authorities) {
        put(AUTHORITIES_KEY, authorities);
    }
    public List<String> getAuthorities() {
        return (List<String>) get(AUTHORITIES_KEY);
    }

    public boolean containsUserInfo() {
        return hasKey(USER_AUTH_INFO_KEY) && hasKey(USER_BASIC_INFO_KEY) &&
                getUserAuthInfo() != null && getUserBasicInfo() != null;
    }

    public void thisIsANewUser() {
        this.isNewUser = true;
    }

    public boolean newUser() {
        return this.isNewUser;
    }

}

package com.moyu.oauth2.client.manager.context;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;

public class OAuth2LoginPostProcessorContext extends MapBasedContext {

    private static final String AUTHENTICATION_KEY = "authentication";
    private static final String PRINCIPAL_KEY = "principal";
    private static final String CREDENTIAL_KEY = "credential";
    private static final String NICKNAME_KEY = "nickname";
    private static final String AVATAR_KEY = "avatar";

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

}

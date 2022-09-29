package com.moyu.oauth2.client.manager.login.convert;

public interface AttributesBasedUserInfoKeyProvider {

    String getPrincipalKey();

    String getNicknameKey();

    String getAvatarKey();

}

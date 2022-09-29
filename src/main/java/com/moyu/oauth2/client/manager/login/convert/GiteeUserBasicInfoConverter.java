package com.moyu.oauth2.client.manager.login.convert;

public class GiteeUserBasicInfoConverter extends AbstractUserBasicInfoConverter {

    public GiteeUserBasicInfoConverter() {
        super("name", "avatar_url");
    }

}

package com.moyu.oauth2.client.manager.login.convert;

public abstract class AbstractNoopUserAuthInfoConverter extends AbstractUserAuthInfoConverter {

    public AbstractNoopUserAuthInfoConverter() {
        super(null);
    }

    @Override
    protected String doResolveCredential(String authType, String authPrincipal) {
        return null;
    }

}

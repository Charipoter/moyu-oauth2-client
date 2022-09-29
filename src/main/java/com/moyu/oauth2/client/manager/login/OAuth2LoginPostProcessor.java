package com.moyu.oauth2.client.manager.login;

import com.moyu.oauth2.client.model.TokenResponseVo;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;

import java.rmi.UnexpectedException;

public interface OAuth2LoginPostProcessor {

    TokenResponseVo postProcessAfterAuthentication(OAuth2AuthenticationToken authenticationToken) throws UnexpectedException;

}

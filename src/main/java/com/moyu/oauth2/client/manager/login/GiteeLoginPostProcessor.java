package com.moyu.oauth2.client.manager.login;

import com.moyu.oauth2.client.manager.login.convert.GiteeUserAuthInfoConverter;
import com.moyu.oauth2.client.manager.login.convert.GiteeUserBasicInfoConverter;
import com.moyu.oauth2.client.model.TokenResponseVo;
import com.moyu.oauth2.client.model.UserAuthInfo;
import com.moyu.oauth2.client.model.UserBasicInfo;
import com.moyu.oauth2.client.service.UserAuthInfoService;
import com.moyu.oauth2.client.service.UserBasicInfoService;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Component;

@Component
public class GiteeLoginPostProcessor extends AbstractOAuth2LoginPostProcessor {

    public GiteeLoginPostProcessor(OAuth2AuthorizedClientService authorizedClientService, UserAuthInfoService userAuthInfoService, UserBasicInfoService userBasicInfoService) {
        super(new GiteeUserAuthInfoConverter(authorizedClientService), new GiteeUserBasicInfoConverter(), userAuthInfoService, userBasicInfoService);
    }

    @Override
    protected TokenResponseVo wrapperOrReGenerateToken(OAuth2AuthenticationToken authenticationToken, UserAuthInfo userAuthInfo, UserBasicInfo userBasicInfo) {
        return null;
    }
}

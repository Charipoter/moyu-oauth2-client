package com.moyu.oauth2.client.manager.login;

import com.moyu.oauth2.client.manager.login.convert.AttributesBasedUserInfoKeyProvider;
import com.moyu.oauth2.client.manager.login.convert.OAuth2UserInfoConverter;
import com.moyu.oauth2.client.manager.login.convert.UserInfoConverterAdapter;
import com.moyu.oauth2.client.model.TokenResponseVo;
import com.moyu.oauth2.client.service.UserAuthInfoService;
import com.moyu.oauth2.client.service.UserBasicInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class OAuth2AttributesBasedLoginPostProcessorManager {
    // 不存在并发修改，使用非并发集合
    private Map<String, OAuth2LoginPostProcessor> postProcessorMap = new HashMap<>();

    public OAuth2AttributesBasedLoginPostProcessorManager(List<AttributesBasedUserInfoKeyProvider> keyProviders,
                                                          OAuth2AuthorizedClientService authorizedClientService,
                                                          UserAuthInfoService userAuthInfoService,
                                                          UserBasicInfoService userBasicInfoService) {
        // 去除 OAuth2 前缀
        int suffixLength = 19;
        for (AttributesBasedUserInfoKeyProvider keyProvider : keyProviders) {

            String fullName = keyProvider.getClass().getSimpleName();
            String authType = fullName.substring(0, fullName.length() - suffixLength).toLowerCase();

            OAuth2UserInfoConverter converter = new UserInfoConverterAdapter(authorizedClientService, keyProvider);
            OAuth2LoginPostProcessor postProcessor = new OAuth2LoginPostProcessorAdapter(converter, userAuthInfoService, userBasicInfoService);

            postProcessorMap.put(authType, postProcessor);
        }
    }

    public TokenResponseVo postProcessAfterAuthentication(OAuth2AuthenticationToken authenticationToken) {

        String authType = authenticationToken.getAuthorizedClientRegistrationId();

        if (postProcessorMap.containsKey(authType)) {
            return postProcessorMap.get(authType).postProcessAfterAuthentication(authenticationToken);
        } else {
            log.warn("认证信息找不到适合的处理组件，考虑是否出现异常");
            return null;
        }

    }
}

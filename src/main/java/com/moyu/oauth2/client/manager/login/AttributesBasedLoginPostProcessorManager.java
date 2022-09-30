package com.moyu.oauth2.client.manager.login;

import com.moyu.oauth2.client.manager.login.convert.attribute.key.UserInfoKeyProvider;
import com.moyu.oauth2.client.model.TokenResponseVo;
import com.moyu.oauth2.client.service.UserAuthInfoService;
import com.moyu.oauth2.client.service.UserBasicInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.rmi.UnexpectedException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class AttributesBasedLoginPostProcessorManager {
    // 不存在并发修改，使用非并发集合
    private Map<String, OAuth2LoginPostProcessor> postProcessorMap = new HashMap<>();

    public AttributesBasedLoginPostProcessorManager(List<UserInfoKeyProvider> keyProviders,
                                                    OAuth2AuthorizedClientService authorizedClientService,
                                                    UserAuthInfoService userAuthInfoService,
                                                    UserBasicInfoService userBasicInfoService) {
        // 去除 OAuth2 前缀
        int suffixLength = 19;
        for (UserInfoKeyProvider keyProvider : keyProviders) {

            String fullName = keyProvider.getClass().getSimpleName();
            String authType = fullName.substring(0, fullName.length() - suffixLength).toLowerCase();

            OAuth2LoginPostProcessor postProcessor = AttributesBasedLoginPostProcessorFactory.get(
                    keyProvider,
                    authorizedClientService,
                    userAuthInfoService,
                    userBasicInfoService
            );

            postProcessorMap.put(authType, postProcessor);
        }
    }

    /**
     * 此处负责出错时事务的回滚
     */
    @Transactional
    public TokenResponseVo postProcessAfterAuthentication(OAuth2AuthenticationToken authenticationToken) throws UnexpectedException {

        String authType = authenticationToken.getAuthorizedClientRegistrationId();

        if (postProcessorMap.containsKey(authType)) {
            return postProcessorMap.get(authType).postProcessAfterAuthentication(authenticationToken);
        } else {
            log.warn("认证信息找不到适合的处理组件，考虑是否出现异常");
            throw new UnsupportedOperationException("找不到适配的后置处理器");
        }

    }
}

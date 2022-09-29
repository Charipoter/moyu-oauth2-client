package com.moyu.oauth2.client.manager.login;

import com.moyu.oauth2.client.model.TokenResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class OAuth2LoginPostProcessorManager {
    // 不存在并发修改，使用非并发集合
    private Map<String, OAuth2LoginPostProcessor> postProcessorMap = new HashMap<>();

    public OAuth2LoginPostProcessorManager(List<OAuth2LoginPostProcessor> loginPostProcessors) {
        // 去除 OAuth2 前缀
        int suffixLength = OAuth2LoginPostProcessor.class.getSimpleName().length() - 6;
        for (OAuth2LoginPostProcessor loginPostProcessor : loginPostProcessors) {
            String name = loginPostProcessor.getClass().getSimpleName();
            // 我们约定实例前缀就是 key
            String prefix = name.substring(0, name.length() - suffixLength).toLowerCase();
            postProcessorMap.put(prefix, loginPostProcessor);
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

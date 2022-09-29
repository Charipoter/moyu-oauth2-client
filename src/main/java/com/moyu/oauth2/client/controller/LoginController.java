package com.moyu.oauth2.client.controller;

import com.moyu.oauth2.client.common.http.R;
import com.moyu.oauth2.client.manager.login.OAuth2AttributesBasedLoginPostProcessorManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Slf4j
@RestController
public class LoginController {
    @Autowired
    private OAuth2AttributesBasedLoginPostProcessorManager loginPostProcessorManager;
    /**
     * 到达这里后已经认证完成了
     */
    @GetMapping("/login/oauth2")
    public R login(Authentication authentication) throws IOException {
        assert loginPostProcessorManager != null : "loginPostProcessor 为 null";

        if (authentication instanceof OAuth2AuthenticationToken authenticationToken) {
            try {
                return R.ok(loginPostProcessorManager.postProcessAfterAuthentication(authenticationToken));
            } catch (Exception e) {
                return R.error(e.getMessage());
            }
        }
        return R.error("认证信息不符合要求，也许业务出现了问题");
    }

}

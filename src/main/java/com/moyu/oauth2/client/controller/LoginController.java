package com.moyu.oauth2.client.controller;

import com.moyu.oauth2.client.common.http.R;
import com.moyu.oauth2.client.login.DefaultOAuth2LoginPostProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RestController
public class LoginController {
    @Autowired
    private DefaultOAuth2LoginPostProcessor loginPostProcessor;
    /**
     * 到达这里后已经认证完成了
     */
    @GetMapping("/login/oauth2")
    public R login(Authentication authentication) throws IOException {
        assert loginPostProcessor != null : "loginPostProcessor 为 null";

        if (authentication instanceof OAuth2AuthenticationToken authenticationToken) {
            try {
                return R.ok(loginPostProcessor.postProcessAfterAuthentication(authenticationToken));
            } catch (Exception e) {
                return R.error(e.getMessage());
            }
        }
        return R.error("认证信息不符合要求，也许业务出现了问题");
    }

    /**
     * 当认证成功后从 requestCache 里取不到已保存请求，默认会前往该界面。
     * 可能情况：
     *      1.直接请求了 /login 界面进行认证，此时已有认证信息
     *      2.直接请求了 / 界面，无有效认证信息
     *      3.服务器问题导致 requestCache 信息丢失
     *      4.请求了其他会直接导致认证成功的 url，例如 /oauth2/authentication/xx
     * 应对方式：
     *      1.重定向到回调 api 即可继续后续逻辑
     *      2.让用户去认证 -> 重定向到回调 api
     *      3.重定向到回调 api 即可继续后续逻辑
     *      4.重定向到回调 api 即可继续后续逻辑
     */
    @GetMapping("/")
    public void index(HttpServletResponse response) throws IOException {

        log.warn("意外到达了 / 页面");
        response.sendRedirect("/login/oauth2");
    }

}

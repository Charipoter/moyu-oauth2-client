package com.moyu.oauth2.client.manager.login;

import com.moyu.oauth2.client.manager.login.convert.OAuth2UserInfoConverter;
import com.moyu.oauth2.client.model.TokenResponseVo;
import com.moyu.oauth2.client.model.UserAuthInfo;
import com.moyu.oauth2.client.model.UserBasicInfo;
import com.moyu.oauth2.client.service.UserAuthInfoService;
import com.moyu.oauth2.client.service.UserBasicInfoService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;

import java.rmi.UnexpectedException;

@Slf4j
@AllArgsConstructor
public abstract class AbstractOAuth2LoginPostProcessor implements OAuth2LoginPostProcessor {

    private OAuth2UserInfoConverter userInfoConverter;
    private UserAuthInfoService userAuthInfoService;
    private UserBasicInfoService userBasicInfoService;
    @Override
    public TokenResponseVo postProcessAfterAuthentication(OAuth2AuthenticationToken authenticationToken) throws UnexpectedException {
        assert userInfoConverter != null;

        UserAuthInfo userAuthInfo;
        UserBasicInfo userBasicInfo;

        try {
            userAuthInfo = userInfoConverter.convertToUserAuthInfo(authenticationToken);
            userBasicInfo = userInfoConverter.convertToUserBasicInfo(authenticationToken);
        } catch (Exception e) {
            log.error("属性解析出现了异常：" + e.getMessage());
            throw e;
        }

        if (userAuthInfo != null && userBasicInfo != null) {
            postProcessWithUserInfo(userAuthInfo, userBasicInfo);
        } else {
            log.warn("用户属性解析为 null，却并没有抛出异常");
            return null;
        }
        // 根据处理后的用户数据重新生成 token
        return wrapperOrReGenerateToken(authenticationToken, userAuthInfo, userBasicInfo);
    }

    /**
     * 该方法出错需要进行事务回滚
     */
    private void postProcessWithUserInfo(UserAuthInfo userAuthInfo, UserBasicInfo userBasicInfo) {

        UserAuthInfo existUserAuthInfo = userAuthInfoService.getOneByTypeAndPrincipal(
                userAuthInfo.getAuthType(), userAuthInfo.getAuthPrincipal()
        );
        boolean foundUserAuthInfo = existUserAuthInfo != null;
        boolean newUser = !foundUserAuthInfo;

        if (foundUserAuthInfo) {

            if (existUserAuthInfo.getUserId() == null) {
                log.warn("当前认证体已存在，但是未关联 user，可能是 bug：" + existUserAuthInfo);
                newUser = true;
            } else {
                // 已关联 user，此处仅需要更新，别忘了设置 userId 关联，否则更新后会变成 null
                userAuthInfo.setUserId(existUserAuthInfo.getUserId());
                userBasicInfo.setId(existUserAuthInfo.getUserId());

                if (!userBasicInfoService.updateById(userBasicInfo)) {
                    log.warn("当前认证体绑定了 user，但绑定的 user 可能不存在：" + existUserAuthInfo);
                    newUser = true;
                }
            }
        }
        // 判定是一个新的用户，为其绑定本系统 user
        if (newUser) {
            // 前面可能给赋了值
            userBasicInfo.setId(null);
            userBasicInfoService.save(userBasicInfo);
            // 绑定 userId
            userAuthInfo.setUserId(userBasicInfo.getId());
        }
        // 存储认证信息
        if (foundUserAuthInfo) {
            userAuthInfoService.updateByTypeAndPrincipal(userAuthInfo);
        } else {
            userAuthInfoService.save(userAuthInfo);
        }
    }

    private TokenResponseVo wrapperOrReGenerateToken(OAuth2AuthenticationToken authenticationToken,
                                                     UserAuthInfo userAuthInfo,
                                                     UserBasicInfo userBasicInfo) {

        return null;
    }

}

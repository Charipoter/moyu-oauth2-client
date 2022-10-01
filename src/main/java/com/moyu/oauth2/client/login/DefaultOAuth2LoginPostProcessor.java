package com.moyu.oauth2.client.login;

import com.moyu.oauth2.client.common.context.OAuth2LoginPostProcessorContext;
import com.moyu.oauth2.client.login.convert.OAuth2UserInfoConverter;
import com.moyu.oauth2.client.login.convert.attribute.AttributesBasedUserInfoConverterFactory;
import com.moyu.oauth2.client.login.convert.attribute.key.UserInfoKeyProvider;
import com.moyu.oauth2.client.model.*;
import com.moyu.oauth2.client.service.*;
import com.moyu.oauth2.client.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.rmi.UnexpectedException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class DefaultOAuth2LoginPostProcessor implements OAuth2LoginPostProcessor {
    @Autowired
    private OAuth2AuthorizedClientService authorizedClientService;
    @Autowired
    private UserAuthInfoService userAuthInfoService;
    @Autowired
    private UserBasicInfoService userBasicInfoService;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private RolePermissionService rolePermissionService;
    private final Map<String, OAuth2UserInfoConverter> converterMap = new HashMap<>();

    public DefaultOAuth2LoginPostProcessor(List<UserInfoKeyProvider> keyProviders,
                                           OAuth2AuthorizedClientService authorizedClientService) {

        int suffixLength = UserInfoKeyProvider.class.getSimpleName().length();
        for (UserInfoKeyProvider keyProvider : keyProviders) {

            String fullName = keyProvider.getClass().getSimpleName();
            String authType = fullName.substring(0, fullName.length() - suffixLength).toLowerCase();

            OAuth2UserInfoConverter converter = AttributesBasedUserInfoConverterFactory.get(
                    authorizedClientService,
                    keyProvider
            );

            converterMap.put(authType, converter);
        }

    }
    @Override
    public TokenResponseVo postProcessAfterAuthentication(OAuth2AuthenticationToken authenticationToken) throws UnexpectedException {

        OAuth2UserInfoConverter userInfoConverter = converterMap.get(authenticationToken.getAuthorizedClientRegistrationId());

        assert userInfoConverter != null : "找不到匹配的转换器";

        OAuth2LoginPostProcessorContext context = new OAuth2LoginPostProcessorContext();
        context.putAuthentication(authenticationToken);

        try {
            context.putUserAuthInfo(userInfoConverter.convertToUserAuthInfo(context));
            context.putUserBasicInfo(userInfoConverter.convertToUserBasicInfo(context));
        } catch (Exception e) {
            log.error("属性解析出现了异常：" + e.getMessage());
            throw e;
        }

        if (context.containsUserInfo()) {
            return postProcessWithUserInfo(context);
        } else {
            log.warn("用户属性解析为 null，却并没有抛出异常");
            return null;
        }
    }

    /**
     * 该方法出错需要进行事务回滚
     */
    @Transactional
    protected TokenResponseVo postProcessWithUserInfo(OAuth2LoginPostProcessorContext context) {

        UserAuthInfo userAuthInfo = context.getUserAuthInfo();
        UserBasicInfo userBasicInfo = context.getUserBasicInfo();

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

            context.thisIsANewUser();
        }
        // 存储认证信息
        if (foundUserAuthInfo) {
            userAuthInfoService.updateByTypeAndPrincipal(userAuthInfo);
        } else {
            userAuthInfoService.save(userAuthInfo);
        }

        return reGenerateToken(context);
    }

    private TokenResponseVo reGenerateToken(OAuth2LoginPostProcessorContext context) {

        Long userId = context.getUserBasicInfo().getId();

        if (context.newUser()) {
            // 直接分配新用户角色
            // 分配新用户角色并存储
            UserRole userRole = UserRole.builder().roleId(1L).userId(userId).build();
            userRoleService.save(userRole);
        }
        // 查询用户权限
        List<Permission> permissions = permissionService.getByUserId(userId);
        // 构建信息
        List<String> authorities = permissions.stream()
                .map(Permission::getName)
                .toList();
        context.putAuthorities(authorities);
        // 生成 token
        Jwt jwt = JwtUtils.generate(context);

        return TokenResponseVo.builder().tokenValue(jwt.getTokenValue()).build();
    }

}

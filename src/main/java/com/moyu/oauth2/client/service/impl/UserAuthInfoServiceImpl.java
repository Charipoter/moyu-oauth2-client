package com.moyu.oauth2.client.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moyu.oauth2.client.mapper.UserAuthInfoMapper;
import com.moyu.oauth2.client.model.UserAuthInfo;
import com.moyu.oauth2.client.service.UserAuthInfoService;
import org.springframework.stereotype.Service;

/**
* @author lenovo
* @description 针对表【user_auth_info】的数据库操作Service实现
* @createDate 2022-09-28 17:11:18
*/
@Service
public class UserAuthInfoServiceImpl extends ServiceImpl<UserAuthInfoMapper, UserAuthInfo>
    implements UserAuthInfoService{

    @Override
    public boolean updateByTypeAndPrincipal(UserAuthInfo userAuthInfo) {
        // TODO 是否只需要更新凭证
        return update(new LambdaUpdateWrapper<UserAuthInfo>()
                .set(UserAuthInfo::getAuthCredential, userAuthInfo.getAuthCredential())
                .set(UserAuthInfo::getUserId, userAuthInfo.getUserId())
                .eq(UserAuthInfo::getAuthPrincipal, userAuthInfo.getAuthPrincipal())
                .eq(UserAuthInfo::getAuthType, userAuthInfo.getAuthType())
        );
    }

    @Override
    public UserAuthInfo getOneByTypeAndPrincipal(String authType, String authPrincipal) {
        return getOne(new LambdaQueryWrapper<UserAuthInfo>()
                .eq(UserAuthInfo::getAuthPrincipal, authPrincipal)
                .eq(UserAuthInfo::getAuthType, authType)
        );
    }
}





package com.moyu.oauth2.client.service;

import com.moyu.oauth2.client.model.UserAuthInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author lenovo
* @description 针对表【user_auth_info】的数据库操作Service
* @createDate 2022-09-28 17:11:18
*/
public interface UserAuthInfoService extends IService<UserAuthInfo> {
    boolean updateByTypeAndPrincipal(UserAuthInfo userAuthInfo);

    UserAuthInfo getOneByTypeAndPrincipal(String authType, String authPrincipal);
}

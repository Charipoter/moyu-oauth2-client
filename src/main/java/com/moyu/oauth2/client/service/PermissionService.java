package com.moyu.oauth2.client.service;

import com.moyu.oauth2.client.model.Permission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author lenovo
* @description 针对表【permission】的数据库操作Service
* @createDate 2022-10-01 15:26:28
*/
public interface PermissionService extends IService<Permission> {

    List<Permission> getByUserId(Long userId);
}

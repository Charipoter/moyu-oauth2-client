package com.moyu.oauth2.client.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moyu.oauth2.client.model.RolePermission;
import com.moyu.oauth2.client.service.RolePermissionService;
import com.moyu.oauth2.client.mapper.RolePermissionMapper;
import org.springframework.stereotype.Service;

/**
* @author lenovo
* @description 针对表【role_permission】的数据库操作Service实现
* @createDate 2022-10-01 15:26:28
*/
@Service
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission>
    implements RolePermissionService{

}





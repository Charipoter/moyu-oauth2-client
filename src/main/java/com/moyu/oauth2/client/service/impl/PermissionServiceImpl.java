package com.moyu.oauth2.client.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moyu.oauth2.client.mapper.PermissionMapper;
import com.moyu.oauth2.client.model.Permission;
import com.moyu.oauth2.client.service.PermissionService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author lenovo
* @description 针对表【permission】的数据库操作Service实现
* @createDate 2022-10-01 15:26:28
*/
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission>
    implements PermissionService{

    @Override
    public List<Permission> getByUserId(Long userId) {
        return getBaseMapper().selectByUserId(userId);
    }

}





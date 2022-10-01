package com.moyu.oauth2.client.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moyu.oauth2.client.model.UserRole;
import com.moyu.oauth2.client.service.UserRoleService;
import com.moyu.oauth2.client.mapper.UserRoleMapper;
import org.springframework.stereotype.Service;

/**
* @author lenovo
* @description 针对表【user_role】的数据库操作Service实现
* @createDate 2022-10-01 14:57:21
*/
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole>
    implements UserRoleService{

}





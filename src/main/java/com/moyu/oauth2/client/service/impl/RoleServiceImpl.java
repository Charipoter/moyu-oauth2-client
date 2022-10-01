package com.moyu.oauth2.client.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moyu.oauth2.client.model.Role;
import com.moyu.oauth2.client.service.RoleService;
import com.moyu.oauth2.client.mapper.RoleMapper;
import org.springframework.stereotype.Service;

/**
* @author lenovo
* @description 针对表【role】的数据库操作Service实现
* @createDate 2022-10-01 15:26:28
*/
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
    implements RoleService{

}





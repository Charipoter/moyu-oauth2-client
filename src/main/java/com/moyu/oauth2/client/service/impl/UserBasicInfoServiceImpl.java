package com.moyu.oauth2.client.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moyu.oauth2.client.model.UserBasicInfo;
import com.moyu.oauth2.client.service.UserBasicInfoService;
import com.moyu.oauth2.client.mapper.UserBasicInfoMapper;
import org.springframework.stereotype.Service;

/**
* @author lenovo
* @description 针对表【user_basic_info】的数据库操作Service实现
* @createDate 2022-09-28 17:11:18
*/
@Service
public class UserBasicInfoServiceImpl extends ServiceImpl<UserBasicInfoMapper, UserBasicInfo>
    implements UserBasicInfoService{

}





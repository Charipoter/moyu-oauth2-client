package com.moyu.oauth2.client.mapper;

import com.moyu.oauth2.client.model.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author lenovo
* @description 针对表【permission】的数据库操作Mapper
* @createDate 2022-10-01 15:26:28
* @Entity com.moyu.oauth2.client.model.Permission
*/
public interface PermissionMapper extends BaseMapper<Permission> {

    List<Permission> selectByUserId(@Param("userId") Long userId);
}





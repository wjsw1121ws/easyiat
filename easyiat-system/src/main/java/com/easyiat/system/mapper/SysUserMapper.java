package com.easyiat.system.mapper;

import com.easyiat.system.pojo.RegisterUserReq;
import com.easyiat.system.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @description:
 * @author: changchun_wu
 * @version: 1.0
 * @blame: Test Team
 **/
@Mapper
public interface SysUserMapper {

    /**
     * 查询登录邮箱是否存在
     *
     * @param username 用户名
     * @return User
     */
    User loadUserByUsername(@Param("username") String username);

    /**
     * 校验用户名是否可用
     * @param username  用户名
     * @return  Boolean
     */
    Integer selectUserCountByUsername(@Param("username") String username);


    /**
     * 注册用户
     * @param registerUserReq   用户注册参数
     */
    void registerUser(@Param("registerUserReq") RegisterUserReq registerUserReq);
}
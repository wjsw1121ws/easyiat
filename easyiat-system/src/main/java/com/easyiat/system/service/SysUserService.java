package com.easyiat.system.service;


import com.easyiat.system.pojo.RegisterUserReq;
import com.easyiat.system.pojo.User;

/**
  * @description: 
  * @author: changchun_wu
  * @version: 1.0 
  * @blame: Test Team
  **/    
public interface SysUserService{

    /**
     * 获取用户信息
     * @param username  用户名
     * @return  User
     */
    User loadUserByUsername(String username);

    /**
     * 校验用户名是否可用
     * @param username  用户名
     * @return  Boolean
     */
    Boolean checkUsernameIsAvailable(String username);

    /**
     * 注册用户
     * @param registerUserReq   用户注册参数
     */
    void registerUser(RegisterUserReq registerUserReq);
}

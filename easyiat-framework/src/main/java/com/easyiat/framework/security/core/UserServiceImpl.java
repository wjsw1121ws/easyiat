package com.easyiat.framework.security.core;

import com.easyiat.framework.security.ienum.AuthMessage;
import com.easyiat.system.pojo.Role;
import com.easyiat.system.pojo.User;
import com.easyiat.system.service.SysRoleService;
import com.easyiat.system.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * @description:
 * @author: changchun_wu
 * @version: 1.0
 * @blame: Test Team
 **/

@Slf4j
@Service
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private SysUserService userService;

    @Autowired
    private SysRoleService roleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("=====================Start: UserService获取用户详情=====================");
        if (StringUtils.isEmpty(username)) {
            log.error("=====用户名为空======");
            throw new InsufficientAuthenticationException(AuthMessage.USERNAME_EMPTY.message());
        }
        User user = userService.loadUserByUsername(username);
        if (user==null){
            log.error("======用户: 【{}】不存在======", username);
            throw new InsufficientAuthenticationException(AuthMessage.USERNAME_NOT_EXIST.message());
        }
        log.info("=====用户存在======");
        List<Role> roleList = roleService.getRoleListByUserId(user.getUserId());
        log.info("======用户: 【{}】角色列表: 【{}】", username, roleList);
        user.setAuthorities(roleList);
        log.info("=====================End: UserService获取用户详情=====================");
        return user;
    }
}

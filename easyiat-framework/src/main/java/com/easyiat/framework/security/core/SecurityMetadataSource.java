package com.easyiat.framework.security.core;

import com.easyiat.framework.ienum.MenuType;
import com.easyiat.framework.security.ienum.AuthMessage;
import com.easyiat.system.service.SysMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @description:
 * @author: changchun_wu
 * @version: 1.0
 * @blame: Test Team
 **/

@Slf4j
@Component
public class SecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    @Autowired
    private SysMenuService menuService;


    /**
     * 返回请求的资源需要的角色
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        //object 中包含用户请求的request 信息
        String url = ((FilterInvocation) o).getRequestUrl();
        log.info("=====当前访问的资源URL: 【{}】======", url);
        List<String> urlList = menuService.selectUrlListByMenuType(Collections.singletonList(MenuType.LIST));
        //表示这是一个不存在的网页
        if (!urlList.contains(url)){
            return null;
        }
        List<String> roleKeyList = menuService.queryRoleKeyByUrl(url);
        List<ConfigAttribute> attributeList = new ArrayList<>();
        //表示没有网页的访问权限
        if (CollectionUtils.isEmpty(roleKeyList)) {
            throw new InternalAuthenticationServiceException(AuthMessage.NO_ACCESS_PERMISSION.message());

        }
        log.info("======URL: 【{}】所需要的角色: 【{}】======", url, roleKeyList);
        roleKeyList.forEach(roleKey -> {
            attributeList.add((ConfigAttribute) () -> roleKey);
        });
        log.info("======获取访问URL: 【{}】所需要的角色成功======", url);
        return attributeList;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return FilterInvocation.class.isAssignableFrom(aClass);
    }

}

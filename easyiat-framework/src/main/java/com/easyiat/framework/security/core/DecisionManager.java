package com.easyiat.framework.security.core;

import com.easyiat.framework.security.ienum.AuthMessage;
import com.easyiat.framework.ienum.CommonRole;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collection;

/**
 * @description: 决策器
 * @author: changchun_wu
 * @version: 1.0
 * @blame: Test Team
 **/

@Slf4j
@Component
public class DecisionManager implements AccessDecisionManager {

    /**
     * 通过传递的参数来决定用户是否有访问对应受保护对象的权限
     *
     * @param authentication   包含了当前的用户信息，包括拥有的权限。这里的权限来源就是前面登录时UserDetailsService中设置的authorities。
     * @param object           就是FilterInvocation对象，可以得到request等web资源
     * @param configAttributes configAttributes是本次访问需要的权限
     */
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        log.info("======决策器决策当前访问URL: 【{}】是否放行======",object);
        if (authentication.getPrincipal().equals(CommonRole.ANONYMOUS_USER)){
            log.info("======用户未登录======");
            throw new InsufficientAuthenticationException(AuthMessage.USER_NOT_LOGIN.message());
        }
        if (!CollectionUtils.isEmpty(configAttributes)) {
            for (ConfigAttribute configAttribute : configAttributes) {
                String needRole = configAttribute.getAttribute();
                log.info("======当前访问URL: 【{}】所需要的角色: 【{}】", object, needRole);
                for (GrantedAuthority ga : authentication.getAuthorities()) {
                    if (needRole.trim().equals(ga.getAuthority().trim())) {
                        log.info("======决策器决策当前访问URL: 【{}】有访问权限，放行======",object);
                        return;
                    }
                }
            }
            log.error("======决策器决策当前访问URL: 【{}】没有有访问权限，阻拦======",object);
            throw new AccessDeniedException(AuthMessage.NO_ACCESS_PERMISSION.message());
        }
    }

    /**
     * 表示此AccessDecisionManager是否能够处理传递的ConfigAttribute呈现的授权请求
     */
    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    /**
     * 表示当前AccessDecisionManager实现是否能够为指定的安全对象（方法调用或Web请求）提供访问控制决策
     */
    @Override
    public boolean supports(Class<?> aClass) {
        return FilterInvocation.class.isAssignableFrom(aClass);
    }
}

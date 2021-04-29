package com.easyiat.framework.security.ienum;

/**
 * @description:
 * @author: changchun_wu
 * @version: 1.0
 * @blame: Test Team
 **/
public class SecuritySource {
    //认证页面的配置
    /**
     * 认证页面的配置
     */
    public static final String LOGIN_FROM_USERNAME_PRAM="username";
    public static final String LOGIN_FROM_PASSWORD_PRAM="password";
    public static final String LOGIN_FROM_REMEMBER_ME_PRAM="remember-me";
    public static final String REMEMBER_ME_COOKIE_NAME="REPOSITORY_USER";
    /**
     * 首页
     */
    public static final String HOME_PAGE="/easyiat/index.html";
    /**
     * 登录页
     */
    public static final String LOGIN_PAGE="/easyiat/login.html";
    /**
     * 注册页
     */
    public static final String REGISTER_PAGE="/easyiat/register.html";
    /**
     * error页
     */
    public static final String ERROR_PAGE="/easyiat/error";

    /**
     * 成功页
     */
    public static final String SUCCESS_PAGE="/easyiat/*/success.html";
    /**
     * 登出页
     */
    public static final String LOGOUT_PAGE = "/easyiat/logout";

    /**
     * openapi的网页
     */
    public static final String API_URL="/easyiat/api/**";
    /**
     * 认证页
     */
    public static final String AUTHENTICATION_URL = "/easyiat/login";

    /**
     * 注册的URL
     */
    public static final String REGISTER_URL = "/easyiat/register";
    /**
     * 要放行的静态资源
     */
    public static final String[] STATIC = {"/easyiat/js/**","/easyiat/css/**","/easyiat/fonts/**","/easyiat/images/**"};
    /**
     * druid连接池的URL pattern
     */
}

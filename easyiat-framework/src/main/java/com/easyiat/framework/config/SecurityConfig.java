package com.easyiat.framework.config;

import com.alibaba.fastjson.JSONObject;
import com.easyiat.common.ienum.BaseMediaType;
import com.easyiat.common.exception.JSONResult;
import com.easyiat.framework.security.core.DecisionManager;
import com.easyiat.framework.security.core.SecurityMetadataSource;
import com.easyiat.framework.security.core.UserServiceImpl;
import com.easyiat.framework.security.ienum.AuthMessage;
import com.easyiat.framework.security.ienum.SecuritySource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.util.DigestUtils;

import java.util.Objects;


/**
 * @description: 安全配置器
 * @author: changchun_wu
 * @version: 1.0
 * @blame: Test Team
 **/

@Slf4j
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${spring.security.logout.clearAuthentication:true}")
    private Boolean clearAuthentication;

    @Value("${spring.security.logout.invalidateHttpSession:true}")
    private Boolean invalidateHttpSession;

    @Value("${spring.security.logout.deleteCookies:JSESSIONID}")
    private String[] deleteCookies;


    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private UserLoginTokenRepository userLoginToken;

    @Autowired
    private SecurityMetadataSource securityMetadataSource;

    @Autowired
    private DecisionManager decisionManager;

    /**
     * 认证配置
     *
     * @param http security认证
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //表单的配置
        http.formLogin()
                //配置表单中用户名的name
                .usernameParameter(SecuritySource.LOGIN_FROM_USERNAME_PRAM)
                //配置表单中密码的name
                .passwordParameter(SecuritySource.LOGIN_FROM_PASSWORD_PRAM)
                //登录页
                .loginPage(SecuritySource.LOGIN_PAGE)
                //认证的请求
                .loginProcessingUrl(SecuritySource.AUTHENTICATION_URL)
                //认证通过
                .successHandler(authenticationSuccessHandler())
                //认证失败
                .failureHandler(authenticationFailureHandler());
        http.logout()
                .logoutUrl(SecuritySource.LOGOUT_PAGE)
                .clearAuthentication(clearAuthentication)
                .invalidateHttpSession(invalidateHttpSession)
                .deleteCookies(deleteCookies)
                .logoutSuccessHandler(logoutSuccessHandler());
        http.headers().frameOptions().sameOrigin();

        //记住我功能
        http.rememberMe()
                //记住我的表单name
                .rememberMeParameter(SecuritySource.LOGIN_FROM_REMEMBER_ME_PRAM)
                //令牌存储的方式-这里使用redis
                //cookie名称
                .rememberMeCookieName(SecuritySource.REMEMBER_ME_COOKIE_NAME)
                .tokenValiditySeconds(30)
                .tokenRepository(userLoginToken)
                //用户详情
                .userDetailsService(userService);
        //授权
        http.authorizeRequests()
                //url决策
                .withObjectPostProcessor(objectPostProcessor())
                .anyRequest().authenticated();
        //异常处理
        http.exceptionHandling()
                //访问拒绝处理
                .authenticationEntryPoint(authenticationEntryPoint())
                .accessDeniedHandler(accessDeniedHandler());
        //关闭csrf
        http.csrf()
                .disable();
    }

    /**
     * 免认证页面的配置
     *
     * @param web WebSecurity
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        log.info("======初始化免认证登录页和静态资源放行======");
        web.ignoring()
                .antMatchers(SecuritySource.HOME_PAGE)
                .antMatchers(SecuritySource.LOGIN_PAGE)
                .antMatchers(SecuritySource.REGISTER_PAGE)
                .antMatchers(SecuritySource.ERROR_PAGE)
                .antMatchers(SecuritySource.SUCCESS_PAGE)
                .antMatchers(SecuritySource.REGISTER_URL)
                .antMatchers("/easyiat/system/**")
                .antMatchers(SecuritySource.API_URL)
                .antMatchers(SecuritySource.STATIC);
        log.info("======初始化免认证登录页和静态资源放行成功======");
    }

    /**
     * 身份验证
     * 对表单中提交的用户信息校验
     *
     * @param auth 身份验证
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(new PasswordEncoder() {
            @Override
            public String encode(CharSequence charSequence) {
                return DigestUtils.md5DigestAsHex(charSequence.toString().getBytes());
            }

            @Override
            public boolean matches(CharSequence charSequence, String password) {
                if (StringUtils.isEmpty(charSequence)) {
                    log.info("======用户登录密码为空======");
                    throw new BadCredentialsException(AuthMessage.PASSWORD_EMPTY.message());
                }
                String md5Password = DigestUtils.md5DigestAsHex(charSequence.toString().getBytes());
                boolean correct = Objects.equals(md5Password, password);
                log.info("======用户登录密码是否正确: {}======", correct);
                if (!correct) {
                    throw new BadCredentialsException(AuthMessage.PASSWORD_INCORRECT.message());
                }
                return true;
            }
        });
    }

    /**
     * 对象前置处理器
     *
     * @return ObjectPostProcessor
     */
    protected ObjectPostProcessor<FilterSecurityInterceptor> objectPostProcessor() {
        return new ObjectPostProcessor<FilterSecurityInterceptor>() {
            @Override
            public <O extends FilterSecurityInterceptor> O postProcess(O object) {
                object.setSecurityMetadataSource(securityMetadataSource);
                object.setAccessDecisionManager(decisionManager);
                return object;
            }
        };
    }

    /**
     * 登录失败处理
     *
     * @return AuthenticationFailureHandler
     */
    protected AuthenticationFailureHandler authenticationFailureHandler() {
        return (request, response, authenticationException) -> {
            log.info("=======用户验证失败======");
            String contentType = request.getHeader(BaseMediaType.CONTENT_TYPE);
            if (!StringUtils.isEmpty(contentType)) {
                if (contentType.contains(BaseMediaType.APPLICATION_FORM_URLENCODED) || contentType.contains(BaseMediaType.APPLICATION_JSON)) {
                    response.setContentType(BaseMediaType.APPLICATION_JSON_UTF8);
                    response.getWriter().print(JSONObject.toJSONString(JSONResult.build(AuthMessage.NO_ACCESS_PERMISSION.code(), authenticationException.getMessage())));
                } else {
                    response.sendRedirect(SecuritySource.LOGIN_PAGE);
                }
            } else {
                response.sendRedirect(SecuritySource.LOGIN_PAGE);

            }
        };
    }

    /**
     * 认证失败处理
     *
     * @return AccessDeniedHandler
     */
    protected AccessDeniedHandler accessDeniedHandler() {
        log.info("======当前认证失败=======");
        return (request, response, accessDeniedException) -> {
            String contentType = request.getHeader(BaseMediaType.CONTENT_TYPE);
            if (!StringUtils.isEmpty(contentType)) {
                if (contentType.contains(BaseMediaType.APPLICATION_FORM_URLENCODED) || contentType.contains(BaseMediaType.APPLICATION_JSON)) {
                    response.setContentType(BaseMediaType.APPLICATION_JSON_UTF8);
                    response.getWriter().print(JSONObject.toJSONString(JSONResult.build(AuthMessage.NO_ACCESS_PERMISSION.code(), accessDeniedException.getMessage())));
                } else {
                    response.sendRedirect(SecuritySource.LOGIN_PAGE);
                }
            } else {
                response.sendRedirect(SecuritySource.LOGIN_PAGE);

            }
        };
    }

    /**
     * 认证过程异常处理
     *
     * @return AuthenticationEntryPoint
     */
    protected AuthenticationEntryPoint authenticationEntryPoint() {
        log.error("=======当前未认证======");
        return (request, response, authenticationException) -> {
            String contentType = request.getHeader(BaseMediaType.CONTENT_TYPE);
            if (!StringUtils.isEmpty(contentType)) {
                if (contentType.contains(BaseMediaType.APPLICATION_FORM_URLENCODED) || contentType.contains(BaseMediaType.APPLICATION_JSON)) {
                    JSONResult dataResult;
                    if (authenticationException.getMessage().equals(AuthMessage.USER_NOT_LOGIN.message())) {
                        dataResult = JSONResult.build(AuthMessage.USER_NOT_LOGIN);
                    } else {
                        dataResult = JSONResult.build(AuthMessage.NO_ACCESS_PERMISSION.code(), authenticationException.getMessage());
                    }
                    response.setContentType(BaseMediaType.APPLICATION_JSON_UTF8);
                    response.getWriter().print(JSONObject.toJSONString(dataResult));
                } else {
                    response.sendRedirect(SecuritySource.LOGIN_PAGE);
                }
            } else {
                response.sendRedirect(SecuritySource.LOGIN_PAGE);

            }
        };
    }

    /**
     * 认证通过处理
     *
     * @return AuthenticationSuccessHandler
     */
    protected AuthenticationSuccessHandler authenticationSuccessHandler() {
        log.info("======登录成功处理======");
        return (request, response, authentication) -> {
            response.setContentType(BaseMediaType.APPLICATION_JSON_UTF8);
            if (authentication == null || !authentication.isAuthenticated()) {
                log.error("======用户登录失败======");
                response.getWriter().print(JSONObject.toJSONString(JSONResult.build(AuthMessage.LOGIN_FAILURE)));
            } else {
                log.info("======用户登录成功======");
                response.getWriter().print(JSONObject.toJSONString(JSONResult.build(AuthMessage.LOGIN_SUCCESS)));
            }
        };
    }

    /**
     * 登录成功处理
     *
     * @return LogoutSuccessHandler
     */
    protected LogoutSuccessHandler logoutSuccessHandler() {
        log.info("======登出成功处理======");
        return (request, response, authentication) -> {
            response.setContentType(BaseMediaType.APPLICATION_JSON_UTF8);
            //用户凭证已删除
            if (authentication != null && authentication.isAuthenticated()) {
                //将认证状态设置未false
                try {
                    request.getSession().invalidate();
                    SecurityContextHolder.getContext().setAuthentication(null);
                    response.getWriter().print(JSONObject.toJSONString(JSONResult.build(AuthMessage.LOGOUT_SUCCESS)));
                } catch (IllegalArgumentException e) {
                    log.error("======业务异常：", e);
                    response.getWriter().print(JSONObject.toJSONString(JSONResult.build(AuthMessage.LOGOUT_FAILURE)));
                }
            } else {
                response.getWriter().print(JSONObject.toJSONString(JSONResult.build(AuthMessage.LOGOUT_SUCCESS)));
            }
        };
    }
}

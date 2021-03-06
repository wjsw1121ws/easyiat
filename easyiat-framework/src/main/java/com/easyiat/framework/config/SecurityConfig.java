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
 * @description: ???????????????
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
     * ????????????
     *
     * @param http security??????
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //???????????????
        http.formLogin()
                //???????????????????????????name
                .usernameParameter(SecuritySource.LOGIN_FROM_USERNAME_PRAM)
                //????????????????????????name
                .passwordParameter(SecuritySource.LOGIN_FROM_PASSWORD_PRAM)
                //?????????
                .loginPage(SecuritySource.LOGIN_PAGE)
                //???????????????
                .loginProcessingUrl(SecuritySource.AUTHENTICATION_URL)
                //????????????
                .successHandler(authenticationSuccessHandler())
                //????????????
                .failureHandler(authenticationFailureHandler());
        http.logout()
                .logoutUrl(SecuritySource.LOGOUT_PAGE)
                .clearAuthentication(clearAuthentication)
                .invalidateHttpSession(invalidateHttpSession)
                .deleteCookies(deleteCookies)
                .logoutSuccessHandler(logoutSuccessHandler());
        http.headers().frameOptions().sameOrigin();

        //???????????????
        http.rememberMe()
                //??????????????????name
                .rememberMeParameter(SecuritySource.LOGIN_FROM_REMEMBER_ME_PRAM)
                //?????????????????????-????????????redis
                //cookie??????
                .rememberMeCookieName(SecuritySource.REMEMBER_ME_COOKIE_NAME)
                .tokenValiditySeconds(30)
                .tokenRepository(userLoginToken)
                //????????????
                .userDetailsService(userService);
        //??????
        http.authorizeRequests()
                //url??????
                .withObjectPostProcessor(objectPostProcessor())
                .anyRequest().authenticated();
        //????????????
        http.exceptionHandling()
                //??????????????????
                .authenticationEntryPoint(authenticationEntryPoint())
                .accessDeniedHandler(accessDeniedHandler());
        //??????csrf
        http.csrf()
                .disable();
    }

    /**
     * ????????????????????????
     *
     * @param web WebSecurity
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        log.info("======????????????????????????????????????????????????======");
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
        log.info("======??????????????????????????????????????????????????????======");
    }

    /**
     * ????????????
     * ???????????????????????????????????????
     *
     * @param auth ????????????
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
                    log.info("======????????????????????????======");
                    throw new BadCredentialsException(AuthMessage.PASSWORD_EMPTY.message());
                }
                String md5Password = DigestUtils.md5DigestAsHex(charSequence.toString().getBytes());
                boolean correct = Objects.equals(md5Password, password);
                log.info("======??????????????????????????????: {}======", correct);
                if (!correct) {
                    throw new BadCredentialsException(AuthMessage.PASSWORD_INCORRECT.message());
                }
                return true;
            }
        });
    }

    /**
     * ?????????????????????
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
     * ??????????????????
     *
     * @return AuthenticationFailureHandler
     */
    protected AuthenticationFailureHandler authenticationFailureHandler() {
        return (request, response, authenticationException) -> {
            log.info("=======??????????????????======");
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
     * ??????????????????
     *
     * @return AccessDeniedHandler
     */
    protected AccessDeniedHandler accessDeniedHandler() {
        log.info("======??????????????????=======");
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
     * ????????????????????????
     *
     * @return AuthenticationEntryPoint
     */
    protected AuthenticationEntryPoint authenticationEntryPoint() {
        log.error("=======???????????????======");
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
     * ??????????????????
     *
     * @return AuthenticationSuccessHandler
     */
    protected AuthenticationSuccessHandler authenticationSuccessHandler() {
        log.info("======??????????????????======");
        return (request, response, authentication) -> {
            response.setContentType(BaseMediaType.APPLICATION_JSON_UTF8);
            if (authentication == null || !authentication.isAuthenticated()) {
                log.error("======??????????????????======");
                response.getWriter().print(JSONObject.toJSONString(JSONResult.build(AuthMessage.LOGIN_FAILURE)));
            } else {
                log.info("======??????????????????======");
                response.getWriter().print(JSONObject.toJSONString(JSONResult.build(AuthMessage.LOGIN_SUCCESS)));
            }
        };
    }

    /**
     * ??????????????????
     *
     * @return LogoutSuccessHandler
     */
    protected LogoutSuccessHandler logoutSuccessHandler() {
        log.info("======??????????????????======");
        return (request, response, authentication) -> {
            response.setContentType(BaseMediaType.APPLICATION_JSON_UTF8);
            //?????????????????????
            if (authentication != null && authentication.isAuthenticated()) {
                //????????????????????????false
                try {
                    request.getSession().invalidate();
                    SecurityContextHolder.getContext().setAuthentication(null);
                    response.getWriter().print(JSONObject.toJSONString(JSONResult.build(AuthMessage.LOGOUT_SUCCESS)));
                } catch (IllegalArgumentException e) {
                    log.error("======???????????????", e);
                    response.getWriter().print(JSONObject.toJSONString(JSONResult.build(AuthMessage.LOGOUT_FAILURE)));
                }
            } else {
                response.getWriter().print(JSONObject.toJSONString(JSONResult.build(AuthMessage.LOGOUT_SUCCESS)));
            }
        };
    }
}

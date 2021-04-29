package com.easyiat.framework.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

/**
 * @description: security认证提示语国际化
 * @author: changchun_wu
 * @version: 1.0
 * @blame: Test Team
 **/

@Slf4j
@Configuration
public class BundleMessageConfig {
    /**
     * security认证提示语
     * @return  ReloadableResourceBundleMessageSource
     */
    @Bean
    public ReloadableResourceBundleMessageSource messageSource() {
        log.info("======设置security认证提示信息国际化=====");
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        // .properties 不要加到后面
        messageSource.setBasename("classpath:org/springframework/security/messages_zh_CN");
        log.info("======设置security认证提示信息国际化成功=====");
        return messageSource;
    }
}

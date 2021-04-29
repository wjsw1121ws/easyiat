package com.easyiat.framework.ienum;

/**
 * @description: Redis缓存中存取数据的前置
 * @author: changchun_wu
 * @version: 1.0
 * @blame: Test Team
 **/
public class RedisProp {
    public static final String REMEMBER_ME_KEY_PREFIX = "spring:security:rememberMe:";
    public static final Long REMEMBER_ME_TOKEN_EXPIRE_DAY = 15L;
    public static final String URL_ROLE_LIST_KEY = "url:role:list:key:";
}

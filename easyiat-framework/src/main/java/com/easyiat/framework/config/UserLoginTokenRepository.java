package com.easyiat.framework.config;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSONObject;
import com.easyiat.common.ienum.BaseSymbol;
import com.easyiat.framework.ienum.RedisProp;
import com.easyiat.framework.security.pojo.RememberMeVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Component;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @description: 页面记住我配置
 * @author: changchun_wu
 * @version: 1.0
 * @blame: Test Team
 **/

@Slf4j
@Component
public class UserLoginTokenRepository implements PersistentTokenRepository {

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 将被记住的用户存放在redis中
     *
     * @param token token
     */
    @Override
    public void createNewToken(PersistentRememberMeToken token) {
        /*
         * 认证通过后在登录页继续登录
         * 先将之前存放的token删除
         */
        log.info("======将用户: 【{}】的信息保存到redis中======",token.getUsername());
        removeUserTokens(token.getUsername());
        String tokenKey = generateTokenKey(token.getSeries());
        long time = token.getDate().getTime();
        RememberMeVo rememberMeVo = RememberMeVo.builder()
                .username(token.getUsername())
                .token(token.getTokenValue())
                .date(time).build();
        redisTemplate.opsForValue().set(tokenKey, JSONObject.toJSONString(rememberMeVo), RedisProp.REMEMBER_ME_TOKEN_EXPIRE_DAY, TimeUnit.DAYS);
        saveUsernameAndSeries(token.getUsername(), token.getSeries());
        log.info("======将用户用户: 【{}】的信息保存到redis成功======",token.getUsername());


    }

    /**
     * 更新被记住账户
     *
     * @param series   分组
     * @param tokenVal redis中key的值
     * @param lastUsed 最后操作使时间
     */
    @Override
    public void updateToken(String series, String tokenVal, Date lastUsed) {
        log.info("======更新分组: 【{}】在redis中的信息======",series);
        PersistentRememberMeToken tokenForSeries = getTokenForSeries(series);
        if (tokenForSeries != null) {
            RememberMeVo rememberMeVo = RememberMeVo.builder()
                    .username(tokenForSeries.getUsername())
                    .token(tokenVal)
                    .date(lastUsed.getTime()).build();
            redisTemplate.opsForValue().set(generateTokenKey(series), JSONObject.toJSONString(rememberMeVo), RedisProp.REMEMBER_ME_TOKEN_EXPIRE_DAY, TimeUnit.DAYS);
            saveUsernameAndSeries(tokenForSeries.getUsername(), series);
            log.info("======更新分组: 【{}】在redis中的信息成功======",series);
        }
        // 第二种实现方式
        /*String tokenKey = generateTokenKey(series);
        if (redisTemplate.hasKey(tokenKey)){
            String seriesKey = generateUsernameOrSeries(series);
            String username = redisTemplate.opsForValue().get(seriesKey);
            RememberMeVo rememberMeVo = RememberMeVo.builder()
                    .username(username)
                    .token(tokenVal)
                    .date(lastUsed.getTime()).build();
            redisTemplate.opsForValue().set(generateTokenKey(series), JSONObject.toJSONString(rememberMeVo), RedisProp.REMEMBER_ME_TOKEN_EXPIRE_DAY, TimeUnit.DAYS);
            saveUsernameAndSeries(username, series);
        }*/
    }

    /**
     * 通过分组获取token
     *
     * @param series 分组
     * @return PersistentRememberMeToken
     */
    @Override
    public PersistentRememberMeToken getTokenForSeries(String series) {
        log.info("=======获取分组: 【{}】在redis中的信息",series);
        String tokenKey = generateTokenKey(series);
        String tokenVal = redisTemplate.opsForValue().get(tokenKey);
        if (!StringUtils.isEmpty(tokenVal)) {
            RememberMeVo rememberMeVo = JSONObject.parseObject(tokenVal, RememberMeVo.class);
            if (!BeanUtil.isEmpty(rememberMeVo)) {
                return new PersistentRememberMeToken(rememberMeVo.getUsername(), series, rememberMeVo.getToken(), new Date(rememberMeVo.getDate()));
            }
        }
        log.info("=======获取分组: 【{}】在redis中的信息不存在",series);
        return null;
    }

    /**
     * 移除记住我的状态
     *
     * @param username 用户名
     */
    @Override
    public void removeUserTokens(String username) {
        log.info("======删除用户: 【{}】在redis中的信息",username);
        String usernameKey = generateUsernameOrSeries(username);
        String series = redisTemplate.opsForValue().get(usernameKey);
        if (!StringUtils.isEmpty(series)) {
            redisTemplate.delete(generateTokenKey(series));
            redisTemplate.delete(generateUsernameOrSeries(username));
            redisTemplate.delete(generateUsernameOrSeries(series));
            log.info("======删除用户: 【{}】在redis中的信息成功",username);
        }
    }

    /**
     * 自定义存放的token的key
     *
     * @param series 分组
     * @return String
     */
    private String generateTokenKey(String series) {
        return RedisProp.REMEMBER_ME_KEY_PREFIX + RememberMeVo.TOKEN + BaseSymbol.COLON + series;
    }

    /**
     * 同过用户名或者分组获取分组或用户名
     *
     * @param s 用户名/分组
     * @return String
     */
    private String generateUsernameOrSeries(String s) {
        return RedisProp.REMEMBER_ME_KEY_PREFIX + s;
    }

    /**
     * 将用户名和分组存放在redis中
     *
     * @param username 用户名
     * @param series   分组
     */
    private void saveUsernameAndSeries(String username, String series) {
        redisTemplate.opsForValue().set(generateUsernameOrSeries(username), series, RedisProp.REMEMBER_ME_TOKEN_EXPIRE_DAY, TimeUnit.DAYS);
        redisTemplate.opsForValue().set(generateUsernameOrSeries(series), username, RedisProp.REMEMBER_ME_TOKEN_EXPIRE_DAY, TimeUnit.DAYS);
    }
}

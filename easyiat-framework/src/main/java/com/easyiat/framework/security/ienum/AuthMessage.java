package com.easyiat.framework.security.ienum;

import com.easyiat.common.exception.BaseResult;

/**
 * @description:
 * @author: changchun_wu
 * @version: 1.0
 * @blame: Test Team
 **/
public enum AuthMessage implements BaseResult {
    /**
     * 登录成功
     */
    LOGIN_SUCCESS(1000,"登陆成功"),

    /**
     * 登出账户成功
     */
    LOGOUT_SUCCESS(1000,"登出账户成功"),

    /**
     * 登录失败
     */
    LOGIN_FAILURE(1001,"登陆失败，请重试"),

    /**
     * 登录失败
     */
    LOGOUT_FAILURE(1001,"登出账户失败"),
    /**
     * 用户名为空
     */
    USERNAME_EMPTY(1001,"用户名不能为空"),
    /**
     * 用户名为空
     */
    PASSWORD_EMPTY(1001,"密码不能为空"),
    /**
     * 用户不存在
     */
    USERNAME_NOT_EXIST(1001,"用户不存在"),
    /**
     * 用户查询到多个有效账户
     */
    MULTI_ENABLED_USERNAME(1001,"用户名对应多个有效用户,请联系管理员"),

    /**
     * 密码错误
     */
    PASSWORD_INCORRECT(1001,"密码错误，请重试"),

    /**
     * 用户被锁
     */
    ACCOUNT_LOCKED(1001,"用户被锁，请联系管理员"),

    /**
     * 密码过期
     */
    PASSWORD_EXPIRED(1001,"密码过期，请联系管理员"),

    /**
     * 账户过期
     */
    ACCOUNT_EXPIRED(1001,"账户过期，请联系管理员"),

    /**
     * 账户禁用
     */
    ACCOUNT_DISABLED(1001,"账户被禁用，请联系管理员"),

    /**
     * 用户名对应多个无效账户
     */
    MULTI_DISABLED_USERNAME(1001,"账户不可用，请联系管理员"),
    /**
     * 用户未登录
     */
    USER_NOT_LOGIN(2001,"用户未登录"),
    /**
     * 没有访问权限
     */
    NO_ACCESS_PERMISSION(2000,"当前没有访问权限，请联系管理员"),
    /**
     * 没有系统的访问权限
     */
    NO_SYSTEM_PERMISSION(2002,"没有系统的访问权限，请联系管理员");

    private final Integer code;
    private final String message;

    AuthMessage(Integer code, String message){
        this.code = code;
        this.message=message;
    }
    @Override
    public Integer code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }
}

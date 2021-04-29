package com.easyiat.framework.ienum;

import com.easyiat.common.exception.BaseResult;

/**
 * @description: 注册提示信息
 * @author: changchun_wu
 * @version: 1.0
 * @blame: Test Team
 **/
public enum RegisterMessage implements BaseResult {
    /**
     * 账户注册成功
     */
    REGISTER_SUCCESS(1000,"账户注册成功"),
    /**
     * 用户名已存在
     */
    USERNAME_EXISTS(1002,"用户名已被使用，换一个试试"),
    /**
     * 用户名已被使用
     */
    REGISTER_FAILURE(1002,"注册失败，请重试");

    private final Integer code;
    private final String message;

    RegisterMessage(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer code() {
        return this.code;
    }

    @Override
    public String message() {
        return this.message;
    }
}

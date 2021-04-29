package com.easyiat.common.exception;

/**
 * @description: 异常类接口
 * @author: changchun_wu
 * @version: 1.0
 * @blame: Test Team
 **/
public interface BaseResult {
    String MESSAGE = "message";
    /**
     * 获取错误状态码
     * @return code
     */
    Integer code();

    /**
     * 获取错苏信息
     * @return message
     */
    String message();

}

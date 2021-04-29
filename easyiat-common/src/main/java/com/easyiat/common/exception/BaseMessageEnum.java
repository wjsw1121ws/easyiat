package com.easyiat.common.exception;

/**
 * @description: 响应状态码和提示信息
 * @author: changchun_wu
 * @version: 1.0
 * @blame: Test Team
 **/


public enum BaseMessageEnum implements BaseResult {
    /**
     * 服务异常，后台报错
     */
    INTERNAL_SERVER_ERROR(-1,"服务异常"),
    /**
     * 请求成功
     */
    BASE_SUCCESS(1000,"success"),
    /**
     * 参数绑定异常
     */
    PARAM_VALID_ERROR(1001,"入参校验未通过"),
    /**
     *
     */
    JSON_PARSE_ERROR(1001,"参数反序列化失败，请检查参数是否正确"),

    /**
     * 没有访问权限
     */
    NO_ACCESS_PERMISSION(2000,"当前没有访问权限");


    private final Integer code;
    private final String message;
    BaseMessageEnum(Integer code, String message){
        this.code=code;
        this.message=message;
    }

    /**
     *
     * @return  code
     */
    @Override
    public Integer code() {
        return code;
    }

    /**
     *
     * @return message
     */
    @Override
    public String message() {
        return message;
    }
}

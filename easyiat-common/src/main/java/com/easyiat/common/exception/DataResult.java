package com.easyiat.common.exception;


import com.alibaba.fastjson.JSONObject;
import lombok.NoArgsConstructor;

/**
 * @description: 接口返回数据
 * @author: changchun_wu
 * @version: 1.0
 * @blame: Test Team
 **/

@NoArgsConstructor
public class DataResult {
    /**
     * 响应代码
     */
    private Integer code;

    /**
     * 响应消息
     */
    private String message;

    /**
     * 响应结果
     */
    private Object data;


    public DataResult(Integer code, String message){
        this.code = code;
        this.message = message;
    }

    public DataResult(BaseResult baseResult) {
        this.code = baseResult.code();
        this.message = baseResult.message();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getResult() {
        return data;
    }

    public void setResult(Object data) {
        this.data = data;
    }

    /**
     * 成功
     *
     * @return  DataResult
     */
    public static DataResult success() {
        DataResult rb = new DataResult();
        rb.setCode(BaseMessageEnum.BASE_SUCCESS.code());
        rb.setMessage(BaseMessageEnum.BASE_SUCCESS.message());
        return rb;
    }
    /**
     * 成功
     *
     * @return  DataResult
     */
    public static DataResult success(Object data) {
        DataResult rb = new DataResult();
        rb.setCode(BaseMessageEnum.BASE_SUCCESS.code());
        rb.setMessage(BaseMessageEnum.BASE_SUCCESS.message());
        rb.setResult(data);
        return rb;
    }

    /**
     *
     * @param baseResult   自定义的成功提示
     * @return  DataResult
     */
    public static DataResult success(BaseResult baseResult) {
        DataResult rb = new DataResult();
        rb.setCode(baseResult.code());
        rb.setMessage(baseResult.message());
        return rb;
    }

    /**
     * 自定义的成功提示
     * @param baseResult    自定义的成功提示
     * @param data  返回结果
     * @return  DataResult
     */
    public static DataResult success(BaseResult baseResult,Object data) {
        DataResult rb = new DataResult();
        rb.setCode(baseResult.code());
        rb.setMessage(baseResult.message());
        rb.setResult(data);
        return rb;
    }

    /**
     *
     * @param baseResult   自定义的成功提示
     * @param data  返回结果集
     * @return  DataResult
     */
    public static DataResult build(BaseResult baseResult, Object data) {
        DataResult rb = new DataResult();
        rb.setCode(baseResult.code());
        rb.setMessage(baseResult.message());
        rb.setResult(data);
        return rb;
    }

    /**
     * 成功
     * @param code  响应状态码
     * @param message   提示信息
     * @return  DataResult
     */
    public static DataResult success(Integer code, String message) {
        DataResult rb = new DataResult();
        rb.setCode(code);
        rb.setMessage(message);
        return rb;
    }


    /**
     * 失败
     * @param errorInfo baseError封装的参数
     * @return  DataResult
     */
    public static DataResult error(BaseResult errorInfo) {
        DataResult rb = new DataResult();
        rb.setCode(errorInfo.code());
        rb.setMessage(errorInfo.message());
        return rb;
    }

    /**
     * 失败
     * @param code  状态码
     * @param message   提示信息
     * @return  DataResult
     */
    public static DataResult error(Integer code, String message) {
        DataResult rb = new DataResult();
        rb.setCode(code);
        rb.setMessage(message);
        return rb;
    }

    /**
     * 失败
     * @param message   提示信息
     * @return  DataResult
     */
    public static DataResult error(String message) {
        DataResult rb = new DataResult();
        rb.setCode(-1);
        rb.setMessage(message);
        return rb;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }

}


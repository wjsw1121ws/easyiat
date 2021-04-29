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
public class JSONResult {
    /**
     * 响应代码
     */
    private Integer code;

    /**
     * 响应消息
     */
    private String message;


    public JSONResult(Integer code, String message){
        this.code = code;
        this.message = message;
    }

    public JSONResult(BaseResult baseResult) {
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


    /**
     *
     * @param baseResult   自定义的成功提示
     * @return  DataResult
     */
    public static JSONResult build(BaseResult baseResult) {
        JSONResult rb = new JSONResult();
        rb.setCode(baseResult.code());
        rb.setMessage(baseResult.message());
        return rb;
    }

    public static JSONResult build(Integer code , String message) {
        JSONResult rb = new JSONResult();
        rb.setCode(code);
        rb.setMessage(message);
        return rb;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }

}


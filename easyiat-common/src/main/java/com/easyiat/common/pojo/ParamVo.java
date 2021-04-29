package com.easyiat.common.pojo;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;

import java.io.Serializable;

/**
 * @description: 接口参数
 * @author: changchun_wu
 * @version: 1.0
 * @blame: Test Team
 **/

@Getter
public class ParamVo implements Serializable {
    private static final long serialVersionUID = -599577545383811410L;
    /**
     * 接口路径
     */
    private String path;

    /**
     * 请求头
     */
    private JSONObject header;

    /**
     * 请求参数
     */
    private JSONObject param;

    /**
     * 描述
     */
    private String description;

    /**
     * 预期响应code
     */
    private Integer code;

    /**
     * 预期响应message
     */
    private String message;

    /**
     * 预期响应code
     */
    private String resultCode;

    /**
     * 预期响应message
     */
    private String resultMessage;

    public void setPath(String path) {
        this.path = path==null?null:path.trim();
    }

    public void setHeader(JSONObject header) {
        this.header = header==null?new JSONObject():header;
    }

    public void setParam(JSONObject param) {
        this.param = param==null?new JSONObject():param;
    }

    public void setDescription(String description) {
        this.description = description==null?null:description.trim();
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode==null?null:resultCode.trim();
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }
}

package com.easyiat.common.pojo;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

/**
 * @description: 请求参数
 * @author: changchun_wu
 * @version: 1.0
 * @blame: Test Team
 **/

@Data
public class RequestVo {
    /**
     * 请求url
     */
    private String requestUrl;

    /**
     * 请求方法
     */
    private String requestMethod;

    /**
     * 请求参数
     */
    private JSONObject requestParam;

    /**
     * 请求头
     */
    private JSONObject requestHeader;
}

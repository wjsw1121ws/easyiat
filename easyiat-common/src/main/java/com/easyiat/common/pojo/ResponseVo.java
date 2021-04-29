package com.easyiat.common.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @description: Http请求相应结果
 * @author: changchun_wu
 * @version: 1.0
 * @blame: Test Team
 **/

@Data
public class ResponseVo implements Serializable {

    private static final long serialVersionUID = 7454246074596454298L;
    /**
     * 状态码
     */
    public Integer statusCode;

    /**
     * 相应结果
     */
    public String responseBody;

    /**
     * 文件路径
     */
    public String responseFilePath;
}

package com.easyiat.framework.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @description:
 * @author: changchun_wu
 * @version: 1.0
 * @blame: Test Team
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterUserVo implements Serializable {
    private static final long serialVersionUID = -2093580520965525660L;
    /**
     * 用户ID
     */
    private Integer userId;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;

}

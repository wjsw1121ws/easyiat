package com.easyiat.framework.security.pojo;

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
public class RememberMeVo implements Serializable {

    private static final long serialVersionUID = -3904087248948845485L;
    private String username;
    private String token;
    private Long date;

    public static final String TOKEN = "token";
}

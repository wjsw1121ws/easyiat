package com.easyiat.system.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
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
public class RegisterUserReq implements Serializable {
    private static final long serialVersionUID = -8478143195072261498L;
    /**
     * 昵称
     */
    @Length(max = 30,message = "昵称长度不能超过30位")
    private String nickname;

    /**
     * 用户名
     */
    @NotEmpty(message = "用户名不能为空")
    @Pattern(regexp = "^[a-zA-Z]\\w{4,29}$",message = "用户名以字母开头，只支持字母数字和下划线，长度5-30位")
    private String username;

    /**
     * 密码
     */
    @NotEmpty(message = "密码不能为空")
    @Pattern(regexp = "^[a-zA-Z]\\w{7,49}$",message = "密码以字母开头，只支持字母数字和下划线，长度8-50位")
    private String password;

    /**
     * 是否同意隐私和协议
     */
    @AssertTrue(message = "请先同意隐私和协议")
    private Boolean isAccepted;
}

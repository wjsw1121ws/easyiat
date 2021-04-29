package com.easyiat.system.pojo;

import com.easyiat.common.ienum.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: 登录类
 * @author: changchun_wu
 * @version: 1.0
 * @blame: Test Team
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements UserDetails, Serializable {
    private static final long serialVersionUID = 2087471964762178992L;
    /**
     * 用户ID
     */
    private Integer userId;
    /**
     * 用户名email
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 账户状态
     */
    private String status;
    /**
     * 角色
     */
    private List<Role> authorities = new ArrayList<>();

    @Override
    public boolean isAccountNonExpired() {
        return !this.status.equals(AccountStatus.EXPIRED);
    }

    @Override
    public boolean isAccountNonLocked() {
        return !this.status.equals(AccountStatus.LOCKED);
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !this.status.equals(AccountStatus.CREDENTIALS_EXPIRED);
    }

    @Override
    public boolean isEnabled() {
        return !this.status.equals(AccountStatus.DISABLE);
    }
}

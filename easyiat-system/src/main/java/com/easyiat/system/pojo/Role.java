package com.easyiat.system.pojo;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;

/**
 * @description: 角色
 * @author: changchun_wu
 * @version: 1.0
 * @blame: Test Team
 **/

@Data
public class Role implements GrantedAuthority, Serializable {
    private static final long serialVersionUID = -9184403522059431192L;
    /**
     * 角色ID
     */
    private Integer roleId;
    /**
     * 角色权限
     */
    private String roleKey;

    @Override
    public String getAuthority() {
        return roleKey;
    }
}

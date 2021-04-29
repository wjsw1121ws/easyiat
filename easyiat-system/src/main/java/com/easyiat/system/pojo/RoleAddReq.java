package com.easyiat.system.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

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
public class RoleAddReq {

    /**
     * 角色Id
     */
    private Integer roleId;
    /**
     * 角色名称
     */
    @NotBlank(message = "角色名称不能为空")
    @Length(max = 30,message = "角色名称不能超过30位")
    private String roleName;
    /**
     * 角色权限
     */
    @NotBlank(message = "角色权限不能为空")
    @Pattern(regexp = "^[A-Z][A-Z_]{5,99}$",message = "角色权限只支持大写字母和下划线，以大写字母开头，长度为6-100位")
    private String roleKey;

    /**
     * 描述/备注
     */
    @Length(message = "角色描述不能超过500位")
    private String remark;
}

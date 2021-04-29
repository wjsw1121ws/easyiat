package com.easyiat.system.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
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
public class RoleReq implements Serializable {
    private static final long serialVersionUID = 2587973175522400134L;
    /**
     * 角色名称
     */
    @Length(max = 30, message = "角色名称不能超过30位")
    private String roleName;

    @Valid
    @NotNull(message = "分页对象不能为空")
    private PageInfoVo pageInfo;

}

package com.easyiat.system.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
  * @description: 角色和菜单关联表
  * @author: changchun_wu
  * @version: 1.0 
  * @blame: Test Team
  **/
@ApiModel(value="com-easyiat-framework-entity-SysRoleMenu")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SysRoleMenu implements Serializable {
    /**
    * 角色ID
    */
    @ApiModelProperty(value="角色ID")
    private Integer roleId;

    /**
    * 菜单ID
    */
    @ApiModelProperty(value="菜单ID")
    private Integer menuId;

    private static final long serialVersionUID = 1L;
}
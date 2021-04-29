package com.easyiat.system.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
  * @description: 用户和角色关联表
  * @author: changchun_wu
  * @version: 1.0 
  * @blame: Test Team
  **/
@ApiModel(value="com-easyiat-framework-entity-SysUserRole")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SysUserRole implements Serializable {
    /**
    * 用户ID
    */
    @ApiModelProperty(value="用户ID")
    private Integer userId;

    /**
    * 角色ID
    */
    @ApiModelProperty(value="角色ID")
    private Integer roleId;

    private static final long serialVersionUID = 1L;
}
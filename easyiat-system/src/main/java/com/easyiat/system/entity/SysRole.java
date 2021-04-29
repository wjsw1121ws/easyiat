package com.easyiat.system.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Date;

/**
  * @description: 角色信息表
  * @author: changchun_wu
  * @version: 1.0 
  * @blame: Test Team
  **/
@ApiModel(value="com-easyiat-framework-entity-SysRole")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SysRole implements GrantedAuthority,Serializable {
    /**
    * 角色ID
    */
    @ApiModelProperty(value="角色ID")
    private Integer roleId;

    /**
    * 角色名称
    */
    @ApiModelProperty(value="角色名称")
    private String roleName;

    /**
    * 角色权限字符串
    */
    @ApiModelProperty(value="角色权限字符串")
    private String roleKey;

    /**
    * 显示顺序
    */
    @ApiModelProperty(value="显示顺序")
    private Integer roleSort;

    /**
    * 数据范围（1：全部数据权限 2：自定数据权限）
    */
    @ApiModelProperty(value="数据范围（1：全部数据权限 2：自定数据权限）")
    private String dataScope;

    /**
    * 角色状态（0正常 1停用）
    */
    @ApiModelProperty(value="角色状态（0正常 1停用）")
    private String status;

    /**
    * 删除标志（0代表存在 2代表删除）
    */
    @ApiModelProperty(value="删除标志（0代表存在 2代表删除）")
    private String delFlag;

    /**
    * 创建者
    */
    @ApiModelProperty(value="创建者")
    private String createBy;

    /**
    * 创建时间
    */
    @ApiModelProperty(value="创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date createTime;

    /**
    * 更新者
    */
    @ApiModelProperty(value="更新者")
    private String updateBy;

    /**
    * 更新时间
    */
    @ApiModelProperty(value="更新时间")
    private Date updateTime;

    /**
    * 备注
    */
    @ApiModelProperty(value="备注")
    private String remark;

    private static final long serialVersionUID = 1L;

    @Override
    public String getAuthority() {
        return this.roleKey;
    }
}
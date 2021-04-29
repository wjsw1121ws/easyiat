package com.easyiat.system.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
  * @description: 菜单权限表
  * @author: changchun_wu
  * @version: 1.0 
  * @blame: Test Team
  **/
@ApiModel(value="com-easyiat-framework-test-SysMenu")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SysMenu implements Serializable {
    /**
    * 菜单ID
    */
    @ApiModelProperty(value="菜单ID")
    private Integer menuId;

    /**
    * 菜单名称
    */
    @ApiModelProperty(value="菜单名称")
    private String menuName;

    /**
    * 父菜单ID
    */
    @ApiModelProperty(value="父菜单ID")
    private Integer parentId;

    /**
    * 显示顺序
    */
    @ApiModelProperty(value="显示顺序")
    private Integer orderNum;

    /**
    * 请求地址
    */
    @ApiModelProperty(value="请求地址")
    private String url;

    /**
    * 菜单类型（M-菜单 L-列表 U-URL B-按钮 A-API）
    */
    @ApiModelProperty(value="菜单类型（M-菜单 L-列表 U-URL B-按钮 A-API）")
    private String menuType;

    /**
    * 菜单状态（0显示 1隐藏）
    */
    @ApiModelProperty(value="菜单状态（0显示 1隐藏）")
    private String visible;

    /**
    * 权限标识
    */
    @ApiModelProperty(value="权限标识")
    private String perms;

    /**
    * 菜单图标
    */
    @ApiModelProperty(value="菜单图标")
    private String icon;

    /**
    * 创建者
    */
    @ApiModelProperty(value="创建者")
    private String createBy;

    /**
    * 创建时间
    */
    @ApiModelProperty(value="创建时间")
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
}
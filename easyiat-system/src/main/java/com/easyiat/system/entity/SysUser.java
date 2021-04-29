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
  * @description: 用户信息表
  * @author: changchun_wu
  * @version: 1.0 
  * @blame: Test Team
  **/
@ApiModel(value="com-easyiat-framework-entity-SysUser")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SysUser implements Serializable {
    /**
    * 用户ID
    */
    @ApiModelProperty(value="用户ID")
    private Integer userId;

    /**
    * 部门ID
    */
    @ApiModelProperty(value="部门ID")
    private String deptId;

    /**
    * 用户名
    */
    @ApiModelProperty(value="用户名")
    private String username;

    /**
    * 昵称
    */
    @ApiModelProperty(value="昵称")
    private String nickname;

    /**
    * 用户类型（00系统用户）
    */
    @ApiModelProperty(value="用户类型（00系统用户）")
    private String userType;

    /**
    * 用户邮箱
    */
    @ApiModelProperty(value="用户邮箱")
    private String email;

    /**
    * 手机号码
    */
    @ApiModelProperty(value="手机号码")
    private String phone;

    /**
    * 用户性别（0男 1女 2未知）
    */
    @ApiModelProperty(value="用户性别（0男 1女 2未知）")
    private String sex;

    /**
    * 头像路径
    */
    @ApiModelProperty(value="头像路径")
    private String avatar;

    /**
    * 密码
    */
    @ApiModelProperty(value="密码")
    private String password;

    /**
    * 盐加密
    */
    @ApiModelProperty(value="盐加密")
    private String salt;

    /**
    * 帐号状态（0正常 1停用）
    */
    @ApiModelProperty(value="帐号状态（0正常 1停用）")
    private String status;

    /**
    * 删除标志（0代表存在 2代表删除）
    */
    @ApiModelProperty(value="删除标志（0代表存在 2代表删除）")
    private String delFlag;

    /**
    * 默认项目ID
    */
    @ApiModelProperty(value="默认项目ID")
    private Integer projectId;

    /**
    * 最后登陆IP
    */
    @ApiModelProperty(value="最后登陆IP")
    private String loginIp;

    /**
    * 最后登陆时间
    */
    @ApiModelProperty(value="最后登陆时间")
    private Date loginDate;

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

    /**
    * 用户日期默认查询条件，单位：天
    */
    @ApiModelProperty(value="用户日期默认查询条件，单位：天")
    private Integer dateQuantum;

    private static final long serialVersionUID = 1L;
}
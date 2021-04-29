package com.easyiat.system.service;

import com.easyiat.common.pojo.PageResult;
import com.easyiat.system.entity.SysRole;
import com.easyiat.system.pojo.Role;
import com.easyiat.system.pojo.RoleAddReq;
import com.easyiat.system.pojo.RoleEditReq;
import com.easyiat.system.pojo.RoleReq;

import java.util.List;


/**
  * @description: 
  * @author: changchun_wu
  * @version: 1.0 
  * @blame: Test Team
  **/    
public interface SysRoleService{

    /**
     * 获取用户的角色列表
     * @param userId    用户id
     * @return  List<Role>角色列表
     */
    List<Role> getRoleListByUserId(Integer userId);

    /**
     * 分页查询角色列表
     * @param roleReq   角色名称和分页参数
     * @return  SysRole
     */
    PageResult<SysRole> selectRoleListByRoleName(RoleReq roleReq);

    /**
     * 角色权限是否可用
     * @param roleKey   角色权限
     * @return  Boolean
     */
    Boolean checkRoleKeyIsAvailable(String roleKey);

    /**
     * 创建角色
     * @param roleAddReq    角色名称，角色权限，角色备注
     */
    void insertRole(RoleAddReq roleAddReq);

    /**
     * 根据角色id查询角色
     * @param roleId    角色id
     * @return  SysRole
     */
    SysRole selectRoleByRoleId(Integer roleId);

    /**
     * 更新角色
     * @param roleEditReq   角色名称，权限，状态，备注信息
     * @return  Integer 更新行数
     */
    Integer updateRole(RoleEditReq roleEditReq);

    /**
     * 根据角色权限查询角色
     * @param roleKey   角色权限
     * @return  SysRole 角色信息
     */
    SysRole selectRoleByRoleKey(String roleKey);

    /**
     * 删除id角色
     * @param roleId    角色id
     * @return  Integer
     */
    Integer deleteRoleByRoleId(Integer roleId);
}

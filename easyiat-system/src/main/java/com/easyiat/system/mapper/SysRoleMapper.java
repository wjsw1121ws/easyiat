package com.easyiat.system.mapper;

import com.easyiat.system.entity.SysRole;
import com.easyiat.system.pojo.Role;
import com.easyiat.system.pojo.RoleAddReq;
import com.easyiat.system.pojo.RoleEditReq;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description:
 * @author: changchun_wu
 * @version: 1.0
 * @blame: Test Team
 **/
@Mapper
public interface SysRoleMapper {

    /**
     * 根据用户名ID用户的角色
     *
     * @param userId 用户ID
     * @return List<SysRole>
     */
    List<Role> getRoleListByUserId(Integer userId);

    /**
     * 根据角色名称查询角色列表
     *
     * @param roleName 角色名称
     * @return SysRole
     */
    List<SysRole> selectRoleListByRoleName(@Param("roleName") String roleName);

    /**
     * 查询角色是否可用
     *
     * @param roleKey 角色权限
     * @return Integer
     */
    Integer selectRoleCountByRoleKey(@Param("roleKey") String roleKey);

    /**
     * 创建角色
     *
     * @param roleAddReq 角色名称，角色权限，角色备注
     */
    void insertRole(@Param("roleAddReq") RoleAddReq roleAddReq);

    /**
     * 根据角色id查询角色
     *
     * @param roleId 角色id
     * @return SysRole
     */
    SysRole selectRoleByRoleId(@Param("roleId") Integer roleId);


    /**
     * 封信角色
     *
     * @param roleEditReq 角色名称，权限，状态，备注信息
     * @return Integer
     */
    Integer updateRole(@Param("roleEditReq") RoleEditReq roleEditReq);

    /**
     * 根据角色权限查询角色
     *
     * @param roleKey 角色权限
     * @return SysRole 角色信息
     */
    SysRole selectRoleByRoleKey(@Param("roleKey") String roleKey);

    /**
     * 删除id角色
     *
     * @param roleId 角色id
     * @return Integer
     */
    Integer deleteRoleByRoleId(@Param("roleKey") Integer roleId);
}
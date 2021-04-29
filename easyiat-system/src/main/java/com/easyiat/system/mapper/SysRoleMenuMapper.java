package com.easyiat.system.mapper;

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
public interface SysRoleMenuMapper {

    /**
     * 通过菜单id获取角色列表
     * @param menuId    菜单id
     * @return    List<String>
     */
    List<String> selectRoleListByMenuId(@Param("menuId") Integer menuId);
}
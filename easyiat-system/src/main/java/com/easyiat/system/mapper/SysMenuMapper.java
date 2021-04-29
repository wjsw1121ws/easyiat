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
public interface SysMenuMapper {

    /**
     * 根据url获取
     * @param url   当前请求的URL
     * @return List<String>
     */
    List<String> queryRoleKeyByUrl(@Param("url") String url);


    /**
     * 获取所有的请求URL
     * @param menuTypeList  菜单类型
     * @return  List<String>
     */
    List<String> selectUrlListByMenuType(@Param("menuTypeList") List<String> menuTypeList);

    /**
     * 获取url的root节点菜单id
     * @param url   url
     * @return  Integer
     */
    Integer selectRootItemIdByUrl(@Param("url") String url);
}
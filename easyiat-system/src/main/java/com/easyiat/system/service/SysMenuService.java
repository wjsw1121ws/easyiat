package com.easyiat.system.service;

import java.util.List;

/**
  * @description: 
  * @author: changchun_wu
  * @version: 1.0 
  * @blame: Test Team
  **/    
public interface SysMenuService{

    /**
     * 通过菜单类型获取菜单下的所有的url
     * @param menuTypeList  菜单类型
     * @return  List<String>
     */
    List<String> selectUrlListByMenuType(List<String> menuTypeList);

    /**
     * 获取url的角色列表
     * @param url   网址
     * @return  List<String>
     */
    List<String> queryRoleKeyByUrl(String url);
}

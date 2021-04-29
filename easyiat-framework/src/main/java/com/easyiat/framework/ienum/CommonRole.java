package com.easyiat.framework.ienum;

/**
 * @description:
 * @author: changchun_wu
 * @version: 1.0
 * @blame: Test Team
 **/
public class CommonRole {
    /**
     * 匿名用户--有所有前端的权限，提供给用户所有前端定义的页面查看权限，不用请求后台
     */
    public static final String ANONYMOUS_USER="anonymousUser";
    /**
     * 管理员用户--整个系统权限
     */
    public static final String ADMINISTRATOR="ROLE_ADMINISTRATOR";
    /**
     * 运维经理--有管理(为普通用户新增角色)和系统监控(系统日志信息管理)权限
     */
    public static final String OPERATIONS_MANAGER="ROLE_OPERATIONS_MANAGER";

    /**
     * 运维工程师--有管理(为普通用户新增角色)权限
     */
    public static final String OPERATION_ENGINEER="ROLE_OPERATION_ENGINEER";

    /**
     * 测试/开发工程师--有操作--接口，性能，功能自动化，接口自动化的权限
     */
    public static final String TEST_ENGINEER ="ROLE_TEST_ENGINEER";
    /**
     * 公共的角色，待定
     */
    public static final String COMMON="ROLE_COMMON";

}

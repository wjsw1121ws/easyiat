package com.easyiat.framework.ienum;

import com.easyiat.common.exception.BaseResult;

/**
 * @description:
 * @author: changchun_wu
 * @version: 1.0
 * @blame: Test Team
 **/
public enum RoleMessage implements BaseResult {
    /**
     * 角色创建成功
     */
    ROLE_CREATE_SUCCESS(1000,"角色创建成功"),
    /**
     * 角色创建成功
     */
    ROLE_UPDATE_SUCCESS(1000,"角色更新成功"),
    /**
     * 角色删除成功
     */
    ROLE_DELETE_SUCCESS(1000,"角色删除成功"),
    /**
     * 角色已存在
     */
    ROLE_KEY_EXIST(1001,"角色已被使用，换一个试试"),
    /**
     * 角色已存在
     */
    ROLE_ID_NOT_EXIST(1001,"角色编码不存在");

    private final Integer code;
    private final String message;

    RoleMessage(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer code() {
        return this.code;
    }

    @Override
    public String message() {
        return this.message;
    }
}

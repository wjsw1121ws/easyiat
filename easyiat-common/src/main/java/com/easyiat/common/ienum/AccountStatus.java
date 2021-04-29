package com.easyiat.common.ienum;

/**
 * @description: 账号状态
 * @author: changchun_wu
 * @version: 1.0
 * @blame: Test Team
 **/
public class AccountStatus {
    //帐号状态（0正常 1停用 2被锁 3过期 4凭证过期）
    /**
     * 账号可以正常使用
     */
    public static final String ENABLE = "0";
    /**
     * 账号不能用
     */
    public static final String DISABLE = "1";
    /**
     * 账号锁定
     */
    public static final String LOCKED = "2";
    /**
     * 账号过期
     */
    public static final String EXPIRED = "3";
    /**
     * 账号认证过期
     */
    public static final String CREDENTIALS_EXPIRED = "4";
}

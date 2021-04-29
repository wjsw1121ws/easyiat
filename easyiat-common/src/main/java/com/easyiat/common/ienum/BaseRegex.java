package com.easyiat.common.ienum;

/**
 * @description: 正则工具类
 * @author: changchun_wu
 * @version: 1.0
 * @blame: Test Team
 **/
public class BaseRegex {
    /**
     * ip正则
     */
    public static final String IP = "^((25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d)))\\.){3}(25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d)))$";
    /**
     * email正则
     */
    public static final String EMAIL = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
    /**
     * 域名正则
     */
    public static final String DOMAIN = "^[a-zA-Z0-9][-a-zA-Z0-9]{0,62}(\\.[a-zA-Z0-9][-a-zA-Z0-9]{0,62})+\\.?$";
    /**
     * 手号码正则
     */
    public static final String MOBILE_PHONE = "^(13[0-9]|14[5|7]|15[0|1|2|3|4|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$";
    /**
     * 电话号码正则
     */
    public static final String MOBILE = "^\\d{3}-\\d{8}|\\d{4}-\\d{7}$";
    /**
     * 身份证正则
     */
    public static final String ID_CARD = "^\\d{15}$)|(^\\d{18}$)|(^\\d{17}(\\d|X|x)$";
    /**
     * 用户名正则(格式: 6-30)
     */
    public static final String USERNAME = "^[a-zA-Z]\\w{5,29}$";
    /**
     * 密码正则，字母数字下划线，字母开头(格式: 8-50)
     */
    public static final String PASSWORD = "^[a-zA-Z]\\w{7,49}$";
    /**
     * 日期正则(格式: 2020-01-31)
     */
    public static final String DATE = "^\\d{4}-\\d{1,2}-\\d{1,2}$";
    /**
     * 月份正则
     */
    public static final String MONTH = "^0?[1-9]|1[0-2]$";
    /**
     * 某日正则
     */
    public static final String DAY = "^(0?[1-9])|((1|2)[0-9])|30|31$";
    /**
     * QQ号正则
     */
    public static final String QQ = "^[1-9][0-9]{4,}$";
    /**
     * 邮政编码
     */
    public static final String POSTAL = "^[1-9]\\d{5}(?!\\d)$";
    /**
     * IPV4地址
     */
    public static final String IPV4 = "^((2(5[0-5]|[0-4]\\d))|[0-1]?\\d{1,2})(\\.((2(5[0-5]|[0-4]\\d))|[0-1]?\\d{1,2})){3}$";
}

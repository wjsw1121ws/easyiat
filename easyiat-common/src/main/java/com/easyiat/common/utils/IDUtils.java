package com.easyiat.common.utils;

import com.easyiat.common.ienum.BaseSymbol;
import org.apache.commons.lang3.StringUtils;

import java.util.Random;
import java.util.UUID;

/**
 * @description:
 * @author: changchun_wu
 * @version: 1.0
 * @blame: Test Team
 **/
public class IDUtils {

    /**
     * 生成32位大写的UUID
     *
     * @return String
     */
    public static String generate32UpperUUID() {
        return UUID.randomUUID().toString().replaceAll(BaseSymbol.JOINER, StringUtils.EMPTY).toUpperCase();
    }

    /**
     * 生成32位小写的UUID
     *
     * @return String
     */
    public static String generate32LowerUUID() {
        return UUID.randomUUID().toString().replaceAll(BaseSymbol.JOINER, StringUtils.EMPTY);

    }

    /**
     * 生成16位的整形字符串ID
     * @return  String
     */
    public static String generate12StringID(){
        long time = System.currentTimeMillis();
        String id = String.valueOf(time);
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < id.length()-1; i++) {
            int index = new Random().nextInt(id.length());
            buffer.append(id.charAt(index));
        }
        return buffer.toString();
    }

    /**
     * 生成12位的整形ID
     * @return  Long
     */
    public static Long generate12LongID() {
        return Long.parseLong(generate12StringID());
    }


    /**
     * 生成16位的整形字符串ID
     * @return  String
     */
    public static String generate16StringID(){
        long time = System.currentTimeMillis();
        int random = new Random().nextInt(1000);
        String id = time + String.format("%03d", random);
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < id.length(); i++) {
            int index = new Random().nextInt(id.length());
            buffer.append(id.charAt(index));
        }
        return buffer.toString();
    }

    /**
     * 生成16位的整形ID
     * @return  Long
     */
    public static Long generate16LongID() {
        return Long.parseLong(generate16StringID());
    }

}

package com.ruoyi.common.utils.mock.commonUtils;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.UUID;

public class UUIDUtils {
    public UUIDUtils() {
    }

    /**
     * 自动生成32位的UUid，对应数据库的主键id进行插入用。
     * @return
     */
    public static String get32UUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /***
     * 自动生成指定长度的随机大小写字母字符串
     * @param length
     * @return
     */
    public static String getRandomStr(String length){
        return RandomStringUtils.randomAlphabetic(Integer.parseInt(length));
    }

    /***
     * 自动生成指定长度的随机数字字符串
     * @param length
     * @return
     */
    public static String getRandomNum(String length){
        return RandomStringUtils.randomNumeric(Integer.parseInt(length));
    }

    /***
     * 自动生成指定长度的随机数字字母混合字符串
     * @param length
     * @return
     */
    public static String getRandomStrNum(String length){
        return RandomStringUtils.randomAlphanumeric(Integer.parseInt(length));
    }
}

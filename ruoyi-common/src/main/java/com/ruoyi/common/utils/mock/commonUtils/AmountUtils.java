package com.ruoyi.common.utils.mock.commonUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;

public class AmountUtils {

    public static DecimalFormat fnum = new DecimalFormat("##0.00000000000000000000");

    /**
     * 格式化金额
     *
     * @param valueStr
     * @return String
     */
    public static String formatMoney(String valueStr) {
        if (valueStr == null || valueStr == "") {
            valueStr = "0.00";
        }
        return fnum.format(new BigDecimal(valueStr));
    }


    /**
     * 金额相加
     *
     * @param valueStr 基础值
     * @param addStr   被加数
     * @return String
     */
    public static String moneyAdd(String valueStr, String addStr) {
        BigDecimal value = new BigDecimal(valueStr);
        BigDecimal augend = new BigDecimal(addStr);
        return fnum.format(value.add(augend));
    }

    /**
     * 金额相加
     *
     * @param valueStr      基础值
     * @param minusValueStr 被加数
     * @return BigDecimal
     */
    public static BigDecimal moneyAdd(BigDecimal valueStr, BigDecimal minusValueStr) {
        return valueStr.add(minusValueStr);
    }

    /**
     * 金额相减
     *
     * @param valueStr      基础值
     * @param minusValueStr 减数
     * @return String
     */
    public static String moneySub(String valueStr, String minusValueStr) {
        BigDecimal value = new BigDecimal(valueStr);
        BigDecimal subtrahend = new BigDecimal(minusValueStr);
        return fnum.format(value.subtract(subtrahend));
    }

    /**
     * 金额相减
     *
     * @param value      基础值
     * @param subtrahend 减数
     * @return BigDecimal
     */
    public static BigDecimal moneySub(BigDecimal value, BigDecimal subtrahend) {
        return value.subtract(subtrahend);
    }


    /**
     * 金额相乘
     *
     * @param valueStr      基础值
     * @param minusValueStr 被乘数
     * @return String
     */
    public static String moneyMul(String valueStr, String minusValueStr) {
        BigDecimal value = new BigDecimal(valueStr);
        BigDecimal mulValue = new BigDecimal(minusValueStr);
        return fnum.format(value.multiply(mulValue));
    }

    /**
     * 金额相乘
     *
     * @param value    基础值
     * @param mulValue 被乘数
     * @return BigDecimal
     */
    public static BigDecimal moneyMul(BigDecimal value, BigDecimal mulValue) {
        return value.multiply(mulValue);
    }

    /**
     * 金额相除 <br/>
     * 精确小位小数
     *
     * @param valueStr      基础值
     * @param minusValueStr 被乘数
     * @return String
     */
    public static String moneydiv(String valueStr, String minusValueStr) {
        BigDecimal value = new BigDecimal(valueStr);
        BigDecimal divideValue = new BigDecimal(minusValueStr);
        return fnum.format(value.divide(divideValue, 2, BigDecimal.ROUND_HALF_UP));
    }

    /**
     * 金额相除 <br/>
     * 精确小位小数
     *
     * @param value       基础值
     * @param divideValue 被乘数
     * @return BigDecimal
     */
    public static BigDecimal moneydiv(BigDecimal value, BigDecimal divideValue) {
        return value.divide(divideValue, 2, BigDecimal.ROUND_HALF_UP);
    }


    /**
     * 值比较大小
     * <br/>如果valueStr大于等于compValueStr,则返回true,否则返回false
     * true 代表可用余额不足
     *
     * @param valueStr     (需要消费金额)
     * @param compValueStr (可使用金额)
     * @return boolean
     */
    public static boolean moneyComp(String valueStr, String compValueStr) {
        BigDecimal value = new BigDecimal(valueStr);
        BigDecimal compValue = new BigDecimal(compValueStr);
        //0:等于    >0:大于    <0:小于
        int result = value.compareTo(compValue);
        if (result >= 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 值比较大小
     * <br/>如果valueStr大于等于compValueStr,则返回true,否则返回false
     * true 代表可用余额不足
     *
     * @param valueStr     (需要消费金额)
     * @param compValueStr (可使用金额)
     * @return boolean
     */
    public static boolean moneyComp(BigDecimal valueStr, BigDecimal compValueStr) {
        //0:等于    >0:大于    <0:小于
        int result = valueStr.compareTo(compValueStr);
        if (result >= 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 金额乘以，省去小数点
     *
     * @param valueStr 基础值
     * @return String
     */
    public static String moneyMulOfNotPoint(String valueStr, String divideStr) {
        BigDecimal value = new BigDecimal(valueStr);
        BigDecimal mulValue = new BigDecimal(divideStr);
        valueStr = fnum.format(value.multiply(mulValue));
        return fnum.format(value.multiply(mulValue)).substring(0, valueStr.length() - 3);
    }

    /**
     * 元转分，确保price保留两位有效数字
     *
     * @return
     */
    public static BigDecimal changeY2F(String amount) {
        BigDecimal bigDecimal = new BigDecimal(amount).setScale(2);
        return bigDecimal.multiply(new BigDecimal(100));
    }

    /**
     * 分转元，转换为bigDecimal在toString
     *
     * @return
     */
    public static BigDecimal changeF2Y(String amount) {
        return BigDecimal.valueOf(Long.valueOf(amount)).divide(new BigDecimal(100));
    }

    /**
     * 将金额整数部分在千分位以逗号分隔表示
     *
     * @return
     */
    public static String toWestNumFormat(String amount) {
        if (null != amount && !"".equals(amount)) {
            DecimalFormat df = new DecimalFormat("#,###");
            int index = amount.indexOf(".");
            String integerPartial = "";
            String decimalPartial = "";
            if (index > 0) {
                integerPartial = amount.substring(0, index);
                long l = Long.parseLong(integerPartial);
                decimalPartial = amount.substring(index);
                return df.format(l) + decimalPartial;
            } else {
                long l = Long.parseLong(amount);
                return df.format(l);
            }
        }else{
            return "";
        }
    }

    /**
     * 将金额整数部分在万分位以逗号分隔表示
     *
     * @return
     */
    public static String toEastNumFormat(String amount) {
        if (null != amount && !"".equals(amount)) {
            DecimalFormat df = new DecimalFormat("#,####");
            int index = amount.indexOf(".");
            String integerPartial = "";
            String decimalPartial = "";
            if (index > 0) {
                integerPartial = amount.substring(0, index);
                long l = Long.parseLong(integerPartial);
                decimalPartial = amount.substring(index);
                return df.format(l) + decimalPartial;
            } else {
                long l = Long.parseLong(amount);
                return df.format(l);
            }
        }else{
            return "";
        }
    }

    /**
     * 消除金额逗号分隔符
     *
     * @return
     */
    public static String removeFormat(String amount) throws ParseException {
        return new DecimalFormat().parse(amount).toString();
    }
}


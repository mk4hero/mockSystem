package com.ruoyi.common.utils.mock;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.mock.commonUtils.AmountUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class FlStrMsgFuncUtils {
    public static final Logger logger = LoggerFactory.getLogger(FlStrMsgFuncUtils.class);

    /**** 节点数值、节点属性修改函数 ****/
    /***
     * 给指定值：无论当前节点的值是什么，都修改成指定的值
     * 节点数值 替换函数
     */
    public void infoChangeTo(int start, int length, String targetValue, String placeholders, StringBuffer targetFlStr, String value){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(placeholders);
        }
        targetFlStr.replace(start, start+length, sb.substring(value.length()) + value);
    }

    /***
     * 取默认值：如果节点不存在或者为空，那么按指定信息进行赋值
     * 节点数值 替换函数
     */
    public void getDefaultValue(int start, int length, String targetValue, String placeholders, StringBuffer targetFlStr, String defaultValue){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(placeholders);
        }

        if(null != targetValue && !"".equals(targetValue)){
            targetFlStr.replace(start, start+length, sb.substring(targetValue.length()) + targetValue);
        }else{
            targetFlStr.replace(start, start+length, sb.substring(defaultValue.length()) + defaultValue);
        }
    }

    /***
     * 带小数点的元模式转换成没有小数点的分模式
     */
    public void changeY2F(int start, int length, String targetValue, String placeholders, StringBuffer targetFlStr){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(placeholders);
        }

        targetValue = AmountUtils.changeY2F(targetValue).toString();
        targetFlStr.replace(start, start+length, sb.substring(targetValue.length()) + targetValue);
    }

    /***
     * 按照格式要求返回当前日期时间
     * @param format
     */
    public void getDateTime(int start, int length, String targetValue, String placeholders, StringBuffer targetFlStr, String format){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(placeholders);
        }
        String date = DateUtils.formatDate(new Date(), format);
        targetFlStr.replace(start, start+length, sb.substring(date.length()) + date);
    }
}

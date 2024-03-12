package com.ruoyi.common.utils.mock;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.mock.commonUtils.AmountUtils;
import com.ruoyi.common.utils.mock.commonUtils.UUIDUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public class XmlMsgFuncUtils {

    public static final Logger logger = LoggerFactory.getLogger(XmlMsgFuncUtils.class);

    /**** 节点数值、节点属性修改函数 ****/
    /***
     * 给指定值：无论当前节点的值是什么，都修改成指定的值
     * 节点数值 替换函数
     */
    public void infoChangeTo(Element node, String str){
        node.setText(str);
    }

    /***
     * 取默认值：如果节点不存在或者为空，那么按指定信息进行赋值
     * 节点数值 替换函数
     */
    public void getDefaultValue(Element node, String str){
        if(null == node.getText() || "".equals(node.getText())){
            node.setText(str);
        }
    }

    /***
     * 节点数值 金额设置千分位
     */
    public void toWestNumFormat(Element node){
        String amount = AmountUtils.toWestNumFormat(node.getText());
        node.setText(amount);
    }

    /***
     * 节点数值 金额设置万分位
     */
    public void toEastNumFormat(Element node){
        String amount = AmountUtils.toEastNumFormat(node.getText());
        node.setText(amount);
    }

    /***
     * 节点数值 金额去除格式化
     */
    public void removeFormat(Element node){
        try{
            String amount = AmountUtils.removeFormat(node.getText());
            node.setText(amount);
        }catch (ParseException p){
            logger.info("==============金额去除格式化 操作执行失败 :" + p.toString());
        }
    }

    /***
     * 节点数值 金额圆转分
     */
    public void changeY2F(Element node){
        String amount = AmountUtils.changeY2F(node.getText()).toString();
        node.setText(amount);
    }

    /***
     * 节点数值 金额分转圆
     */
    public void changeF2Y(Element node){
        String amount = AmountUtils.changeF2Y(node.getText()).toString();
        node.setText(amount);
    }

    /***
     * 节点数值 年增加
     */
    public void addYears(Element node, String year, String format){
        String dateTime = node.getText();
        Date date = DateUtils.addYear(DateUtils.parseDate(dateTime), Integer.parseInt(year));
        node.setText(DateUtils.formatDate(date, format));
    }

    /***
     * 节点数值 月增加
     */
    public void addMouths(Element node, String mouth, String format){
        String dateTime = node.getText();
        Date date = DateUtils.addMonth(DateUtils.parseDate(dateTime), Integer.parseInt(mouth));
        node.setText(DateUtils.formatDate(date, format));
    }

    /***
     * 节点数值 周增加
     */
    public void addWeeks(Element node, String week, String format){
        String dateTime = node.getText();
        Date date = DateUtils.addDay(DateUtils.parseDate(dateTime), Integer.parseInt(week)*7);
        node.setText(DateUtils.formatDate(date, format));
    }

    /***
     * 节点数值 日增加
     */
    public void addDays(Element node, String day, String format){
        String dateTime = node.getText();
        Date date = DateUtils.addDay(DateUtils.parseDate(dateTime), Integer.parseInt(day));
        node.setText(DateUtils.formatDate(date, format));
    }
    /***
     * 节点数值 时增加
     */
    public void addHours(Element node, String hour, String format){
        String dateTime = node.getText();
        Date date = DateUtils.addHour(DateUtils.parseDate(dateTime), Integer.parseInt(hour));
        node.setText(DateUtils.formatDate(date, format));
    }
    /***
     * 节点数值 分增加
     */
    public void addMins(Element node, String min, String format){
        String dateTime = node.getText();
        Date date = DateUtils.addMinute(DateUtils.parseDate(dateTime), Integer.parseInt(min));
        node.setText(DateUtils.formatDate(date, format));
    }
//    /***
//     * 节点数值 秒增加
//     */
//    public void addSecs(Element node, String sec, String format){
//
//    }

    /***
     * 按照格式要求返回当前日期时间
     * @param format
     */
    public void getDateTime(Element node, String format){
        node.setText(DateUtils.formatDate(new Date(), format));
    }

    /**
     * 返回指定位数token字符串
     * @param node
     * @param length
     */
    public void getToken(Element node, String length) {
        node.setText(UUIDUtils.getRandomStrNum(length));
    }

    public void testStrAdd(Element node, String a, String b, String c, String d, String e){
        node.setText(a+b+c+d+e);
    }
}
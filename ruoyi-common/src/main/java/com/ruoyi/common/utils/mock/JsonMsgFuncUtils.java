package com.ruoyi.common.utils.mock;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.ruoyi.common.utils.mock.commonUtils.AmountUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/***
 * 保证每个函数都要待key参数
 */
public class JsonMsgFuncUtils {
    public static final Logger logger = LoggerFactory.getLogger(JsonMsgFuncUtils.class);

    /**** 节点数值、节点属性修改函数 ****/
    /***
     * 给指定值：无论当前节点的值是什么，都修改成指定的值
     * 节点数值 替换函数
     */
    public void infoChangeTo(JSONObject rootObj, String path, String value){
        JSONPath.set(rootObj, path, value);
    }

    /***
     * 取默认值：如果节点不存在或者为空，那么按指定信息进行赋值
     * 节点数值 替换函数
     */
    public void getDefaultValue(JSONObject rootObj, String path, String defaultValue){
        Object value = JSONPath.read(rootObj.toString(), path);
        if(value instanceof String){
            if("".equals(value)){
                JSONPath.set(rootObj, path, defaultValue);
            }
        }else if(null == value){
            JSONPath.set(rootObj, path, defaultValue);
        }
    }

    /***
     * 节点数值 金额设置千分位
     */
    public void toWestNumFormat(JSONObject rootObj, String path){
        Object value = JSONPath.read(rootObj.toString(), path);
        if(value instanceof String){
            if(null != value || !"".equals(value)){
                String amount = AmountUtils.toWestNumFormat((String) value);
                JSONPath.set(rootObj, path, amount);
            }
        }
    }

    /***
     * 节点数值 金额设置万分位
     */
    public void toEastNumFormat(JSONObject rootObj, String path){
        Object value = JSONPath.read(rootObj.toString(), path);
        if(value instanceof String){
            if(null != value || !"".equals(value)){
                String amount = AmountUtils.toEastNumFormat((String) value);
                JSONPath.set(rootObj, path, amount);
            }
        }
    }
}

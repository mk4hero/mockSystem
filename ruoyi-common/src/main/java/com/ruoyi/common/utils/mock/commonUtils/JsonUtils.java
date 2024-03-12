package com.ruoyi.common.utils.mock.commonUtils;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class JsonUtils {
    public static String format(JSONObject unformattedJsonObj) {
        return JSON.toJSONString(unformattedJsonObj, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteDateUseDateFormat);
    }
}

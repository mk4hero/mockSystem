package com.ruoyi.system.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.alibaba.fastjson.parser.Feature;
import com.ruoyi.common.enums.MsgType;
import com.ruoyi.common.exception.GlobalException;
import com.ruoyi.system.service.http.IProcessService;
import com.ruoyi.common.utils.mock.BeanFactoryUtil;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;

@Service
public class EsbMsgService {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public String doEBKProcess(HttpServletRequest request) throws GlobalException, IOException {
        StringBuffer reqMsg = new StringBuffer();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"))) {
            String buffer;
            while ((buffer = reader.readLine()) != null) {
                reqMsg.append(buffer);
            }
        }
        logger.info("请求报文：" + reqMsg);

        String msgType = determineMessageType(reqMsg);
        logger.info("当前报文为" + msgType + "格式");

        String svcId = extractServiceId(reqMsg, msgType);
        logger.info("获取到ebank请求服务ID[" + svcId + "], 寻找服务");

        IProcessService processServer = null;
        Object object = new Object();
        try {
            processServer = (IProcessService) BeanFactoryUtil.getIntance().getBean(svcId);
            object = processServer.doProcess(reqMsg, msgType);
        } catch (NoSuchBeanDefinitionException e) {
            logger.error("服务[" + svcId + "]未被注册", e);
        } catch (GlobalException e) {
            logger.error("服务[" + svcId + "]业务处理失败", e);
        }

        return (String) object;
    }

    private String determineMessageType(StringBuffer reqMsg) {
        if (reqMsg == null || reqMsg.length() == 0) {
            return MsgType.UNKNOWN.getValue();
        }

        char firstChar = reqMsg.toString().trim().charAt(0);
        if (firstChar == '<') {
            return MsgType.XML.getValue();
        } else if (firstChar == '{') {
            return MsgType.JSON.getValue();
        } else {
            return MsgType.FLSTR.getValue();
        }
    }

    private String extractServiceId(StringBuffer reqMsg, String msgType) {
        MsgType type = MsgType.getKey(msgType);

        switch (type) {
            case XML:
                try {
                    org.dom4j.Document document = DocumentHelper.parseText(reqMsg.toString());
                    Element root = document.getRootElement();
                    Element head = root.element("head");
                    Element trncod = head.element("trncod");
                    return trncod.getStringValue();
                } catch (DocumentException e) {
                    logger.error(e.getMessage(), e);
                    return null;
                }
            case JSON:
                String json = reqMsg.toString().trim();
                LinkedHashMap<String, Object> jsonLinkedHashMap = JSON.parseObject(json, LinkedHashMap.class, Feature.OrderedField);
                JSONObject jsonObject = new JSONObject(true);
                jsonObject.putAll(jsonLinkedHashMap);
                ArrayList busiCodeArray = (ArrayList) JSONPath.read(jsonObject.toString(), "$..busiCode");
                return (String) busiCodeArray.get(0);
            case FLSTR:
                return Integer.valueOf(reqMsg.substring(0, 6).trim()).toString();
            default:
                return null;
        }
    }
}

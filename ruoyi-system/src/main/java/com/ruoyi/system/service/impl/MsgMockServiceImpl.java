package com.ruoyi.system.service.impl;

import com.alibaba.fastjson.*;
import com.alibaba.fastjson.parser.Feature;
import com.ruoyi.common.core.domain.TreeSelect;
import com.ruoyi.common.enums.MsgType;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.mock.FlStrMsgFuncUtils;
import com.ruoyi.common.utils.mock.JsonMsgFuncUtils;
import com.ruoyi.common.utils.mock.XmlMsgFuncUtils;
import com.ruoyi.common.utils.mock.commonUtils.JsonUtils;
import com.ruoyi.common.utils.mock.commonUtils.UUIDUtils;
import com.ruoyi.common.utils.mock.commonUtils.XmlUtils;
import com.ruoyi.system.domain.mock.*;
import com.ruoyi.system.mapper.mock.MocksysMappingInfoDAO;
import com.ruoyi.system.mapper.mock.MocksysMessagesInfoMapper;
import com.ruoyi.system.mapper.mock.MocksysTemplateNodeInfoDAO;
import com.ruoyi.system.service.IMsgMockService;
import org.dom4j.*;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.tree.BaseElement;
import org.dom4j.tree.DefaultDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.DOMImplementation;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

@Service
public class MsgMockServiceImpl implements IMsgMockService {

    public static final Logger logger = LoggerFactory.getLogger(MsgMockServiceImpl.class);
    private final static String XML_STR = "xml";
    private final static String JSON_STR = "json";
    private final static String FLSTR_STR = "flStr";

    @Autowired
    private MocksysTemplateNodeInfoDAO mocksysTemplateNodeInfoDAO;

    @Autowired
    private MocksysMessagesInfoMapper mocksysMessagesInfoMapper;

    @Autowired
    private MocksysMappingInfoDAO mocksysMappingInfoDAO;

    private HashMap<String , Integer> pathMap = new HashMap<>();

    int msgNnumber = 0;

    /**
     * 原报文是xml类型的处理函数
     * @param reqMsg
     * @param busiCode
     * @return
     */
    @Override
    public String serialNetDoXml(StringBuffer reqMsg, String busiCode) {
        logger.info("MsgMockServiceImpl-----接收xml报文为：" + XmlUtils.format(reqMsg.toString()));
        String responseMsgStr = "";

        try {
            /** 解析XML报文，获取xml报文最外层元素对象 */
            Document document = DocumentHelper.parseText(reqMsg.toString());
            Element sourceRoot = document.getRootElement();

            /** 工具函数，将新的xml序列化到数据库备用 自用函数*/
//            msgNnumber = 0;
//            getXmlNodes(busiCode, channel, root, "", 0);
//            pathMap = new HashMap<>();

            /** 请求报文落表 */
            saveRequestToDatabase(XmlUtils.format(reqMsg.toString()), XML_STR, busiCode);

            /** 第一步，获取应映射规则 */
            List<MocksysMappingInfo> mappingInfoList = getMappingInfoList(XML_STR, busiCode);

            /** 第二步，获取目标报文模板 */
            if( null != mappingInfoList && mappingInfoList.size() > 0){
                MocksysMappingInfo firstMappingInfo = mappingInfoList.get(0);
                String targetBusiCode = firstMappingInfo.getTargetBusiCode();
                String targetMsgType = firstMappingInfo.getTargetMsgType();

                MocksysTemplateNodeInfoExample nodeInfoExample = new MocksysTemplateNodeInfoExample();
                MocksysTemplateNodeInfoExample.Criteria nodeInfoCriteria = nodeInfoExample.createCriteria();
                nodeInfoCriteria.andBusiCodeEqualTo(targetBusiCode);
                nodeInfoCriteria.andMsgTypeEqualTo(targetMsgType);
                nodeInfoExample.setOrderByClause("id");
                List<MocksysTemplateNodeInfo> nodeInfoList = mocksysTemplateNodeInfoDAO.selectByExample(nodeInfoExample);

                if(null != nodeInfoList && nodeInfoList.size() > 0){
                    if(XML_STR.equals(targetMsgType)){
                        Document targetXml = createXmlMessage(nodeInfoList);
                        Element targetRoot = targetXml.getRootElement();
                        logger.info("生成模板报文为：" + XmlUtils.format(targetXml.asXML()));

                        manipulateXmlMessage(sourceRoot, targetRoot, mappingInfoList);
                        responseMsgStr = XmlUtils.format(targetXml.asXML());
                        logger.info("返回xml报文：" + responseMsgStr);
                    } else if (JSON_STR.equals(targetMsgType)) {
                        JSONObject targetJsonObj = createJsonMessage(nodeInfoList);
                        logger.info("生成模板报文为：" + JsonUtils.format(targetJsonObj));

                        manipulateJsonMessage(sourceRoot, targetJsonObj, mappingInfoList);
                        responseMsgStr = JsonUtils.format(targetJsonObj);
                        logger.info("返回json报文：" + responseMsgStr);
                    } else if (FLSTR_STR.equals(targetMsgType)) {
                        StringBuffer targetFlStr = createFlStrMessage(nodeInfoList);
                        logger.info("生成模板报文为：" + targetFlStr);

                        manipulateFlStrMessage(sourceRoot, targetFlStr, mappingInfoList);
                        responseMsgStr = targetFlStr.toString();
                        logger.info("返回json报文：" + responseMsgStr);
                    } else {
                        logger.error("报文类型判断错误，请检查！");
                    }
                } else {
                    logger.error("交易码：" + targetBusiCode + ", 报文类型：" + targetMsgType + "不存在当前类型报文模板！");
                }
            } else {
                logger.error("当前业务(业务号：" + busiCode + "; 报文类型：" + XML_STR + ")下没有配置映射规则");
            }
        } catch (DocumentException e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
        return responseMsgStr;
    }

    /**
     * 原报文时json类型是的处理函数
     * @param reqMsg
     * @param busiCode
     * @return
     */
    @Override
    public String serialNetDoJson(StringBuffer reqMsg, String busiCode) {
        String responseMsgStr = "";

        // 将请求消息转换为 JSON 对象并记录日志
        JSONObject sourceJsonObject = new JSONObject(true);
        try {
            String json = reqMsg.toString();
            LinkedHashMap<String, Object> jsonLinkedHashMap = JSON.parseObject(json, LinkedHashMap.class, Feature.OrderedField);
            sourceJsonObject.putAll(jsonLinkedHashMap);
            logger.info("接收到的 JSON 报文为：" + JsonUtils.format(sourceJsonObject));
        } catch (Exception e) {
            logger.error("解析 JSON 报文时出现错误：" + e.getMessage());
        }

        /** 工具函数，将新的json序列化到数据库备用 自用函数*/
//        String Xpath = new String("$");
//        msgNnumber = 0;
//        getJsonNodes(busiCode, channel, sourceJsonObject, Xpath, 0);

        /** 请求报文落表 */
        saveRequestToDatabase(JsonUtils.format(sourceJsonObject), JSON_STR, busiCode);

        /** 第一步，获取应映射规则 */
        List<MocksysMappingInfo> mappingInfoList = getMappingInfoList(JSON_STR, busiCode);

        /** 第二步，获取目标报文模板 */
        if( null != mappingInfoList && mappingInfoList.size() > 0){
            String targetBusiCode = mappingInfoList.get(0).getTargetBusiCode();
            String targetMsgType = mappingInfoList.get(0).getTargetMsgType();

            MocksysTemplateNodeInfoExample nodeInfoExample = new MocksysTemplateNodeInfoExample();
            MocksysTemplateNodeInfoExample.Criteria nodeInfoCriteria = nodeInfoExample.createCriteria();
            nodeInfoCriteria.andBusiCodeEqualTo(targetBusiCode);
            nodeInfoCriteria.andMsgTypeEqualTo(targetMsgType);
            nodeInfoExample.setOrderByClause("id");
            List<MocksysTemplateNodeInfo> nodeInfoList = mocksysTemplateNodeInfoDAO.selectByExample(nodeInfoExample);

            if(null != nodeInfoList && nodeInfoList.size() > 0){
                if(XML_STR.equals(targetMsgType)){
                    Document targetXml = createXmlMessage(nodeInfoList);
                    Element targetRoot = targetXml.getRootElement();
                    logger.info("生成模板报文为：" + XmlUtils.format(targetXml.asXML()));

                    manipulateXmlMessage(sourceJsonObject, targetRoot, mappingInfoList);
                    responseMsgStr = XmlUtils.format(targetXml.asXML());
                    logger.info("返回xml报文：" + responseMsgStr);
                } else if (JSON_STR.equals(targetMsgType)) {
                    JSONObject targetJsonObj = createJsonMessage(nodeInfoList);
                    logger.info("生成模板报文为：" + JsonUtils.format(targetJsonObj));

                    manipulateJsonMessage(sourceJsonObject, targetJsonObj, mappingInfoList);
                    responseMsgStr = JsonUtils.format(targetJsonObj);
                    logger.info("返回json报文：" + responseMsgStr);
                } else if (FLSTR_STR.equals(targetMsgType)) {
                    StringBuffer targetFlStr = createFlStrMessage(nodeInfoList);
                    logger.info("生成模板报文为：" + targetFlStr);

                    manipulateFlStrMessage(sourceJsonObject, targetFlStr, mappingInfoList);
                    responseMsgStr = targetFlStr.toString();
                    logger.info("返回json报文：" + responseMsgStr);
                } else {
                    logger.error("报文类型判断错误，请检查！");
                }
            } else {
                logger.error("交易码：" + targetBusiCode + ", 报文类型：" + targetMsgType + "不存在当前类型报文模板！");
            }
        }else{
            logger.error("当前业务(业务号：" + busiCode + ";报文类型：" + XML_STR + ")下没有配置映射规则");
        }
        return responseMsgStr;
    }

    /**
     * 原报文为定长字符串是的处理函数
     * @param sourceFlStr
     * @param busiCode
     * @return
     */
    @Override
    public String serialNetDoFlStr(StringBuffer sourceFlStr, String busiCode) {
        String responseMsgStr = "";
        logger.info("接收定长报文为：" + sourceFlStr);
        logger.info("接收定长报文长度为：" + sourceFlStr.length());

        /** 请求报文落表 */
        saveRequestToDatabase(String.valueOf(sourceFlStr), FLSTR_STR, busiCode);

        /** 第一步，获取应映射规则 */
        List<MocksysMappingInfo> mappingInfoList = getMappingInfoList(JSON_STR, busiCode);

        /** 第二步，获取目标报文模板 */
        if( null != mappingInfoList && mappingInfoList.size() > 0){
            String targetBusiCode = mappingInfoList.get(0).getTargetBusiCode();
            String targetMsgType = mappingInfoList.get(0).getTargetMsgType();

            MocksysTemplateNodeInfoExample nodeInfoExample = new MocksysTemplateNodeInfoExample();
            MocksysTemplateNodeInfoExample.Criteria nodeInfoCriteria = nodeInfoExample.createCriteria();
            nodeInfoCriteria.andBusiCodeEqualTo(targetBusiCode);
            nodeInfoCriteria.andMsgTypeEqualTo(targetMsgType);
            nodeInfoExample.setOrderByClause("id");
            List<MocksysTemplateNodeInfo> nodeInfoList = mocksysTemplateNodeInfoDAO.selectByExample(nodeInfoExample);

            if(null != nodeInfoList && nodeInfoList.size() > 0){
                if(XML_STR.equals(targetMsgType)){
                    Document targetXml = createXmlMessage(nodeInfoList);
                    Element targetRoot = targetXml.getRootElement();
                    logger.info("生成模板报文为：" + XmlUtils.format(targetXml.asXML()));

                    manipulateXmlMessage(String.valueOf(sourceFlStr), targetRoot, mappingInfoList);
                    responseMsgStr = XmlUtils.format(targetXml.asXML());
                    logger.info("返回xml报文：" + responseMsgStr);
                } else if (JSON_STR.equals(targetMsgType)) {
                    JSONObject targetJsonObj = createJsonMessage(nodeInfoList);
                    logger.info("生成模板报文为：" + JsonUtils.format(targetJsonObj));

                    manipulateJsonMessage(String.valueOf(sourceFlStr), targetJsonObj, mappingInfoList);
                    responseMsgStr = JsonUtils.format(targetJsonObj);
                    logger.info("返回json报文：" + responseMsgStr);
                } else if (FLSTR_STR.equals(targetMsgType)) {
                    StringBuffer targetFlStr = createFlStrMessage(nodeInfoList);
                    logger.info("生成模板报文为：" + targetFlStr);

                    manipulateFlStrMessage(String.valueOf(sourceFlStr), targetFlStr, mappingInfoList);
                    responseMsgStr = targetFlStr.toString();
                    logger.info("返回json报文：" + responseMsgStr);
                } else {
                    logger.error("报文类型判断错误，请检查！");
                }
            } else {
                logger.error("交易码：" + targetBusiCode + ", 报文类型：" + targetMsgType + "不存在当前类型报文模板！");
            }
        }else{
            logger.error("当前业务(业务号：" + busiCode + ";报文类型：" + XML_STR + ")下没有配置映射规则");
        }
        return responseMsgStr;
    }

    /**
     * 请求报文信息落表
     * @param reqMsg
     * @param msgType
     * @param busiCode
     */
    private void saveRequestToDatabase(String reqMsg, String msgType, String busiCode) {
        try {
            MocksysMessagesInfo msgInfoDto = new MocksysMessagesInfo();
            msgInfoDto.setId(UUIDUtils.get32UUID());
            msgInfoDto.setMsgType(msgType);
            msgInfoDto.setReqMsg(reqMsg);
            msgInfoDto.setReqDateTime(DateUtils.getDateTime());
            msgInfoDto.setBusiCode(busiCode);
            int id = mocksysMessagesInfoMapper.insertMocksysMessagesInfo(msgInfoDto);
            if (id == -1) {
                logger.info("报文落表失败");
            }
        } catch (Exception e) {
            logger.error("报文落表失败：" + e.getMessage(), e);
        }
    }

    /**
     * 查询映射规则表，获取映射规则
     * @param msgType
     * @param busiCode
     * @return
     */
    private List<MocksysMappingInfo> getMappingInfoList(String msgType, String busiCode) {
        MocksysMappingInfoExample example = new MocksysMappingInfoExample();
        MocksysMappingInfoExample.Criteria criteria = example.createCriteria();
        criteria.andSourceBusiCodeEqualTo(busiCode);
        criteria.andSourceMsgTypeEqualTo(msgType);
        return  mocksysMappingInfoDAO.selectByExample(example);
    }

    /**
     * 通过反射机制，调用定长字符串类型报文 节点值 处理函数
     * @param operate
     * @param parameterJsonMap
     * @param targetLocation
     * @param targetLength
     * @param targetValue
     * @param placeholders
     * @param targetFlStr
     */
    private void flStrMsgDoFunction(String operate, LinkedHashMap<String, String> parameterJsonMap, Integer targetLocation, Integer targetLength, String targetValue, String placeholders, StringBuffer targetFlStr) {
        if(null != operate && !"".equals(operate)) {
            Object[] parameters = new Object[parameterJsonMap.size() + 5];
            Class[] parameterTypes = new Class[parameterJsonMap.size() + 5];
            parameters[0] = targetLocation;
            parameterTypes[0] = int.class;
            parameters[1] = targetLength;
            parameterTypes[1] = int.class;
            parameters[2] = targetValue;
            parameterTypes[2] = String.class;
            parameters[3] = placeholders;
            parameterTypes[3] = String.class;
            parameters[4] = targetFlStr;
            parameterTypes[4] = StringBuffer.class;
            if (parameterJsonMap.size() > 0) {
                int i = 5;
                for (String pValue : parameterJsonMap.values()) {
                    parameters[i] = pValue;
                    parameterTypes[i] = String.class;
                    i++;
                }
            }
            try {
                FlStrMsgFuncUtils flStrMsgFuncUtils = new FlStrMsgFuncUtils();
                Class<? extends FlStrMsgFuncUtils> aClass = flStrMsgFuncUtils.getClass();
                Method m = aClass.getMethod(operate, parameterTypes);
                m.invoke(flStrMsgFuncUtils, parameters);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 通过反射机制，调用json类型报文 节点值 处理函数
     * @param operate
     * @param parameterJsonMap
     * @param targetJsonObj
     * @param targetPath
     */
    private void jsonNodeDoFunction(String operate, LinkedHashMap<String, String> parameterJsonMap, Object targetJsonObj, String targetPath) {
        if(null != operate && !"".equals(operate)) {
            Object[] parameters = new Object[parameterJsonMap.size() + 2];
            Class[] parameterTypes = new Class[parameterJsonMap.size() + 2];
            parameters[0] = targetJsonObj;
            parameterTypes[0] = JSONObject.class;
            parameters[1] = targetPath;
            parameterTypes[1] = String.class;
            if (parameterJsonMap.size() > 0) {
                int i = 2;
                for (String parameterValue : parameterJsonMap.values()) {
                    parameters[i] = parameterValue;
                    parameterTypes[i] = String.class;
                    i++;
                }
            }
            try {
                JsonMsgFuncUtils jsonMsgFuncUtils = new JsonMsgFuncUtils();
                Class<? extends JsonMsgFuncUtils> aClass = jsonMsgFuncUtils.getClass();
                Method m = aClass.getMethod(operate, parameterTypes);
                m.invoke(jsonMsgFuncUtils, parameters);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }else {
            logger.info("未查询到待操作信息！");
        }
    }

    /**
     * 通过反射机制，调用xml类型报文 节点值 处理函数
     * @param operate
     * @param parameterJsonMap
     * @param targetElement
     */
    private void xmlNodeDoFunction(String operate, LinkedHashMap<String, String> parameterJsonMap, Element targetElement) {
        if(null != operate && !"".equals(operate)){
            Object[] parameters = new Object[parameterJsonMap.size() + 1];
            Class[] parameterTypes = new Class[parameterJsonMap.size() + 1];
            parameters[0] = targetElement;
            parameterTypes[0] = Element.class;
            if(parameterJsonMap.size() > 0){
                int i = 1;
                for(String parameterValue : parameterJsonMap.values()){
                    parameters[i] = parameterValue;
                    parameterTypes[i] = String.class;
                    i++;
                }
            }
            try{
                XmlMsgFuncUtils xmlMsgFuncUtils = new XmlMsgFuncUtils();
                Class<? extends XmlMsgFuncUtils> aClass = xmlMsgFuncUtils.getClass();
                Method m = aClass.getMethod(operate, parameterTypes);
                m.invoke(xmlMsgFuncUtils, parameters);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }else {
            logger.info("未查询到待操作信息！");
        }
    }

    /**
     * 目标报文模板为xml，按照映射规则，组装处理目标报文
     * @param sourceData
     * @param targetRoot
     * @param mappingInfoList
     */
    private void manipulateXmlMessage(Object sourceData, Element targetRoot, List<MocksysMappingInfo> mappingInfoList) {
        for (MocksysMappingInfo rule : mappingInfoList) {
            String sourcePath = rule.getSourcePath();
            String sourceSign = rule.getSourceSign();
            String targetPath = rule.getTargetPath();
            String targetSign = rule.getTargetSign();
            String action = rule.getAction();
            String operate = rule.getOperate();
            String parameter = rule.getParameter();

            if (null != action && !"".equals(action)) {
                switch (action) {
                    // 01字段值映射
                    case "01": {
                        LinkedHashMap<String, String> parameterJsonMap = new LinkedHashMap<>();
                        if (null != parameter) {
                            JSONObject jsonObject = JSONObject.parseObject(parameter, Feature.OrderedField);
                            parameterJsonMap = JSON.parseObject(jsonObject.toString(), new TypeReference<LinkedHashMap<String, String>>() {
                            });//关键所在，转化为有序的
                        }

                        /** 判断原报文类型，按照不同类型进行对应处理，取出所需字段值，
                         *  再放到目标报文模板中，
                         *  最后按照处理函数对目标报文进行处理 */
                        List<Element> targetElementList = targetRoot.selectNodes(targetPath);
                        Element targetElement = targetElementList.get(Integer.valueOf(targetSign));

                        if (sourceData instanceof Element) {
                            logger.info("原报文 是 xml 类型");
                            Element sourceRoot = (Element) sourceData;
                            List<Element> valueElementList = sourceRoot.selectNodes(sourcePath);
                            Element valueElement = valueElementList.get(Integer.valueOf(sourceSign));
                            String value = valueElement.getText();
                            targetElement.setText(value);
                        } else if (sourceData instanceof JSONObject) {
                            logger.info("原报文 是 json 类型");
                            JSONObject sourceJsonObject = (JSONObject) sourceData;
                            String value = (String) JSONPath.read(sourceJsonObject.toString(), sourcePath);

                            targetElement.setText(value);
                        } else if (sourceData instanceof String){
                            logger.info("原报文 是 定长字符串 类型");

                            Integer sourceLocation = rule.getSourceLocation();
                            Integer sourceLength = rule.getSourceLength();
                            Integer sourceLoopCount = rule.getSourceLoopCount();
                            Integer sourceLoopLength = rule.getSourceLoopLength();

                            /**todo 应当考虑定长字符串循环字段存在时的处理方式*/

                            // 一般视定长字符串每个字段向左补齐
                            String sourceFlStr = (String) sourceData;
                            String value = sourceFlStr.substring(sourceLocation, sourceLocation + sourceLength);

                            /**原定长字符串字段对齐方式*/
                            String sourceAlignment = parameterJsonMap.get("sourceAlignment");
                            parameterJsonMap.remove("sourceAlignment");
                            /**原报文定长字符串补位用特殊字符*/
                            String sourcePlaceholders = parameterJsonMap.get("sourcePlaceholders");
                            parameterJsonMap.remove("sourcePlaceholders");
                            for(int i=0;i < value.length();i++) {
                                if(!sourcePlaceholders.equals(value.charAt(i))){
                                    value = value.substring(i);
                                    break;
                                }
                            }
                            targetElement.setText(value);
                        }
                        xmlNodeDoFunction(operate, parameterJsonMap, targetElement);
                    }
                    break;
                    case "02": {
                        List<Element> targetElementList = targetRoot.selectNodes(targetPath);
                        Element targetElement = targetElementList.get(Integer.valueOf(targetSign));

                        /** 原报类型判断 */
                        if (sourceData instanceof Element) {
                            logger.info("原报文 是 xml 类型");
                            Element sourceRoot = (Element) sourceData;
                            List<Element> valueElementList = sourceRoot.selectNodes(sourcePath);
                            Element valueElement = valueElementList.get(Integer.valueOf(sourceSign));
                            String value = valueElement.getText();
                            targetElement.setName(parameter);
                            targetElement.setText(value);
                        } else if (sourceData instanceof JSONObject) {
                            logger.info("原报文 是 json 类型");
                        } else if (sourceData instanceof String){
                            logger.info("原报文 是 定长字符串 类型");
                        }
                    }
                    break;
                    case "03": {
                        LinkedHashMap<String, String> parameterJsonMap = new LinkedHashMap<>();
                        if (null != parameter) {
                            JSONObject jsonObject = JSONObject.parseObject(parameter, Feature.OrderedField);
                            parameterJsonMap = JSON.parseObject(jsonObject.toString(), new TypeReference<LinkedHashMap<String, String>>() {
                            });//关键所在，转化为有序的
                        }

                        String attributeName = parameterJsonMap.get("attrbuiteName");
                        String isNewElement = parameterJsonMap.get("isNewElement");
                        String newElementName = parameterJsonMap.get("newElementName");
                        parameterJsonMap.remove("attrbuiteName");
                        parameterJsonMap.remove("isNewElement");
                        parameterJsonMap.remove("newElementName");

                        /** 原报类型判断 */
                        if (sourceData instanceof Element) {
                            logger.info("原报文 是 xml 类型");
                            Element sourceRoot = (Element) sourceData;
                            List<Element> valueElementList = sourceRoot.selectNodes(sourcePath);
                            Element valueElement = valueElementList.get(Integer.valueOf(sourceSign));
                            Attribute sourceAttribute = valueElement.attribute(attributeName);

                            if ("0".equals(isNewElement)) {
                                // 不是新节点，是 映射到现有节点
                                List<Element> targetElementList = targetRoot.selectNodes(targetPath);
                                Element targetElement = targetElementList.get(Integer.valueOf(targetSign));
                                targetElement.setText(sourceAttribute.getValue());

                                xmlNodeDoFunction(operate, parameterJsonMap, targetElement);
                            } else if ("1".equals(isNewElement)) {
                                // 是新节点
                                List<Element> targetFatherElementList = targetRoot.selectNodes(targetPath);
                                Element targetFatherElement = targetFatherElementList.get(Integer.valueOf(targetSign));
                                Element targetElement = targetFatherElement.addElement(newElementName);
                                targetElement.setText(sourceAttribute.getValue());

                                xmlNodeDoFunction(operate, parameterJsonMap, targetElement);
                            }
                        } else if (sourceData instanceof JSONObject) {
                            logger.info("原报文 是 json 类型");
                        } else if (sourceData instanceof String){
                            logger.info("原报文 是 定长字符串 类型");
                        }
                    }
                    break;
                    case "04": {
                        LinkedHashMap<String, String> parameterJsonMap = new LinkedHashMap<>();
                        if (null != parameter) {
                            JSONObject jsonObject = JSONObject.parseObject(parameter, Feature.OrderedField);
                            parameterJsonMap = JSON.parseObject(jsonObject.toString(), new TypeReference<LinkedHashMap<String, String>>() {
                            });//关键所在，转化为有序的
                        }

                        String newElementName = parameterJsonMap.get("newElementName");
                        String newElementValue = parameterJsonMap.get("newElementValue");
                        String isNewValue = parameterJsonMap.get("isNewValue");
                        parameterJsonMap.remove("newElementName");
                        parameterJsonMap.remove("newElementValue");
                        parameterJsonMap.remove("isNewValue");

                        if ("0".equals(isNewValue)) {
                            // 从原报文节点中获取value

                            /** 原报类型判断 */
                            if (sourceData instanceof Element) {
                                logger.info("原报文 是 xml 类型");
                                Element sourceRoot = (Element) sourceData;
                                List<Element> valueElementList = sourceRoot.selectNodes(sourcePath);
                                Element valueElement = valueElementList.get(Integer.valueOf(sourceSign));
                                newElementValue = valueElement.getText();
                            } else if (sourceData instanceof JSONObject) {
                                logger.info("原报文 是 json 类型");
                            } else if (sourceData instanceof String){
                                logger.info("原报文 是 定长字符串 类型");
                            }
                        }

                        // 使用新的value
                        List<Element> targetFatherElementList = targetRoot.selectNodes(targetPath);
                        Element targetFatherElement = targetFatherElementList.get(Integer.valueOf(targetSign));
                        Element targetElement = targetFatherElement.addElement(newElementName);
                        targetElement.setText(newElementValue);

                        xmlNodeDoFunction(operate, parameterJsonMap, targetElement);
                    }
                    break;
                }
            }
        }
    }

    /**
     * 目标报文模板为json，按照映射规则，组装处理目标报文
     * @param sourceData
     * @param targetJsonObj
     * @param mappingInfoList
     */
    private void manipulateJsonMessage(Object sourceData, JSONObject targetJsonObj, List<MocksysMappingInfo> mappingInfoList){
        for(MocksysMappingInfo rule : mappingInfoList){
            String sourcePath = rule.getSourcePath();
            String sourceSign = rule.getSourceSign();
            String targetPath = rule.getTargetPath();
            String action = rule.getAction();
            String operate = rule.getOperate();
            String parameter = rule.getParameter();

            if(null != action && !"".equals(action)) {
                switch (action) {
                    // 01字段值映射
                    case "01": {
                        LinkedHashMap<String, String> parameterJsonMap = new LinkedHashMap<>();
                        if(null != parameter){
                            JSONObject jsonObject = JSONObject.parseObject(parameter, Feature.OrderedField);
                            parameterJsonMap = JSON.parseObject(jsonObject.toString(), new TypeReference<LinkedHashMap<String, String>>(){});//关键所在，转化为有序的
                        }

                        /** 原报类型判断 */
                        if (sourceData instanceof Element) {
                            logger.info("原报文 是 xml 类型");
                            Element sourceRoot = (Element) sourceData;
                            List<Element> valueElementList = sourceRoot.selectNodes(sourcePath);
                            Element valueElement = valueElementList.get(Integer.valueOf(sourceSign));
                            String value = valueElement.getText();
                            JSONPath.set(targetJsonObj, targetPath, value);
                        } else if (sourceData instanceof JSONObject) {
                            logger.info("原报文 是 json 类型");
                            JSONObject sourceJsonObject = (JSONObject) sourceData;
                            String value = (String) JSONPath.read(sourceJsonObject.toString(), sourcePath);

                            JSONPath.set(targetJsonObj, targetPath, value);
                        } else if (sourceData instanceof String){
                            logger.info("原报文 是 定长字符串 类型");
                            Integer sourceLocation = rule.getSourceLocation();
                            Integer sourceLength = rule.getSourceLength();
                            Integer sourceLoopCount = rule.getSourceLoopCount();
                            Integer sourceLoopLength = rule.getSourceLoopLength();

                            // 一般视定长字符串每个字段向左补齐
                            String sourceFlStr = (String) sourceData;
                            String value = sourceFlStr.substring(sourceLocation, sourceLocation + sourceLength);

                            /**原定长字符串字段对齐方式*/
                            String sourceAlignment = parameterJsonMap.get("sourceAlignment");
                            parameterJsonMap.remove("sourceAlignment");
                            /**原报文定长字符串补位用特殊字符*/
                            String sourcePlaceholders = parameterJsonMap.get("sourcePlaceholders");
                            parameterJsonMap.remove("sourcePlaceholders");
                            for (int i = 0; i < value.length(); i++) {
                                if (!sourcePlaceholders.equals(value.charAt(i))) {
                                    value = value.substring(i);
                                }
                            }

                            JSONPath.set(targetJsonObj, targetPath, value);
                        }
                        jsonNodeDoFunction(operate, parameterJsonMap, targetJsonObj, targetPath);
                    } break;
                    /** 不同报文类型间不支持字段名修改 */
                    case "03": {
                        LinkedHashMap<String, String> parameterJsonMap = new LinkedHashMap<>();
                        if(null != parameter){
                            JSONObject jsonObject = JSONObject.parseObject(parameter, Feature.OrderedField);
                            parameterJsonMap = JSON.parseObject(jsonObject.toString(), new TypeReference<LinkedHashMap<String, String>>(){});//关键所在，转化为有序的
                        }

                        String attributeName = parameterJsonMap.get("attrbuiteName");
                        parameterJsonMap.remove("attrbuiteName");

                        /** 原报类型判断 */
                        if (sourceData instanceof Element) {
                            logger.info("原报文 是 xml 类型");
                            Element sourceRoot = (Element) sourceData;
                            List<Element> valueElementList = sourceRoot.selectNodes(sourcePath);
                            Element valueElement = valueElementList.get(Integer.valueOf(sourceSign));
                            Attribute sourceAttribute = valueElement.attribute(attributeName);
                            // 只支持映射到现有节点
                            JSONPath.set(targetJsonObj, targetPath, sourceAttribute.getValue());
                        } else if (sourceData instanceof JSONObject) {
                            logger.info("原报文 是 json 类型");
                        } else if (sourceData instanceof String){
                            logger.info("原报文 是 定长字符串 类型");
                        }

                        jsonNodeDoFunction(operate, parameterJsonMap, targetJsonObj, targetPath);
                        /** 不同报文类型，应该直接维护报文模板，做字段映射处理，不应该支持动态添加字段 */
                    } break;
                }
            }
        }
    }

    /**
     * 目标报文模板为定长字符串，按照映射规则，组装处理目标报文
     * @param sourceData
     * @param targetFlStr
     * @param mappingInfoList
     */
    private void manipulateFlStrMessage(Object sourceData, StringBuffer targetFlStr, List<MocksysMappingInfo> mappingInfoList){
        for(MocksysMappingInfo rule : mappingInfoList){
            String sourcePath = rule.getSourcePath();
            String sourceSign = rule.getSourceSign();
            Integer targetLocation = rule.getTargetLocation();
            Integer targetLength = rule.getTargetLength();
            Integer targetLoopCount = rule.getTargetLoopCount();
            Integer targetLoopLength = rule.getTargetLoopLength();
            String sign = rule.getTargetSign();
            String action = rule.getAction();
            String operate = rule.getOperate();
            String parameter = rule.getParameter();

            if (action == null || action.isEmpty()) {
                continue;
            }

            switch (action) {
                // 01字段值映射
                case "01": {
                    LinkedHashMap<String, String> parameterJsonMap = new LinkedHashMap<>();
                    if(null != parameter){
                        JSONObject jsonObject = JSONObject.parseObject(parameter, Feature.OrderedField);
                        parameterJsonMap = JSON.parseObject(jsonObject.toString(), new TypeReference<LinkedHashMap<String, String>>(){});//关键所在，转化为有序的
                    }

                    /** 目标定长字符串字段对齐方式 */
                    String targetAlignment = parameterJsonMap.get("targetAlignment");
                    parameterJsonMap.remove("targetAlignment");
                    /** 目标报文定长字符串补位用特殊字符 */
                    String targetPlaceholders = parameterJsonMap.get("targetPlaceholders");
                    parameterJsonMap.remove("targetPlaceholders");
//                        // 判断目标字段是否为循环字段
//                        if("0".equals(sign)){
//                            // 不是循环字段
//                            String targetStr = StringUtils.leftPad("1", 5, "0");
//                        }else{
//
//                        }
                    /** 原报类型判断 */
                    String targetValue = "";
                    /** 原报类型判断 */
                    if (sourceData instanceof Element) {
                        logger.info("原报文 是 xml 类型");
                        Element sourceRoot = (Element) sourceData;
                        List<Element> valueElementList = sourceRoot.selectNodes(sourcePath);
                        Element valueElement = valueElementList.get(Integer.valueOf(sourceSign));
                        targetValue = valueElement.getText();
                    } else if (sourceData instanceof JSONObject) {
                        logger.info("原报文 是 json 类型");
                        JSONObject sourceJsonObject = (JSONObject) sourceData;
                        targetValue = (String) JSONPath.read(sourceJsonObject.toString(), sourcePath);
                    } else if (sourceData instanceof String){
                        logger.info("原报文 是 定长字符串 类型");

                        Integer sourceLocation = rule.getSourceLocation();
                        Integer sourceLength = rule.getSourceLength();
                        Integer sourceLoopCount = rule.getSourceLoopCount();
                        Integer sourceLoopLength = rule.getSourceLoopLength();

                        String sourceFlStr = (String) sourceData;
                        targetValue = sourceFlStr.substring(sourceLocation, sourceLocation + sourceLength);

                        /**原定长字符串字段对齐方式*/
                        String sourceAlignment = parameterJsonMap.get("sourceAlignment");
                        parameterJsonMap.remove("sourceAlignment");
                        /**原报文定长字符串补位用特殊字符*/
                        String sourcePlaceholders = parameterJsonMap.get("sourcePlaceholders");
                        parameterJsonMap.remove("sourcePlaceholders");
                        for (int i = 0; i < targetValue.length(); i++) {
                            if(!sourcePlaceholders.equals(targetValue.charAt(i))){
                                targetValue = targetValue.substring(i);
                            }
                        }
                    }

                    flStrMsgDoFunction(operate, parameterJsonMap, targetLocation, targetLength, targetValue, targetPlaceholders, targetFlStr);
                } break;
                /** 不同报文类型间不支持字段名修改 */
                case "03": {
                    LinkedHashMap<String, String> parameterJsonMap = new LinkedHashMap<>();
                    if(null != parameter){
                        JSONObject jsonObject = JSONObject.parseObject(parameter, Feature.OrderedField);
                        parameterJsonMap = JSON.parseObject(jsonObject.toString(), new TypeReference<LinkedHashMap<String, String>>(){});//关键所在，转化为有序的
                    }

                    String attributeName = parameterJsonMap.get("attrbuiteName");
                    parameterJsonMap.remove("attrbuiteName");
                    String placeholders = parameterJsonMap.get("placeholders");
                    parameterJsonMap.remove("placeholders");

                    String sourceAttributeValue = "";

                    /** 原报类型判断 */
                    if (sourceData instanceof Element) {
                        logger.info("原报文 是 xml 类型");
                        Element sourceRoot = (Element) sourceData;
                        List<Element> valueElementList = sourceRoot.selectNodes(sourcePath);
                        Element valueElement = valueElementList.get(Integer.valueOf(sourceSign));
                        Attribute sourceAttribute = valueElement.attribute(attributeName);
                        sourceAttributeValue = sourceAttribute.getValue();
                    } else if (sourceData instanceof JSONObject) {
                        logger.info("原报文 是 json 类型");
                    } else if (sourceData instanceof String){
                        logger.info("原报文 是 定长字符串 类型");
                    }

                    // 只支持映射到现有节点
                    flStrMsgDoFunction(operate, parameterJsonMap, targetLocation, targetLength, sourceAttributeValue, placeholders, targetFlStr);
                    /** 不同报文类型，应该直接维护报文模板，做字段映射处理，不应该支持动态添加字段 */
                } break;
            }
        }
    }

    /**
     * 按照报文模板数据，生成xml报文对象
     * @param nodeInfoList
     * @return
     */
    public Document createXmlMessage (List < MocksysTemplateNodeInfo > nodeInfoList) {
        // 创建一个新的 XML 文档
        Document targetXml = DocumentHelper.createDocument();
        for (MocksysTemplateNodeInfo item : nodeInfoList) {
            if ("0".equals(item.getNumber())) {
                // 如果是根节点，则直接创建
                targetXml.addElement(item.getFieldName());
            } else {
                // 如果是子节点，则在父节点下创建
                Element root = targetXml.getRootElement();
                String path = item.getPath();
                // 获取父节点路径
                String parentPath = path.substring(0, path.lastIndexOf("/"));
                List<Element> parentElementList = root.selectNodes(parentPath);
                if (null != parentElementList && parentElementList.size() > 0) {
                    // 找到父节点，创建子节点
                    Element parentElement = parentElementList.get(Integer.valueOf(item.getSign()));
                    parentElement.addElement(item.getFieldName());
                } else {
                    // 未找到父节点，记录错误日志
                    logger.error(parentPath + "路径下未查到对应节点");
                }
            }
        }
        return targetXml;
    }

    /**
     * 按照报文模板数据，生成json报文对象
     * @param nodeInfoList
     * @return
     */
    public JSONObject createJsonMessage (List < MocksysTemplateNodeInfo > nodeInfoList) {
        // 创建根 JSON 对象
        JSONObject rootObj = new JSONObject();

        for (MocksysTemplateNodeInfo item : nodeInfoList) {
            // 如果当前节点不是子节点，即为对象或数组
            if ("0".equals(item.getIsSonNode())) {
                // 如果节点为数组元素
                if ("1".equals(item.getSign())) {
                    // 创建新的 JSON 数组
                    JSONArray newArray = new JSONArray();

                    // 获取路径信息
                    String path = item.getPath();
                    String parentPath = path.substring(0, path.lastIndexOf("."));

                    // 如果当前节点为根节点下的数组元素
                    if ("$".equals(parentPath)) {
                        rootObj.put(item.getFieldName(), newArray);
                    } else {
                        // 否则，找到父节点，并将新数组加入到父节点中
                        JSONObject parentObj = (JSONObject) JSONPath.read(rootObj.toString(), parentPath);
                        parentObj.put(item.getFieldName(), newArray);
                    }
                } else {
                    // 如果节点为对象元素
                    JSONObject newObj = new JSONObject();

                    // 获取路径信息
                    String path = item.getPath();
                    if (item.getFieldName().contains("[")) {
                        String parentPath = path.substring(0, path.lastIndexOf("["));

                        JSONArray parentObj = (JSONArray) JSONPath.read(rootObj.toString(), parentPath);
                        parentObj.add(newObj);
                    } else {
                        String parentPath = path.substring(0, path.lastIndexOf("."));

                        if ("$".equals(parentPath)) {
                            rootObj.put(item.getFieldName(), newObj);
                        } else {
                            JSONObject parentObj = (JSONObject) JSONPath.read(rootObj.toString(), parentPath);
                            parentObj.put(item.getFieldName(), newObj);
                        }
                    }
                }
            }
            // 如果当前节点是子节点，则代表当前元素为对象属性，直接添加属性
            else {
                // 获取路径信息
                String path = item.getPath();
                String parentPath = path.substring(0, path.lastIndexOf("."));

                // 找到父节点，并将属性加入到父节点中
                JSONObject parentObj = (JSONObject) JSONPath.read(rootObj.toString(), parentPath);
                parentObj.put(item.getFieldName(), "");
            }
        }

        return rootObj;
    }

    /**
     * 按照报文模板数据，生成定长字符串报文对象
     * @param nodeInfoList
     * @return
     */
    public StringBuffer createFlStrMessage (List < MocksysTemplateNodeInfo > nodeInfoList) {
        StringBuffer msg = new StringBuffer();
        for (MocksysTemplateNodeInfo item : nodeInfoList) {
            if ("1".equals(item.getSign())) {
                msg.append(StringUtils.repeat("0", (item.getLength() + item.getLoopCount() * item.getLoopLength())));
            } else {
                msg.append(StringUtils.repeat("0", item.getLength()));
            }
        }
        return msg;
    }

    /**
     * xml报文节点落表
     * @param busiType
     * @param channel
     * @param node
     * @param Xpath
     * @param parentId
     */
    public void getXmlNodes (String busiType, String channel, Element node, String Xpath,int parentId){
        Xpath = Xpath + "/" + node.getName();
        Integer no = pathMap.get(Xpath);
        if (null != no) {
            pathMap.remove(Xpath);
            no++;
        } else {
            no = 0;
        }

        logger.info("--------------------");

        //当前节点的名称、文本内容和属性
        logger.info("当前节点名称：" + node.getName());//当前节点名称
        logger.info("当前节点Xpath：" + Xpath);//当前节点名称
        logger.info("当前节点的内容：" + node.getTextTrim());//当前节点名称

        MocksysTemplateNodeInfo mocksysTemplateNodeInfo = new MocksysTemplateNodeInfo();
        mocksysTemplateNodeInfo.setBusiCode(busiType);// 交易码
        mocksysTemplateNodeInfo.setMsgType(XML_STR);// 报文类型
        mocksysTemplateNodeInfo.setFieldName(node.getName());// 节点名称
        mocksysTemplateNodeInfo.setPath(Xpath);// 路径
        mocksysTemplateNodeInfo.setSign(String.valueOf(no));// 区分标识
        mocksysTemplateNodeInfo.setChannel(channel);// 渠道
        mocksysTemplateNodeInfo.setParentId(parentId);
        mocksysTemplateNodeInfo.setNumber(String.valueOf(msgNnumber++));

        pathMap.put(Xpath, no);

        /** 打印属性的，切勿删除 */
//        List<Attribute> listAttr = node.attributes();//当前节点的所有属性的list
//        for(Attribute attr:listAttr){//遍历当前节点的所有属性
//            String name=attr.getName();//属性名称
//            String value=attr.getValue();//属性的值
//            logger.info("属性名称："+name+"属性值："+value);
//        }

        //递归遍历当前节点所有的子节点
        List<Element> listElement = node.elements();//所有一级子节点的list
        if (null != listElement && listElement.size() > 0) {
            mocksysTemplateNodeInfo.setIsSonNode(false);
            mocksysTemplateNodeInfoDAO.insertSelective(mocksysTemplateNodeInfo);
            for (Element e : listElement) {//遍历所有一级子节点
                parentId = mocksysTemplateNodeInfo.getId();
                this.getXmlNodes(busiType, channel, e, Xpath, parentId);//递归
            }
        } else {
            logger.info("当前节点为叶子节点");
            mocksysTemplateNodeInfo.setIsSonNode(true);
            mocksysTemplateNodeInfoDAO.insertSelective(mocksysTemplateNodeInfo);
        }
    }

    /**
     * json报文节点落表
     * @param busiCode
     * @param channel
     * @param obj
     * @param Xpath
     * @param parentId
     */
    public void getJsonNodes (String busiCode, String channel, JSONObject obj, String Xpath,int parentId){
        for (String key : obj.keySet()) {    // 最外层的key
            StringBuilder objPath = new StringBuilder(Xpath);
            /**处理路径**/
            logger.info("--------------------");
            objPath = objPath.append("." + key);
            //当前节点的名称、文本内容和属性
            logger.info("当前节点名称：" + key);//当前节点名称
            logger.info("当前节点Xpath：" + objPath.toString());//当前节点名称

            MocksysTemplateNodeInfo mocksysTemplateNodeInfo = new MocksysTemplateNodeInfo();
            mocksysTemplateNodeInfo.setBusiCode(busiCode);// 交易码
            mocksysTemplateNodeInfo.setMsgType(JSON_STR);// 报文类型
            mocksysTemplateNodeInfo.setFieldName(key);// 节点名称
            mocksysTemplateNodeInfo.setPath(objPath.toString());// 路径
            mocksysTemplateNodeInfo.setChannel(channel);// 渠道
            mocksysTemplateNodeInfo.setParentId(parentId);
            mocksysTemplateNodeInfo.setNumber(String.valueOf(msgNnumber++));

            Object value = obj.get(key);
            // 空字符串也是字符串
            if (value instanceof String || value instanceof Number) {
                logger.info("当前节点的内容 : " + value);
                mocksysTemplateNodeInfo.setIsSonNode(true);
                mocksysTemplateNodeInfoDAO.insert(mocksysTemplateNodeInfo);
                continue;
            }
            if (value instanceof JSONObject) {
                JSONObject object = (JSONObject) value;
                logger.info("=======》" + key + "(json对象)");
                mocksysTemplateNodeInfo.setIsSonNode(false);
                mocksysTemplateNodeInfoDAO.insert(mocksysTemplateNodeInfo);
                getJsonNodes(busiCode, channel, object, objPath.toString(), mocksysTemplateNodeInfo.getId());
                continue;
            }
            if (value instanceof JSONArray) {
                logger.info("=======》" + key + "(json数组)");
                if (value == null) {    //不会存在
                    logger.info("***************************************8");
                    mocksysTemplateNodeInfo.setIsSonNode(true);
                    mocksysTemplateNodeInfoDAO.insert(mocksysTemplateNodeInfo);
                }
                JSONArray jsonArray = (JSONArray) value;
                if (jsonArray.isEmpty()) {
                    logger.info(key + " : " + "[]");
                    mocksysTemplateNodeInfo.setIsSonNode(true);
                    mocksysTemplateNodeInfoDAO.insert(mocksysTemplateNodeInfo);
                } else {
                    mocksysTemplateNodeInfo.setIsSonNode(false);
                    mocksysTemplateNodeInfoDAO.insert(mocksysTemplateNodeInfo);
                    for (int i = 0; i < jsonArray.size(); i++) {
                        StringBuilder arryPath = new StringBuilder(objPath);
                        arryPath = arryPath.append("[" + i + "]");

                        /**处理路径**/
                        logger.info("--------------------");
                        //当前节点的名称、文本内容和属性
                        logger.info("当前节点名称：" + key + "[" + i + "]");//当前节点名称
                        logger.info("当前节点Xpath：" + arryPath.toString());// 当前节点路径

                        MocksysTemplateNodeInfo mocksysTemplateNodeInfoArray = new MocksysTemplateNodeInfo();
                        mocksysTemplateNodeInfoArray.setBusiCode(busiCode);// 交易码
                        mocksysTemplateNodeInfoArray.setMsgType(JSON_STR);// 报文类型
                        mocksysTemplateNodeInfoArray.setFieldName(key + "[" + i + "]");// 当前节点名称
                        mocksysTemplateNodeInfoArray.setPath(arryPath.toString());// 路径
                        mocksysTemplateNodeInfoArray.setChannel(channel);// 渠道
                        mocksysTemplateNodeInfoArray.setParentId(mocksysTemplateNodeInfo.getId());
                        mocksysTemplateNodeInfoArray.setNumber(String.valueOf(msgNnumber++));

                        JSONObject o1 = jsonArray.getJSONObject(i);
                        if (null != o1.keySet()) {
                            mocksysTemplateNodeInfoArray.setIsSonNode(false);
                            mocksysTemplateNodeInfoDAO.insert(mocksysTemplateNodeInfoArray);
                            getJsonNodes(busiCode, channel, o1, arryPath.toString(), mocksysTemplateNodeInfoArray.getId());
                        } else {
                            mocksysTemplateNodeInfoArray.setIsSonNode(true);
                            mocksysTemplateNodeInfoDAO.insert(mocksysTemplateNodeInfoArray);
                        }
                    }
                }
            }
        }
    }

    public List<NatureTree> getMsgTree (String busiCode, String msgType ){
        List<NatureTree> theTree = new ArrayList<>();
        if (null != busiCode && null != msgType) {
            MocksysTemplateNodeInfoExample example = new MocksysTemplateNodeInfoExample();
            MocksysTemplateNodeInfoExample.Criteria criteria = example.createCriteria();
            criteria.andBusiCodeEqualTo(busiCode);
            criteria.andMsgTypeEqualTo(msgType);
            List<MocksysTemplateNodeInfo> list = mocksysTemplateNodeInfoDAO.selectByExample(example);

            if (null != list && list.size() > 0) {
                theTree = makeNewTree(list, msgType);
            }
            logger.info("数据处理完毕");
            return theTree;
        } else {
            logger.info("获取报文树失败，传递参数为空");
            return null;
        }
    }

    private List<NatureTree> makeNewTree (List < MocksysTemplateNodeInfo > list, String msgType){
        List<NatureTree> theTree = new ArrayList<>();
        if (msgType.equals(FLSTR_STR)) {
            for (MocksysTemplateNodeInfo dto : list) {
                NatureTree natureTree = new NatureTree();
                natureTree.setId(dto.getId());
                natureTree.setParentId(null);
                natureTree.setLabel(dto.getFieldName());
                natureTree.setPath("");
                natureTree.setChildren(null);
                natureTree.setNumber(dto.getNumber());
                natureTree.setBusiCode(dto.getBusiCode());
                natureTree.setMsgType(dto.getMsgType());
                natureTree.setSign(dto.getSign());
                natureTree.setLocation(dto.getLocation());
                natureTree.setLength(dto.getLength());
                natureTree.setLoopCount(dto.getLoopCount());
                natureTree.setLoopLength(dto.getLoopLength());
                theTree.add(natureTree);
            }
        } else {
            for (MocksysTemplateNodeInfo dto : list) {
                NatureTree natureTree = new NatureTree();
                natureTree.setId(dto.getId());
                natureTree.setParentId(dto.getParentId());
                natureTree.setLabel(dto.getFieldName());
                natureTree.setNumber(dto.getNumber());
                natureTree.setBusiCode(dto.getBusiCode());
                natureTree.setMsgType(dto.getMsgType());
                natureTree.setLocation(dto.getLocation());
                natureTree.setLength(dto.getLength());
                natureTree.setLoopCount(dto.getLoopCount());
                natureTree.setLoopLength(dto.getLoopLength());
                if (XML_STR.equals(msgType)) {
                    natureTree.setPath(dto.getPath());
                    natureTree.setXpath(dto.getPath());
                    natureTree.setSign(dto.getSign());
                } else {
                    natureTree.setSign("");
                    natureTree.setPath(dto.getPath());
                    natureTree.setXpath(dto.getPath().substring(1).replace(".", "/"));
                }
                if (dto.getIsSonNode()) {
                    natureTree.setChildren(null);
                } else {
                    List<NatureTree> childList = new ArrayList<>();
                    natureTree.setChildren(childList);
                }
                if (0 == dto.getParentId()) {
                    theTree.add(natureTree);
                    continue;
                }
                if (null != theTree && theTree.size() > 0) {
                    /** 把节点放在对应的位置上 */
                    workOnTreeNode(dto, theTree, natureTree);
                }
            }
        }
        return theTree;
    }

    private void workOnTreeNode (MocksysTemplateNodeInfo dto, List < NatureTree > parentTree, NatureTree natureTree){
        Iterator<NatureTree> it = parentTree.iterator();
        while (it.hasNext()) {
            NatureTree n = it.next();

            if (StringUtils.isNotNull(n.getId()) && n.getId() == dto.getParentId()) {
                n.getChildren().add(natureTree);
            } else {
                if (null != n.getChildren() && n.getChildren().size() > 0) {
                    workOnTreeNode(dto, n.getChildren(), natureTree);
                }
            }
        }
    }
}

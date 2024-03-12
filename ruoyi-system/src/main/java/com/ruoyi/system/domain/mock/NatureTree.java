package com.ruoyi.system.domain.mock;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.List;

/**
 * Treeselect树结构实体类
 * 
 * @author ruoyi
 */
public class NatureTree implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 节点ID */
    private Integer id;

    /** 父节点ID */
    private Integer parentId;

    /** 节点名称 */
    private String label;

    /** 绝对路径 */
    private String Xpath;

    /** 展示路径 */
    private String path;

    /** 映射用序号字段 */
    private String number;

    /** 交易码 */
    private String busiCode;

    /** 报文类型 */
    private String msgType;

    /** xml区分相同路径不同元素（同级重名元素） */
    private String sign;

    /** 定长字符串字段起始位置 */
    private Integer location;

    /** 定长字符串字段长度 */
    private Integer length;

    /** 定长字符串可循环部分循环次数 */
    private Integer loopCount;

    /** 定长字符串可循环部分单次字符长度 */
    private Integer loopLength;

    /** 子节点 */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<NatureTree> children;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getLabel()
    {
        return label;
    }

    public void setLabel(String label)
    {
        this.label = label;
    }

    public List<NatureTree> getChildren()
    {
        return children;
    }

    public void setChildren(List<NatureTree> children)
    {
        this.children = children;
    }

    public String getXpath() {
        return Xpath;
    }

    public void setXpath(String xpath) {
        Xpath = xpath;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getBusiCode() {
        return busiCode;
    }

    public void setBusiCode(String busiCode) {
        this.busiCode = busiCode;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public Integer getLocation() {
        return location;
    }

    public void setLocation(Integer location) {
        this.location = location;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getLoopCount() {
        return loopCount;
    }

    public void setLoopCount(Integer loopCount) {
        this.loopCount = loopCount;
    }

    public Integer getLoopLength() {
        return loopLength;
    }

    public void setLoopLength(Integer loopLength) {
        this.loopLength = loopLength;
    }
}

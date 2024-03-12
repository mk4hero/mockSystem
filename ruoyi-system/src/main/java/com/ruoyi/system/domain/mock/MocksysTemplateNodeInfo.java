package com.ruoyi.system.domain.mock;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 报文模板数据对象 mocksys_template_node_info
 * 
 * @author ruoyi
 * @date 2024-03-06
 */
public class MocksysTemplateNodeInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 自增编号 */
    private Integer id;

    /** 交易码 */
    @Excel(name = "交易码")
    private String busiCode;

    /** 渠道 */
    @Excel(name = "渠道")
    private String channel;

    /** 报文类型：xml,json,flStr(定长字符串) */
    @Excel(name = "报文类型：xml,json,flStr(定长字符串)")
    private String msgType;

    /** 字段名称 */
    @Excel(name = "字段名称")
    private String fieldName;

    /** xml,json节点绝对路径 */
    @Excel(name = "xml,json节点绝对路径")
    private String path;

    /** xml区分相同路径不同元素（同级重名元素） */
    @Excel(name = "xml区分相同路径不同元素", readConverterExp = "同=级重名元素")
    private String sign;

    /** 定长字符串字段起始位置 */
    @Excel(name = "定长字符串字段起始位置")
    private Integer location;

    /** 定长字符串字段长度 */
    @Excel(name = "定长字符串字段长度")
    private Integer length;

    /** 定长字符串可循环部分循环次数 */
    @Excel(name = "定长字符串可循环部分循环次数")
    private Integer loopCount;

    /** 定长字符串可循环部分单次字符长度 */
    @Excel(name = "定长字符串可循环部分单次字符长度")
    private Integer loopLength;

    /** 父节点id */
    @Excel(name = "父节点id")
    private Integer parentId;

    /** 是否为叶子节点 */
    @Excel(name = "是否为叶子节点")
    private Boolean isSonNode;

    /** 映射用序号字段 */
    @Excel(name = "映射用序号字段")
    private String number;

    /** 备注字段1 */
    @Excel(name = "备注字段1")
    private String remark1;

    /** 备注字段2 */
    @Excel(name = "备注字段2")
    private String remark2;

    /** 备注字段3 */
    @Excel(name = "备注字段3")
    private String remark3;

    public void setId(Integer id) 
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }
    public void setBusiCode(String busiCode) 
    {
        this.busiCode = busiCode;
    }

    public String getBusiCode() 
    {
        return busiCode;
    }
    public void setChannel(String channel) 
    {
        this.channel = channel;
    }

    public String getChannel() 
    {
        return channel;
    }
    public void setMsgType(String msgType) 
    {
        this.msgType = msgType;
    }

    public String getMsgType() 
    {
        return msgType;
    }
    public void setFieldName(String fieldName) 
    {
        this.fieldName = fieldName;
    }

    public String getFieldName() 
    {
        return fieldName;
    }
    public void setPath(String path) 
    {
        this.path = path;
    }

    public String getPath() 
    {
        return path;
    }
    public void setSign(String sign) 
    {
        this.sign = sign;
    }

    public String getSign() 
    {
        return sign;
    }
    public void setLocation(Integer location) 
    {
        this.location = location;
    }

    public Integer getLocation() 
    {
        return location;
    }
    public void setLength(Integer length) 
    {
        this.length = length;
    }

    public Integer getLength() 
    {
        return length;
    }
    public void setLoopCount(Integer loopCount) 
    {
        this.loopCount = loopCount;
    }

    public Integer getLoopCount() 
    {
        return loopCount;
    }
    public void setLoopLength(Integer loopLength) 
    {
        this.loopLength = loopLength;
    }

    public Integer getLoopLength() 
    {
        return loopLength;
    }
    public void setParentId(Integer parentId) 
    {
        this.parentId = parentId;
    }

    public Integer getParentId() 
    {
        return parentId;
    }
    public void setIsSonNode(Boolean isSonNode) 
    {
        this.isSonNode = isSonNode;
    }

    public Boolean getIsSonNode() 
    {
        return isSonNode;
    }
    public void setNumber(String number) 
    {
        this.number = number;
    }

    public String getNumber() 
    {
        return number;
    }
    public void setRemark1(String remark1) 
    {
        this.remark1 = remark1;
    }

    public String getRemark1() 
    {
        return remark1;
    }
    public void setRemark2(String remark2) 
    {
        this.remark2 = remark2;
    }

    public String getRemark2() 
    {
        return remark2;
    }
    public void setRemark3(String remark3) 
    {
        this.remark3 = remark3;
    }

    public String getRemark3() 
    {
        return remark3;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("busiCode", getBusiCode())
            .append("channel", getChannel())
            .append("msgType", getMsgType())
            .append("fieldName", getFieldName())
            .append("path", getPath())
            .append("sign", getSign())
            .append("location", getLocation())
            .append("length", getLength())
            .append("loopCount", getLoopCount())
            .append("loopLength", getLoopLength())
            .append("parentId", getParentId())
            .append("isSonNode", getIsSonNode())
            .append("number", getNumber())
            .append("remark1", getRemark1())
            .append("remark2", getRemark2())
            .append("remark3", getRemark3())
            .toString();
    }
}

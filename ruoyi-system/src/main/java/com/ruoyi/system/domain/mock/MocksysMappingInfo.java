package com.ruoyi.system.domain.mock;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 映射信息对象 mocksys_mapping_info
 * 
 * @author ruoyi
 * @date 2024-03-06
 */
public class MocksysMappingInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 自增编号 */
    private Integer id;

    /** 原交易码 */
    @Excel(name = "原交易码")
    private String sourceBusiCode;

    /** 原报文类型：xml,json,flStr(定长字符串) */
    @Excel(name = "原报文类型：xml,json,flStr(定长字符串)")
    private String sourceMsgType;

    /** 原报文字段名称 */
    @Excel(name = "原报文字段名称")
    private String sourceFieldName;

    /** 原映射用序号字段 */
    @Excel(name = "原映射用序号字段")
    private Integer sourceNumber;

    /** 原xml,json指定待修改子节点绝对路径 */
    @Excel(name = "原xml,json指定待修改子节点绝对路径")
    private String sourcePath;

    /** 原报文xml区分相同路径不同元素元素（同级重名元素） */
    @Excel(name = "原报文xml区分相同路径不同元素元素", readConverterExp = "同=级重名元素")
    private String sourceSign;

    /** 原报文定长字符串字段起始位置 */
    @Excel(name = "原报文定长字符串字段起始位置")
    private Integer sourceLocation;

    /** 原报文定长字符串字段长度 */
    @Excel(name = "原报文定长字符串字段长度")
    private Integer sourceLength;

    /** 原报文定长字符串可循环部分循环次数 */
    @Excel(name = "原报文定长字符串可循环部分循环次数")
    private Integer sourceLoopCount;

    /** 原报文定长字符串可循环部分单次字符长度 */
    @Excel(name = "原报文定长字符串可循环部分单次字符长度")
    private Integer sourceLoopLength;

    /** 目标交易码 */
    @Excel(name = "目标交易码")
    private String targetBusiCode;

    /** 目标报文类型：xml,json,flStr(定长字符串) */
    @Excel(name = "目标报文类型：xml,json,flStr(定长字符串)")
    private String targetMsgType;

    /** 目标报文字段名称 */
    @Excel(name = "目标报文字段名称")
    private String targetFieldName;

    /** 目标映射用序号字段 */
    @Excel(name = "目标映射用序号字段")
    private Integer targetNumber;

    /** 目标xml,json指定待修改子节点绝对路径 */
    @Excel(name = "目标xml,json指定待修改子节点绝对路径")
    private String targetPath;

    /** 目标报文xml区分相同路径不同元素元素（同级重名元素） */
    @Excel(name = "目标报文xml区分相同路径不同元素元素", readConverterExp = "同=级重名元素")
    private String targetSign;

    /** 目标报文定长字符串字段起始位置 */
    @Excel(name = "目标报文定长字符串字段起始位置")
    private Integer targetLocation;

    /** 目标报文定长字符串字段长度 */
    @Excel(name = "目标报文定长字符串字段长度")
    private Integer targetLength;

    /** 目标报文定长字符串可循环部分循环次数 */
    @Excel(name = "目标报文定长字符串可循环部分循环次数")
    private Integer targetLoopCount;

    /** 目标报文定长字符串可循环部分单次字符长度 */
    @Excel(name = "目标报文定长字符串可循环部分单次字符长度")
    private Integer targetLoopLength;

    /** 动作：00默认为空，01字段值映射，02字段名修改，03字段属性提取，04字段新增 */
    @Excel(name = "动作：00默认为空，01字段值映射，02字段名修改，03字段属性提取，04字段新增")
    private String action;

    /** 具体修改操作 */
    @Excel(name = "具体修改操作")
    private String operate;

    /** 配合修改参数(JSON格式) */
    @Excel(name = "配合修改参数(JSON格式)")
    private String parameter;

    /** 渠道 */
    @Excel(name = "渠道")
    private String channel;

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
    public void setSourceBusiCode(String sourceBusiCode) 
    {
        this.sourceBusiCode = sourceBusiCode;
    }

    public String getSourceBusiCode() 
    {
        return sourceBusiCode;
    }
    public void setSourceMsgType(String sourceMsgType) 
    {
        this.sourceMsgType = sourceMsgType;
    }

    public String getSourceMsgType() 
    {
        return sourceMsgType;
    }
    public void setSourceFieldName(String sourceFieldName) 
    {
        this.sourceFieldName = sourceFieldName;
    }

    public String getSourceFieldName() 
    {
        return sourceFieldName;
    }
    public void setSourceNumber(Integer sourceNumber) 
    {
        this.sourceNumber = sourceNumber;
    }

    public Integer getSourceNumber() 
    {
        return sourceNumber;
    }
    public void setSourcePath(String sourcePath) 
    {
        this.sourcePath = sourcePath;
    }

    public String getSourcePath() 
    {
        return sourcePath;
    }
    public void setSourceSign(String sourceSign) 
    {
        this.sourceSign = sourceSign;
    }

    public String getSourceSign() 
    {
        return sourceSign;
    }
    public void setSourceLocation(Integer sourceLocation) 
    {
        this.sourceLocation = sourceLocation;
    }

    public Integer getSourceLocation() 
    {
        return sourceLocation;
    }
    public void setSourceLength(Integer sourceLength) 
    {
        this.sourceLength = sourceLength;
    }

    public Integer getSourceLength() 
    {
        return sourceLength;
    }
    public void setSourceLoopCount(Integer sourceLoopCount) 
    {
        this.sourceLoopCount = sourceLoopCount;
    }

    public Integer getSourceLoopCount() 
    {
        return sourceLoopCount;
    }
    public void setSourceLoopLength(Integer sourceLoopLength) 
    {
        this.sourceLoopLength = sourceLoopLength;
    }

    public Integer getSourceLoopLength() 
    {
        return sourceLoopLength;
    }
    public void setTargetBusiCode(String targetBusiCode) 
    {
        this.targetBusiCode = targetBusiCode;
    }

    public String getTargetBusiCode() 
    {
        return targetBusiCode;
    }
    public void setTargetMsgType(String targetMsgType) 
    {
        this.targetMsgType = targetMsgType;
    }

    public String getTargetMsgType() 
    {
        return targetMsgType;
    }
    public void setTargetFieldName(String targetFieldName) 
    {
        this.targetFieldName = targetFieldName;
    }

    public String getTargetFieldName() 
    {
        return targetFieldName;
    }
    public void setTargetNumber(Integer targetNumber) 
    {
        this.targetNumber = targetNumber;
    }

    public Integer getTargetNumber() 
    {
        return targetNumber;
    }
    public void setTargetPath(String targetPath) 
    {
        this.targetPath = targetPath;
    }

    public String getTargetPath() 
    {
        return targetPath;
    }
    public void setTargetSign(String targetSign) 
    {
        this.targetSign = targetSign;
    }

    public String getTargetSign() 
    {
        return targetSign;
    }
    public void setTargetLocation(Integer targetLocation) 
    {
        this.targetLocation = targetLocation;
    }

    public Integer getTargetLocation() 
    {
        return targetLocation;
    }
    public void setTargetLength(Integer targetLength) 
    {
        this.targetLength = targetLength;
    }

    public Integer getTargetLength() 
    {
        return targetLength;
    }
    public void setTargetLoopCount(Integer targetLoopCount) 
    {
        this.targetLoopCount = targetLoopCount;
    }

    public Integer getTargetLoopCount() 
    {
        return targetLoopCount;
    }
    public void setTargetLoopLength(Integer targetLoopLength) 
    {
        this.targetLoopLength = targetLoopLength;
    }

    public Integer getTargetLoopLength() 
    {
        return targetLoopLength;
    }
    public void setAction(String action) 
    {
        this.action = action;
    }

    public String getAction() 
    {
        return action;
    }
    public void setOperate(String operate) 
    {
        this.operate = operate;
    }

    public String getOperate() 
    {
        return operate;
    }
    public void setParameter(String parameter) 
    {
        this.parameter = parameter;
    }

    public String getParameter() 
    {
        return parameter;
    }
    public void setChannel(String channel) 
    {
        this.channel = channel;
    }

    public String getChannel() 
    {
        return channel;
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
            .append("sourceBusiCode", getSourceBusiCode())
            .append("sourceMsgType", getSourceMsgType())
            .append("sourceFieldName", getSourceFieldName())
            .append("sourceNumber", getSourceNumber())
            .append("sourcePath", getSourcePath())
            .append("sourceSign", getSourceSign())
            .append("sourceLocation", getSourceLocation())
            .append("sourceLength", getSourceLength())
            .append("sourceLoopCount", getSourceLoopCount())
            .append("sourceLoopLength", getSourceLoopLength())
            .append("targetBusiCode", getTargetBusiCode())
            .append("targetMsgType", getTargetMsgType())
            .append("targetFieldName", getTargetFieldName())
            .append("targetNumber", getTargetNumber())
            .append("targetPath", getTargetPath())
            .append("targetSign", getTargetSign())
            .append("targetLocation", getTargetLocation())
            .append("targetLength", getTargetLength())
            .append("targetLoopCount", getTargetLoopCount())
            .append("targetLoopLength", getTargetLoopLength())
            .append("action", getAction())
            .append("operate", getOperate())
            .append("parameter", getParameter())
            .append("channel", getChannel())
            .append("remark1", getRemark1())
            .append("remark2", getRemark2())
            .append("remark3", getRemark3())
            .toString();
    }
}

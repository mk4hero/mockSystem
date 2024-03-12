package com.ruoyi.system.domain.mock;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 报文模板函数数据对象 mocksys_function_info
 * 
 * @author ruoyi
 * @date 2024-03-08
 */
public class MocksysFunctionInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 自增编号 */
    private Integer id;

    /** 函数名称 */
    @Excel(name = "函数名称")
    private String name;

    /** 函数中文名称 */
    @Excel(name = "函数中文名称")
    private String cnName;

    /** 报文类型：xml,json,flStr(定长字符串) */
    @Excel(name = "报文类型：xml,json,flStr(定长字符串)")
    private String msgType;

    /** 操作类型：00默认为空，01字段值映射，02字段名修改，03字段属性提取，04字段新增 */
    @Excel(name = "操作类型：00默认为空，01字段值映射，02字段名修改，03字段属性提取，04字段新增")
    private String action;

    /** 参数个数 */
    @Excel(name = "参数个数")
    private Integer parameterCount;

    /** 用于按顺序展示参数提示，存储字典表字典项 */
    @Excel(name = "用于按顺序展示参数提示，存储字典表字典项")
    private String parameterType;

    /** 用于下拉选展示数据，存储字典表字典项 */
    @Excel(name = "用于下拉选展示数据，存储字典表字典项")
    private String optionData;

    public void setId(Integer id) 
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setCnName(String cnName) 
    {
        this.cnName = cnName;
    }

    public String getCnName() 
    {
        return cnName;
    }
    public void setMsgType(String msgType) 
    {
        this.msgType = msgType;
    }

    public String getMsgType() 
    {
        return msgType;
    }
    public void setAction(String action) 
    {
        this.action = action;
    }

    public String getAction() 
    {
        return action;
    }
    public void setParameterCount(Integer parameterCount) 
    {
        this.parameterCount = parameterCount;
    }

    public Integer getParameterCount() 
    {
        return parameterCount;
    }
    public void setParameterType(String parameterType) 
    {
        this.parameterType = parameterType;
    }

    public String getParameterType() 
    {
        return parameterType;
    }
    public void setOptionData(String optionData) 
    {
        this.optionData = optionData;
    }

    public String getOptionData() 
    {
        return optionData;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("cnName", getCnName())
            .append("msgType", getMsgType())
            .append("action", getAction())
            .append("parameterCount", getParameterCount())
            .append("parameterType", getParameterType())
            .append("optionData", getOptionData())
            .toString();
    }
}

package com.ruoyi.system.domain.mock;

import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 报文归档信息对象 mocksys_messages_info
 * 
 * @author ruoyi
 * @date 2024-02-27
 */
public class MocksysMessagesInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号(32位uuid) */
    private String id;

    /** 交易码 */
    private String busiCode;

    /** 报文类型：xml,json,flStr(定长字符串) */
    private String msgType;

    /** 渠道 */
    private String channel;

    /** 请求接收时间 */
    private String reqDateTime;

    /** 请求报文信息 */
    private String reqMsg;

    /** 回复报文时间 */
    private String respDateTime;

    /** 回复报文信息 */
    private String respMsg;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
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
    public void setMsgType(String msgType) 
    {
        this.msgType = msgType;
    }

    public String getMsgType() 
    {
        return msgType;
    }
    public void setChannel(String channel) 
    {
        this.channel = channel;
    }

    public String getChannel() 
    {
        return channel;
    }
    public void setReqDateTime(String reqDateTime) 
    {
        this.reqDateTime = reqDateTime;
    }

    public String getReqDateTime() 
    {
        return reqDateTime;
    }
    public void setReqMsg(String reqMsg) 
    {
        this.reqMsg = reqMsg;
    }

    public String getReqMsg() 
    {
        return reqMsg;
    }
    public void setRespDateTime(String respDateTime) 
    {
        this.respDateTime = respDateTime;
    }

    public String getRespDateTime() 
    {
        return respDateTime;
    }
    public void setRespMsg(String respMsg) 
    {
        this.respMsg = respMsg;
    }

    public String getRespMsg() 
    {
        return respMsg;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("busiCode", getBusiCode())
            .append("msgType", getMsgType())
            .append("channel", getChannel())
            .append("reqDateTime", getReqDateTime())
            .append("reqMsg", getReqMsg())
            .append("respDateTime", getRespDateTime())
            .append("respMsg", getRespMsg())
            .toString();
    }
}

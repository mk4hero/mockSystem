package com.ruoyi.system.domain.mock;

import java.io.Serializable;

/**
 * @author 
 * 报文归档信息表
 */
public class MocksysMessagesInfoWithBLOBs extends MocksysMessagesInfo implements Serializable {
    /**
     * 请求报文信息
     */
    private String reqMsg;

    /**
     * 回复报文信息
     */
    private String respMsg;

    private static final long serialVersionUID = 1L;

    public String getReqMsg() {
        return reqMsg;
    }

    public void setReqMsg(String reqMsg) {
        this.reqMsg = reqMsg;
    }

    public String getRespMsg() {
        return respMsg;
    }

    public void setRespMsg(String respMsg) {
        this.respMsg = respMsg;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        MocksysMessagesInfoWithBLOBs other = (MocksysMessagesInfoWithBLOBs) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getBusiCode() == null ? other.getBusiCode() == null : this.getBusiCode().equals(other.getBusiCode()))
            && (this.getMsgType() == null ? other.getMsgType() == null : this.getMsgType().equals(other.getMsgType()))
            && (this.getChannel() == null ? other.getChannel() == null : this.getChannel().equals(other.getChannel()))
            && (this.getReqDateTime() == null ? other.getReqDateTime() == null : this.getReqDateTime().equals(other.getReqDateTime()))
            && (this.getRespDateTime() == null ? other.getRespDateTime() == null : this.getRespDateTime().equals(other.getRespDateTime()))
            && (this.getReqMsg() == null ? other.getReqMsg() == null : this.getReqMsg().equals(other.getReqMsg()))
            && (this.getRespMsg() == null ? other.getRespMsg() == null : this.getRespMsg().equals(other.getRespMsg()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getBusiCode() == null) ? 0 : getBusiCode().hashCode());
        result = prime * result + ((getMsgType() == null) ? 0 : getMsgType().hashCode());
        result = prime * result + ((getChannel() == null) ? 0 : getChannel().hashCode());
        result = prime * result + ((getReqDateTime() == null) ? 0 : getReqDateTime().hashCode());
        result = prime * result + ((getRespDateTime() == null) ? 0 : getRespDateTime().hashCode());
        result = prime * result + ((getReqMsg() == null) ? 0 : getReqMsg().hashCode());
        result = prime * result + ((getRespMsg() == null) ? 0 : getRespMsg().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", reqMsg=").append(reqMsg);
        sb.append(", respMsg=").append(respMsg);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
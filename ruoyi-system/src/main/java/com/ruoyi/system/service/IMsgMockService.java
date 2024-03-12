package com.ruoyi.system.service;

import com.ruoyi.system.domain.mock.NatureTree;

import java.util.List;

public interface IMsgMockService {
    public String serialNetDoXml(StringBuffer reqMsg, String busiCode);

    public String serialNetDoJson(StringBuffer reqMsg, String busiCode);

    public String serialNetDoFlStr(StringBuffer reqMsg, String busiCode);

    public List<NatureTree> getMsgTree(String busiCode, String msgType );
}

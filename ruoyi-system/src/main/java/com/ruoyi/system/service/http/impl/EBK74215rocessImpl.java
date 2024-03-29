package com.ruoyi.system.service.http.impl;

import com.ruoyi.common.enums.MsgType;
import com.ruoyi.common.exception.GlobalException;
import com.ruoyi.system.service.http.IProcessService;
import com.ruoyi.system.service.impl.MsgMockServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("74215")
public class EBK74215rocessImpl implements IProcessService {
    private static final Logger logger = LoggerFactory.getLogger(EBK74215rocessImpl.class);

    private static String busiCode = "74215";

    @Autowired
    private MsgMockServiceImpl msgMockServiceImpl;

    @Override
    public Object doProcess(StringBuffer inMsg, String msgType) throws GlobalException {
        String mag = "";
        switch (MsgType.getKey(msgType)){
            case XML:
                mag = msgMockServiceImpl.serialNetDoXml(inMsg, busiCode);
                break;
            case JSON:
                mag = msgMockServiceImpl.serialNetDoJson(inMsg, busiCode);
                break;
            case FLSTR:
                mag = msgMockServiceImpl.serialNetDoFlStr(inMsg, busiCode);
                break;
            default :
                logger.error("报文类型判断错误，请检查！");
        }

        return mag;
    }
}

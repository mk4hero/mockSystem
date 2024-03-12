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
        switch (MsgType.getKey(msgType)){
            case XML:
                msgMockServiceImpl.serialNetDoXml(inMsg, busiCode);
//                msgMockServiceImpl.mapping(inMsg, msgType, busiCode);
                break;
            case JSON:
                msgMockServiceImpl.serialNetDoJson(inMsg, busiCode);
//                msgMockServiceImpl.mapping(inMsg, msgType, busiCode);
                break;
            case FLSTR:
                msgMockServiceImpl.serialNetDoFlStr(inMsg, busiCode);
//                msgMockServiceImpl.mapping(inMsg, msgType, busiCode);
                break;
            default :
                logger.error("报文类型判断错误，请检查！");
        }

        return null;
    }
}

package com.ruoyi.web.controller.mock;

import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.exception.GlobalException;
import com.ruoyi.system.service.impl.EsbMsgService;
import com.ruoyi.system.service.impl.MsgMockServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Anonymous
@RestController
@RequestMapping("/mock")
public class MockSysController extends BaseController
{
    public static final Logger logger = LoggerFactory.getLogger(MockSysController.class);

    @Autowired
    private EsbMsgService esbMsgService;

    @Autowired
    private MsgMockServiceImpl msgMockServiceImpl;

    @GetMapping("/msgDispose")
    public AjaxResult msgDispose(HttpServletRequest request) throws GlobalException, IOException {
        esbMsgService.doEBKProcess(request);
        return success("成功");
    }

    @GetMapping("/getTree")
    public AjaxResult getMsgTree( String busiCode, String msgType ){
        return success(msgMockServiceImpl.getMsgTree(busiCode, msgType));
    }
}



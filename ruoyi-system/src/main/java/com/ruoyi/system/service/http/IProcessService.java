package com.ruoyi.system.service.http;

import com.ruoyi.common.exception.GlobalException;

/***
 * 区分是做透传还是做映射
 * 1.透传，在原有报文基础上做修改调整
 * 2.映射，将原报文转换成其他格式的报文信息
 *
 * 按照不同业务码 进行分类处理，实现每个方法拥有自己的逻辑
 */
public interface IProcessService {

    public Object doProcess(StringBuffer inMsg, String msgType) throws GlobalException;
}

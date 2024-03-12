package com.ruoyi.system.service;


import com.ruoyi.system.domain.mock.MocksysMessagesInfo;

import java.util.List;

/**
 * 报文归档信息Service接口
 * 
 * @author ruoyi
 * @date 2024-02-27
 */
public interface IMocksysMessagesInfoService 
{
    /**
     * 查询报文归档信息
     * 
     * @param id 报文归档信息主键
     * @return 报文归档信息
     */
    public MocksysMessagesInfo selectMocksysMessagesInfoById(String id);

    /**
     * 查询报文归档信息列表
     * 
     * @param mocksysMessagesInfo 报文归档信息
     * @return 报文归档信息集合
     */
    public List<MocksysMessagesInfo> selectMocksysMessagesInfoList(MocksysMessagesInfo mocksysMessagesInfo);

    /**
     * 新增报文归档信息
     * 
     * @param mocksysMessagesInfo 报文归档信息
     * @return 结果
     */
    public int insertMocksysMessagesInfo(MocksysMessagesInfo mocksysMessagesInfo);

    /**
     * 修改报文归档信息
     * 
     * @param mocksysMessagesInfo 报文归档信息
     * @return 结果
     */
    public int updateMocksysMessagesInfo(MocksysMessagesInfo mocksysMessagesInfo);

    /**
     * 批量删除报文归档信息
     * 
     * @param ids 需要删除的报文归档信息主键集合
     * @return 结果
     */
    public int deleteMocksysMessagesInfoByIds(String[] ids);

    /**
     * 删除报文归档信息信息
     * 
     * @param id 报文归档信息主键
     * @return 结果
     */
    public int deleteMocksysMessagesInfoById(String id);
}

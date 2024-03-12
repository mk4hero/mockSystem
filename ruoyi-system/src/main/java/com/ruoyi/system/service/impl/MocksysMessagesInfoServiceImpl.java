package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.mock.MocksysMessagesInfo;
import com.ruoyi.system.mapper.mock.MocksysMessagesInfoMapper;
import com.ruoyi.system.service.IMocksysMessagesInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 报文归档信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-02-27
 */
@Service
public class MocksysMessagesInfoServiceImpl implements IMocksysMessagesInfoService
{
    @Autowired
    private MocksysMessagesInfoMapper mocksysMessagesInfoMapper;

    /**
     * 查询报文归档信息
     * 
     * @param id 报文归档信息主键
     * @return 报文归档信息
     */
    @Override
    public MocksysMessagesInfo selectMocksysMessagesInfoById(String id)
    {
        return mocksysMessagesInfoMapper.selectMocksysMessagesInfoById(id);
    }

    /**
     * 查询报文归档信息列表
     * 
     * @param mocksysMessagesInfo 报文归档信息
     * @return 报文归档信息
     */
    @Override
    public List<MocksysMessagesInfo> selectMocksysMessagesInfoList(MocksysMessagesInfo mocksysMessagesInfo)
    {
        return mocksysMessagesInfoMapper.selectMocksysMessagesInfoList(mocksysMessagesInfo);
    }

    /**
     * 新增报文归档信息
     * 
     * @param mocksysMessagesInfo 报文归档信息
     * @return 结果
     */
    @Override
    public int insertMocksysMessagesInfo(MocksysMessagesInfo mocksysMessagesInfo)
    {
        return mocksysMessagesInfoMapper.insertMocksysMessagesInfo(mocksysMessagesInfo);
    }

    /**
     * 修改报文归档信息
     * 
     * @param mocksysMessagesInfo 报文归档信息
     * @return 结果
     */
    @Override
    public int updateMocksysMessagesInfo(MocksysMessagesInfo mocksysMessagesInfo)
    {
        return mocksysMessagesInfoMapper.updateMocksysMessagesInfo(mocksysMessagesInfo);
    }

    /**
     * 批量删除报文归档信息
     * 
     * @param ids 需要删除的报文归档信息主键
     * @return 结果
     */
    @Override
    public int deleteMocksysMessagesInfoByIds(String[] ids)
    {
        return mocksysMessagesInfoMapper.deleteMocksysMessagesInfoByIds(ids);
    }

    /**
     * 删除报文归档信息信息
     * 
     * @param id 报文归档信息主键
     * @return 结果
     */
    @Override
    public int deleteMocksysMessagesInfoById(String id)
    {
        return mocksysMessagesInfoMapper.deleteMocksysMessagesInfoById(id);
    }
}

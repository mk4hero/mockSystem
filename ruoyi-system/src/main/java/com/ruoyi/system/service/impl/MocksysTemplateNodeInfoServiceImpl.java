package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.system.domain.mock.MocksysTemplateNodeInfo;
import com.ruoyi.system.mapper.mock.MocksysTemplateNodeInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.service.IMocksysTemplateNodeInfoService;

/**
 * 报文模板数据Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-03-02
 */
@Service
public class MocksysTemplateNodeInfoServiceImpl implements IMocksysTemplateNodeInfoService 
{
    @Autowired
    private MocksysTemplateNodeInfoMapper mocksysTemplateNodeInfoMapper;

    /**
     * 查询报文模板数据
     * 
     * @param id 报文模板数据主键
     * @return 报文模板数据
     */
    @Override
    public MocksysTemplateNodeInfo selectMocksysTemplateNodeInfoById(Integer id)
    {
        return mocksysTemplateNodeInfoMapper.selectMocksysTemplateNodeInfoById(id);
    }

    /**
     * 查询报文模板数据列表
     * 
     * @param mocksysTemplateNodeInfo 报文模板数据
     * @return 报文模板数据
     */
    @Override
    public List<MocksysTemplateNodeInfo> selectMocksysTemplateNodeInfoList(MocksysTemplateNodeInfo mocksysTemplateNodeInfo)
    {
        return mocksysTemplateNodeInfoMapper.selectMocksysTemplateNodeInfoList(mocksysTemplateNodeInfo);
    }

    /**
     * 新增报文模板数据
     * 
     * @param mocksysTemplateNodeInfo 报文模板数据
     * @return 结果
     */
    @Override
    public int insertMocksysTemplateNodeInfo(MocksysTemplateNodeInfo mocksysTemplateNodeInfo)
    {
        return mocksysTemplateNodeInfoMapper.insertMocksysTemplateNodeInfo(mocksysTemplateNodeInfo);
    }

    /**
     * 修改报文模板数据
     * 
     * @param mocksysTemplateNodeInfo 报文模板数据
     * @return 结果
     */
    @Override
    public int updateMocksysTemplateNodeInfo(MocksysTemplateNodeInfo mocksysTemplateNodeInfo)
    {
        return mocksysTemplateNodeInfoMapper.updateMocksysTemplateNodeInfo(mocksysTemplateNodeInfo);
    }

    /**
     * 批量删除报文模板数据
     * 
     * @param ids 需要删除的报文模板数据主键
     * @return 结果
     */
    @Override
    public int deleteMocksysTemplateNodeInfoByIds(Integer[] ids)
    {
        return mocksysTemplateNodeInfoMapper.deleteMocksysTemplateNodeInfoByIds(ids);
    }

    /**
     * 删除报文模板数据信息
     * 
     * @param id 报文模板数据主键
     * @return 结果
     */
    @Override
    public int deleteMocksysTemplateNodeInfoById(Integer id)
    {
        return mocksysTemplateNodeInfoMapper.deleteMocksysTemplateNodeInfoById(id);
    }
}

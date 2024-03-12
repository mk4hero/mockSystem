package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.system.domain.mock.MocksysFunctionInfo;
import com.ruoyi.system.mapper.mock.MocksysFunctionInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.service.IMocksysFunctionInfoService;

/**
 * 报文模板函数数据Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-03-08
 */
@Service
public class MocksysFunctionInfoServiceImpl implements IMocksysFunctionInfoService 
{
    @Autowired
    private MocksysFunctionInfoMapper mocksysFunctionInfoMapper;

    /**
     * 查询报文模板函数数据
     * 
     * @param id 报文模板函数数据主键
     * @return 报文模板函数数据
     */
    @Override
    public MocksysFunctionInfo selectMocksysFunctionInfoById(Integer id)
    {
        return mocksysFunctionInfoMapper.selectMocksysFunctionInfoById(id);
    }

    /**
     * 查询报文模板函数数据列表
     * 
     * @param mocksysFunctionInfo 报文模板函数数据
     * @return 报文模板函数数据
     */
    @Override
    public List<MocksysFunctionInfo> selectMocksysFunctionInfoList(MocksysFunctionInfo mocksysFunctionInfo)
    {
        return mocksysFunctionInfoMapper.selectMocksysFunctionInfoList(mocksysFunctionInfo);
    }

    /**
     * 新增报文模板函数数据
     * 
     * @param mocksysFunctionInfo 报文模板函数数据
     * @return 结果
     */
    @Override
    public int insertMocksysFunctionInfo(MocksysFunctionInfo mocksysFunctionInfo)
    {
        return mocksysFunctionInfoMapper.insertMocksysFunctionInfo(mocksysFunctionInfo);
    }

    /**
     * 修改报文模板函数数据
     * 
     * @param mocksysFunctionInfo 报文模板函数数据
     * @return 结果
     */
    @Override
    public int updateMocksysFunctionInfo(MocksysFunctionInfo mocksysFunctionInfo)
    {
        return mocksysFunctionInfoMapper.updateMocksysFunctionInfo(mocksysFunctionInfo);
    }

    /**
     * 批量删除报文模板函数数据
     * 
     * @param ids 需要删除的报文模板函数数据主键
     * @return 结果
     */
    @Override
    public int deleteMocksysFunctionInfoByIds(Integer[] ids)
    {
        return mocksysFunctionInfoMapper.deleteMocksysFunctionInfoByIds(ids);
    }

    /**
     * 删除报文模板函数数据信息
     * 
     * @param id 报文模板函数数据主键
     * @return 结果
     */
    @Override
    public int deleteMocksysFunctionInfoById(Integer id)
    {
        return mocksysFunctionInfoMapper.deleteMocksysFunctionInfoById(id);
    }
}

package com.ruoyi.system.service;

import com.ruoyi.system.domain.mock.MocksysFunctionInfo;

import java.util.List;

/**
 * 报文模板函数数据Service接口
 * 
 * @author ruoyi
 * @date 2024-03-08
 */
public interface IMocksysFunctionInfoService 
{
    /**
     * 查询报文模板函数数据
     * 
     * @param id 报文模板函数数据主键
     * @return 报文模板函数数据
     */
    public MocksysFunctionInfo selectMocksysFunctionInfoById(Integer id);

    /**
     * 查询报文模板函数数据列表
     * 
     * @param mocksysFunctionInfo 报文模板函数数据
     * @return 报文模板函数数据集合
     */
    public List<MocksysFunctionInfo> selectMocksysFunctionInfoList(MocksysFunctionInfo mocksysFunctionInfo);

    /**
     * 新增报文模板函数数据
     * 
     * @param mocksysFunctionInfo 报文模板函数数据
     * @return 结果
     */
    public int insertMocksysFunctionInfo(MocksysFunctionInfo mocksysFunctionInfo);

    /**
     * 修改报文模板函数数据
     * 
     * @param mocksysFunctionInfo 报文模板函数数据
     * @return 结果
     */
    public int updateMocksysFunctionInfo(MocksysFunctionInfo mocksysFunctionInfo);

    /**
     * 批量删除报文模板函数数据
     * 
     * @param ids 需要删除的报文模板函数数据主键集合
     * @return 结果
     */
    public int deleteMocksysFunctionInfoByIds(Integer[] ids);

    /**
     * 删除报文模板函数数据信息
     * 
     * @param id 报文模板函数数据主键
     * @return 结果
     */
    public int deleteMocksysFunctionInfoById(Integer id);
}

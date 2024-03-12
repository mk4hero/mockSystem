package com.ruoyi.system.mapper.mock;

import com.ruoyi.system.domain.mock.MocksysFunctionInfo;

import java.util.List;

/**
 * 报文模板函数数据Mapper接口
 * 
 * @author ruoyi
 * @date 2024-03-08
 */
public interface MocksysFunctionInfoMapper 
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
     * 删除报文模板函数数据
     * 
     * @param id 报文模板函数数据主键
     * @return 结果
     */
    public int deleteMocksysFunctionInfoById(Integer id);

    /**
     * 批量删除报文模板函数数据
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMocksysFunctionInfoByIds(Integer[] ids);
}

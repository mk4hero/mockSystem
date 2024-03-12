package com.ruoyi.system.mapper.mock;

import com.ruoyi.system.domain.mock.MocksysTemplateNodeInfo;

import java.util.List;

/**
 * 报文模板数据Mapper接口
 * 
 * @author ruoyi
 * @date 2024-03-06
 */
public interface MocksysTemplateNodeInfoMapper 
{
    /**
     * 查询报文模板数据
     * 
     * @param id 报文模板数据主键
     * @return 报文模板数据
     */
    public MocksysTemplateNodeInfo selectMocksysTemplateNodeInfoById(Integer id);

    /**
     * 查询报文模板数据列表
     * 
     * @param mocksysTemplateNodeInfo 报文模板数据
     * @return 报文模板数据集合
     */
    public List<MocksysTemplateNodeInfo> selectMocksysTemplateNodeInfoList(MocksysTemplateNodeInfo mocksysTemplateNodeInfo);

    /**
     * 新增报文模板数据
     * 
     * @param mocksysTemplateNodeInfo 报文模板数据
     * @return 结果
     */
    public int insertMocksysTemplateNodeInfo(MocksysTemplateNodeInfo mocksysTemplateNodeInfo);

    /**
     * 修改报文模板数据
     * 
     * @param mocksysTemplateNodeInfo 报文模板数据
     * @return 结果
     */
    public int updateMocksysTemplateNodeInfo(MocksysTemplateNodeInfo mocksysTemplateNodeInfo);

    /**
     * 删除报文模板数据
     * 
     * @param id 报文模板数据主键
     * @return 结果
     */
    public int deleteMocksysTemplateNodeInfoById(Integer id);

    /**
     * 批量删除报文模板数据
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMocksysTemplateNodeInfoByIds(Integer[] ids);
}

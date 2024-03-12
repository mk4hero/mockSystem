package com.ruoyi.system.mapper.mock;

import com.ruoyi.system.domain.mock.MocksysMappingInfo;

import java.util.List;

/**
 * 映射信息Mapper接口
 * 
 * @author ruoyi
 * @date 2024-03-06
 */
public interface MocksysMappingInfoMapper 
{
    /**
     * 查询映射信息
     * 
     * @param id 映射信息主键
     * @return 映射信息
     */
    public MocksysMappingInfo selectMocksysMappingInfoById(Integer id);

    /**
     * 查询映射信息列表
     * 
     * @param mocksysMappingInfo 映射信息
     * @return 映射信息集合
     */
    public List<MocksysMappingInfo> selectMocksysMappingInfoList(MocksysMappingInfo mocksysMappingInfo);

    /**
     * 新增映射信息
     * 
     * @param mocksysMappingInfo 映射信息
     * @return 结果
     */
    public int insertMocksysMappingInfo(MocksysMappingInfo mocksysMappingInfo);

    /**
     * 修改映射信息
     * 
     * @param mocksysMappingInfo 映射信息
     * @return 结果
     */
    public int updateMocksysMappingInfo(MocksysMappingInfo mocksysMappingInfo);

    /**
     * 删除映射信息
     * 
     * @param id 映射信息主键
     * @return 结果
     */
    public int deleteMocksysMappingInfoById(Integer id);

    /**
     * 批量删除映射信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMocksysMappingInfoByIds(Integer[] ids);
}

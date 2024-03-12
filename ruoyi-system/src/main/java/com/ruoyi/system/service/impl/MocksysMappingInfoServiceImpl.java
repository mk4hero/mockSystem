package com.ruoyi.system.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.utils.mock.JsonMsgFuncUtils;
import com.ruoyi.system.domain.mock.MocksysMappingInfo;
import com.ruoyi.system.mapper.mock.MocksysMappingInfoDAO;
import com.ruoyi.system.mapper.mock.MocksysMappingInfoMapper;
import com.ruoyi.system.service.IMocksysMappingInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 映射信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-02-27
 */
@Service
public class MocksysMappingInfoServiceImpl implements IMocksysMappingInfoService {

    public static final Logger logger = LoggerFactory.getLogger(MocksysMappingInfoServiceImpl.class);

    @Autowired
    private MocksysMappingInfoMapper mocksysMappingInfoMapper;

    @Autowired
    private MocksysMappingInfoDAO mocksysMappingInfoDAO;

    /**
     * 查询映射信息
     * 
     * @param id 映射信息主键
     * @return 映射信息
     */
    @Override
    public MocksysMappingInfo selectMocksysMappingInfoById(Integer id)
    {
        return mocksysMappingInfoMapper.selectMocksysMappingInfoById(id);
    }

    /**
     * 查询映射信息列表
     * 
     * @param mocksysMappingInfo 映射信息
     * @return 映射信息
     */
    @Override
    public List<MocksysMappingInfo> selectMocksysMappingInfoList(MocksysMappingInfo mocksysMappingInfo)
    {
        return mocksysMappingInfoMapper.selectMocksysMappingInfoList(mocksysMappingInfo);
    }

    /**
     * 新增映射信息
     * 
     * @param mocksysMappingInfo 映射信息
     * @return 结果
     */
    @Override
    public int insertMocksysMappingInfo(MocksysMappingInfo mocksysMappingInfo)
    {
        return mocksysMappingInfoMapper.insertMocksysMappingInfo(mocksysMappingInfo);
    }

    public int insertForList(List<MocksysMappingInfo> list){
        int count = 0;
        for(MocksysMappingInfo item : list){
            count += mocksysMappingInfoMapper.insertMocksysMappingInfo(item);
        }
        return count;
    }

    /**
     * 修改映射信息
     * 
     * @param mocksysMappingInfo 映射信息
     * @return 结果
     */
    @Override
    public int updateMocksysMappingInfo(MocksysMappingInfo mocksysMappingInfo)
    {
        return mocksysMappingInfoMapper.updateMocksysMappingInfo(mocksysMappingInfo);
    }

    /**
     * 批量删除映射信息
     * 
     * @param ids 需要删除的映射信息主键
     * @return 结果
     */
    @Override
    public int deleteMocksysMappingInfoByIds(Integer[] ids)
    {
        return mocksysMappingInfoMapper.deleteMocksysMappingInfoByIds(ids);
    }

    /**
     * 删除映射信息信息
     * 
     * @param id 映射信息主键
     * @return 结果
     */
    @Override
    public int deleteMocksysMappingInfoById(Integer id)
    {
        return mocksysMappingInfoMapper.deleteMocksysMappingInfoById(id);
    }
}

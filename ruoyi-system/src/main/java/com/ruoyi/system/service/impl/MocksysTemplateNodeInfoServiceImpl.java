package com.ruoyi.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.ruoyi.system.domain.mock.MocksysTemplateNodeInfo;
import com.ruoyi.system.mapper.mock.MocksysTemplateNodeInfoDAO;
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

    @Autowired
    private MocksysTemplateNodeInfoDAO mocksysTemplateNodeInfoDAO;

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
        int count = mocksysTemplateNodeInfoMapper.updateMocksysTemplateNodeInfo(mocksysTemplateNodeInfo);
        if(!"flStr".equals(mocksysTemplateNodeInfo.getMsgType())){
            if(null != mocksysTemplateNodeInfo.getSign() && !"".equals(mocksysTemplateNodeInfo.getSign())){
                int parentId = mocksysTemplateNodeInfo.getId();
                MocksysTemplateNodeInfo newObj = new MocksysTemplateNodeInfo();
                newObj.setBusiCode(mocksysTemplateNodeInfo.getBusiCode());
                newObj.setMsgType(mocksysTemplateNodeInfo.getMsgType());
                List<MocksysTemplateNodeInfo> newList = mocksysTemplateNodeInfoMapper.selectMocksysTemplateNodeInfoList(newObj);

                /** 我需要一个字符串数组，以及一个状态 */
                String sign = mocksysTemplateNodeInfo.getSign();
                int loopCount = mocksysTemplateNodeInfo.getLoopCount();
                if("0".equals(sign)){
                    loopCount = 0;
                }
                List<Integer> childrenIds = new ArrayList<Integer>();
                selectChildrenId(parentId, newList, childrenIds);

                count += mocksysTemplateNodeInfoMapper.updateChildrenSignAndLoopCount(childrenIds, sign, loopCount);

            }
        }
        return count;
    }

    private void selectChildrenId(int parentId, List<MocksysTemplateNodeInfo> targetList, List<Integer> childrenIds){
        // 遍历节点列表
        for (MocksysTemplateNodeInfo item : targetList) {
            // 如果当前节点的父节点 ID 等于给定的父节点 ID，则将其添加到子节点列表中
            if (item.getParentId() == parentId) {
                childrenIds.add(item.getId());
                // 如果当前节点是非叶子节点，则递归查找其子节点，并将子节点添加到子节点列表中
                if (!item.getIsSonNode()) {
                    selectChildrenId(item.getId(), targetList, childrenIds);
                }
            }
        }
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

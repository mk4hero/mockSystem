package com.ruoyi.system.mapper.mock;

import com.ruoyi.system.domain.mock.MocksysTemplateNodeInfo;
import com.ruoyi.system.domain.mock.MocksysTemplateNodeInfoExample;
import org.springframework.stereotype.Repository;

/**
 * MocksysTemplateNodeInfoDAO继承基类
 */
@Repository
public interface MocksysTemplateNodeInfoDAO extends MyBatisBaseDao<MocksysTemplateNodeInfo, Integer, MocksysTemplateNodeInfoExample> {
}
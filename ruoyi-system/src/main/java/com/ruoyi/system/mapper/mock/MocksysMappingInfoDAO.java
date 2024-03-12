package com.ruoyi.system.mapper.mock;

import com.ruoyi.system.domain.mock.MocksysMappingInfo;
import com.ruoyi.system.domain.mock.MocksysMappingInfoExample;
import org.springframework.stereotype.Repository;

/**
 * MocksysMappingInfoDAO继承基类
 */
@Repository
public interface MocksysMappingInfoDAO extends MyBatisBaseDao<MocksysMappingInfo, Integer, MocksysMappingInfoExample> {
}
package com.ruoyi.system.mapper.mock;

import com.ruoyi.system.domain.mock.MocksysMessagesInfo;
import com.ruoyi.system.domain.mock.MocksysMessagesInfoExample;
import org.springframework.stereotype.Repository;

/**
 * MocksysMessagesInfoDAO继承基类
 */
@Repository
public interface MocksysMessagesInfoDAO extends MyBatisBaseDao<MocksysMessagesInfo, String, MocksysMessagesInfoExample> {
}
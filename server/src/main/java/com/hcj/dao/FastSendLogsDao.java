package com.hcj.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hcj.entity.FastSendLogs;
import org.springframework.stereotype.Repository;

/**
 * FastSendLogsDao
 *
 * @author hcj
 * @date 2023-06-16
 */
@Repository("fastSendLogsDao")
public interface FastSendLogsDao extends BaseMapper<FastSendLogs> {
}

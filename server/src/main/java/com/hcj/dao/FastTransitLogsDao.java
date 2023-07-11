package com.hcj.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hcj.entity.FastTransitLogs;
import org.springframework.stereotype.Repository;

/**
 * FastTransitLogsDao
 *
 * @author hcj
 * @date 2023-06-16
 */
@Repository("fastTransitLogsDao")
public interface FastTransitLogsDao extends BaseMapper<FastTransitLogs> {
}

package com.hcj.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hcj.entity.FastPacksLogs;
import org.springframework.stereotype.Repository;

/**
 * FastPacksLogsDao
 *
 * @author hcj
 * @date 2023-06-16
 */
@Repository("fastPacksLogsDao")
public interface FastPacksLogsDao extends BaseMapper<FastPacksLogs> {
}

package com.hcj.service;

import com.hcj.comm.BaseService;
import com.hcj.comm.PageInfo;
import com.hcj.entity.FastPacksLogs;

/**
 * IFastPacksLogsService
 *
 * @author hcj
 * @date 2023-06-16
 */
public interface IFastPacksLogsService extends BaseService<FastPacksLogs,String> {
    // 根据分页查询及搜索条件查询所有快件信息
    PageInfo findFastPacksLogsByPage(Long pageIndex, Long pageSize, FastPacksLogs fastPacksLogs);
}

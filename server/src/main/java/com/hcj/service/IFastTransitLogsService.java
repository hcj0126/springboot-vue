package com.hcj.service;

import com.hcj.comm.BaseService;
import com.hcj.comm.PageInfo;
import com.hcj.entity.FastTransitLogs;

/**
 * IFastTransitLogsService
 *
 * @author hcj
 * @date 2023-06-16
 */
public interface IFastTransitLogsService extends BaseService<FastTransitLogs,String> {
    // 根据分页查询及搜索条件查询所有运输记录
    PageInfo findFastTransitLogsByPage(Long pageIndex, Long pageSize, FastTransitLogs fastTransitLogs);
}

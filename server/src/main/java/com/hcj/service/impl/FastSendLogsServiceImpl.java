package com.hcj.service.impl;

import com.hcj.dao.FastSendLogsDao;
import com.hcj.entity.FastSendLogs;
import com.hcj.service.IFastSendLogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * FastSendLogsServiceImpl
 *
 * @author hcj
 * @date 2023-06-16
 */
@Service("fastSendLogsService")
public class FastSendLogsServiceImpl implements IFastSendLogsService {

    @Autowired
    private FastSendLogsDao fastSendLogsDao;

    @Override
    public void add(FastSendLogs fastSendLogs) {

    }

    @Override
    public void update(FastSendLogs fastSendLogs) {

    }

    @Override
    public void delete(FastSendLogs fastSendLogs) {

    }

    @Override
    public FastSendLogs getOne(String s) {
        return null;
    }
}

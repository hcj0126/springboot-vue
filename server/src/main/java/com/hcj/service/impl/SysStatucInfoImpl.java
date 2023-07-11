package com.hcj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hcj.comm.PackCode;
import com.hcj.dao.FastPacksLogsDao;
import com.hcj.dao.FastServicePointDao;
import com.hcj.entity.FastPacksLogs;
import com.hcj.service.ISysStatucInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * SysStatucInfoImpl
 *
 * @author hcj
 * @date 2023-06-17
 */
@Service("sysStatucInfo")
public class SysStatucInfoImpl implements ISysStatucInfo {

    @Autowired
    private FastServicePointDao fastServicePointDao;

    @Autowired
    private FastPacksLogsDao fastPacksLogsDao;

    /**
     * 统计当前系统累计收到的快件数量
     */
    @Override
    @Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
    public Integer getCollectPackTotal() {
        Integer collectPackTotal = fastPacksLogsDao.selectCount(null);
        return collectPackTotal;
    }

    /**
     * 统计当前公司营业网点数目
     */
    @Override
    @Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
    public Integer getServicePointTotal() {
        Integer servicePointTotal = fastServicePointDao.selectCount(null);
        return servicePointTotal;
    }

    /**
     * 统计已成功签收的快件数量
     */
    @Override
    @Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
    public Integer getSuccessGainPackTotal() {
        QueryWrapper<FastPacksLogs> qw = new QueryWrapper<>();
        // 要匹配status=4签收下
        qw.eq("status", PackCode.PACK_STATUS_GAIN);
        Integer successGainPackTotal = fastPacksLogsDao.selectCount(qw);
        return successGainPackTotal;
    }
}

package com.hcj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hcj.comm.PageInfo;
import com.hcj.dao.FastPacksLogsDao;
import com.hcj.dao.FastServicePointDao;
import com.hcj.dao.FastTransitLogsDao;
import com.hcj.entity.FastPacksLogs;
import com.hcj.entity.FastServicePoint;
import com.hcj.entity.FastTransitLogs;
import com.hcj.service.IFastTransitLogsService;
import com.hcj.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * FastTransitLogsServiceImpl
 *
 * @author hcj
 * @date 2023-06-16
 */
@Service("fastTransitLogsService")
public class FastTransitLogsServiceImpl implements IFastTransitLogsService {

    @Autowired
    private FastTransitLogsDao fastTransitLogsDao;

    @Autowired
    private FastPacksLogsDao fastPacksLogsDao;

    @Autowired
    private FastServicePointDao fastServicePointDao;

    @Override
    public void add(FastTransitLogs fastTransitLogs) {

    }

    @Override
    public void update(FastTransitLogs fastTransitLogs) {

    }

    @Override
    public void delete(FastTransitLogs fastTransitLogs) {

    }

    @Override
    public FastTransitLogs getOne(String s) {
        return null;
    }

    // 根据分页查询及搜索条件查询所有运输记录
    @Override
    @Transactional(readOnly=true, propagation= Propagation.SUPPORTS)
    public PageInfo findFastTransitLogsByPage(Long pageIndex, Long pageSize, FastTransitLogs fastTransitLogs) {
        QueryWrapper<FastTransitLogs> qw = new QueryWrapper<>();
        // 判断网点id不为空
        if(StringUtils.isNotNullOrEmpty(fastTransitLogs.getSendPoint())){
            qw.like("send_point",fastTransitLogs.getSendPoint());
        }
        // 模糊查询一个条件：快件编号：fast_pack_id
        if(StringUtils.isNotNullOrEmpty(fastTransitLogs.getFastPackId())){
            qw.like("fast_pack_id",fastTransitLogs.getFastPackId());
        }

        // 查询根据运出时间的降序
        qw.orderByDesc("send_time");
        Page<FastTransitLogs> p = fastTransitLogsDao.selectPage(new Page<>(pageIndex,pageSize),qw);
        return parsePage(p);
    }

    /**
     * 转换分页查询结果
     */
    public PageInfo parsePage(Page<FastTransitLogs> p){
        // 创建分页对象
        PageInfo pageInfo = new PageInfo();
        pageInfo.setPageIndex(p.getCurrent());
        pageInfo.setPageSize(p.getSize());


        Long pageTotal = (p.getRecords().size()%p.getSize())==0?(p.getRecords().size()/p.getSize()):(p.getRecords().size()/p.getSize()+1);
        pageInfo.setPageTotal(pageTotal);

        pageInfo.setCount(p.getRecords().size());

        List<Map<String,Object>> list = new ArrayList<>();
        // 遍历用户列表
        for (FastTransitLogs fastTransitLogs : p.getRecords()) {
            Map<String,Object> map = new HashMap<>();
            map.put("id",fastTransitLogs.getId());
            map.put("sendTime",fastTransitLogs.getSendTime());
            // 存入快件编号，根据fast_pack_id到快件表中查询
            FastPacksLogs fastPacksLogs=fastPacksLogsDao.selectById(fastTransitLogs.getFastPackId());
            map.put("fastPackId",fastPacksLogs.getId());
            // 上一网点sendPointName，根据send_point到fast_service_point表中查询
            if(StringUtils.isNotNullOrEmpty(fastTransitLogs.getSendPoint())){
                FastServicePoint fastServicePoint = fastServicePointDao.selectById(fastTransitLogs.getSendPoint());
                map.put("sendPoint",fastServicePoint.getId());
                map.put("sendPointName",fastServicePoint.getName());
            }
            // 下一网点nextPointName，根据next_point到fast_service_point表中查询
            if(StringUtils.isNotNullOrEmpty(fastTransitLogs.getNextPoint())){
                FastServicePoint fastServicePoint = fastServicePointDao.selectById(fastTransitLogs.getNextPoint());
                map.put("nextPoint",fastServicePoint.getId());
                map.put("nextPointName",fastServicePoint.getName());
            }
            // 快件信息 寄件人姓名、寄件人地址、寄件人电话
            map.put("packSendName",fastPacksLogs.getSendName());
            map.put("packSendPhone",fastPacksLogs.getSendPhone());
            map.put("packSendAddress",fastPacksLogs.getSendAddress());
            // 快件信息 收件人姓名、收件人地址、收件人电话
            map.put("packGainName",fastPacksLogs.getGainName());
            map.put("packGainAddress",fastPacksLogs.getGainAddress());
            map.put("packGainPhone",fastPacksLogs.getGainPhone());
            list.add(map);
        }
        pageInfo.setData(list);
        return pageInfo;
    }
}

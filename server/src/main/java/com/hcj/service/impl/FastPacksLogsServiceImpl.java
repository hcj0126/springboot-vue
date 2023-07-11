package com.hcj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hcj.comm.PageInfo;
import com.hcj.dao.FastPacksLogsDao;
import com.hcj.dao.FastServicePointDao;
import com.hcj.entity.FastPacksLogs;
import com.hcj.entity.FastServicePoint;
import com.hcj.service.IFastPacksLogsService;
import com.hcj.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * FastPacksLogsServiceImpl
 *
 * @author hcj
 * @date 2023-06-16
 */
@Service("fastPacksLogsService")
public class FastPacksLogsServiceImpl implements IFastPacksLogsService {

    @Autowired
    private FastPacksLogsDao fastPacksLogsDao;
    @Autowired
    private FastServicePointDao fastServicePointDao;

    //  分页查询
    @Override
    @Transactional(readOnly=true, propagation= Propagation.SUPPORTS)
    public PageInfo findFastPacksLogsByPage(Long pageIndex, Long pageSize, FastPacksLogs fastPacksLogs) {
        QueryWrapper<FastPacksLogs> qw = new QueryWrapper<>();
        // 判断网点id不为空
        if(StringUtils.isNotNullOrEmpty(fastPacksLogs.getCollectPoint())){
            qw.like("collect_point",fastPacksLogs.getCollectPoint());
        }
        // 模糊查询三个条件：寄件地址：send_address,收件人姓名:gain_name,收件人地址:gain_address
        if(StringUtils.isNotNullOrEmpty(fastPacksLogs.getSendAddress())){
            qw.like("send_address",fastPacksLogs.getSendAddress());
        }
        if(StringUtils.isNotNullOrEmpty(fastPacksLogs.getGainName())){
            qw.like("gain_name",fastPacksLogs.getGainName());
        }
        if(StringUtils.isNotNullOrEmpty(fastPacksLogs.getGainAddress())){
            qw.like("gain_address",fastPacksLogs.getGainAddress());
        }
        // 查询根据揽收时间的降序
        qw.orderByDesc("collect_time");
        Page<FastPacksLogs> p = fastPacksLogsDao.selectPage(new Page<>(pageIndex,pageSize),qw);
        // 转换分页查询结果
        return parsePage(p);
    }
    /**
     * 转换分页查询结果
     */
    public PageInfo parsePage(Page<FastPacksLogs> p){
        // 创建分页对象
        PageInfo pageInfo = new PageInfo();
        pageInfo.setPageIndex(p.getCurrent());
        pageInfo.setPageSize(p.getSize());


        Long pageTotal = (p.getRecords().size()%p.getSize())==0?(p.getRecords().size()/p.getSize()):(p.getRecords().size()/p.getSize()+1);
        pageInfo.setPageTotal(pageTotal);

        pageInfo.setCount(p.getRecords().size());

        List<Map<String,Object>> list = new ArrayList<>();
        // 遍历用户列表
        for (FastPacksLogs fastPacksLogs : p.getRecords()) {
            Map<String,Object> map = new HashMap<>();
            map.put("id",fastPacksLogs.getId());
            map.put("collectTime",fastPacksLogs.getCollectTime()); // 揽收时间
            map.put("payTotal",fastPacksLogs.getPayTotal());
            map.put("sendName",fastPacksLogs.getSendName());
            map.put("sendPhone",fastPacksLogs.getSendPhone());
            map.put("sendAddress",fastPacksLogs.getSendAddress());
            map.put("sendTime",fastPacksLogs.getSendTime()); // 寄件时间
            map.put("gainName",fastPacksLogs.getGainName());
            map.put("gainAddress",fastPacksLogs.getGainAddress());
            map.put("gainPhone",fastPacksLogs.getGainPhone());
            map.put("status",fastPacksLogs.getStatus()); // 快件状态
            // 根据揽收网点collect_point去到网点信息表中查询
            if(StringUtils.isNotNullOrEmpty(fastPacksLogs.getCollectPoint())){
                FastServicePoint fastServicePoint = fastServicePointDao.selectById(fastPacksLogs.getCollectPoint());
                map.put("collectPointId",fastServicePoint.getId());
                map.put("collectPointName",fastServicePoint.getName());
            }else{
                map.put("collectPointId",fastPacksLogs.getCollectPoint());
            }
            list.add(map);
        }
        pageInfo.setData(list);
        return pageInfo;
    }

    @Override
    public void add(FastPacksLogs fastPacksLogs) {

    }

    @Override
    public void update(FastPacksLogs fastPacksLogs) {

    }

    @Override
    public void delete(FastPacksLogs fastPacksLogs) {

    }

    @Override
    public FastPacksLogs getOne(String s) {
        return null;
    }
}

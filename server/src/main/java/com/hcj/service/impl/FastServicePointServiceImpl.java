package com.hcj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hcj.comm.PageInfo;
import com.hcj.dao.*;
import com.hcj.entity.*;
import com.hcj.service.IFastServicePointService;
import com.hcj.util.IDUtils;
import com.hcj.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * FastServicePointServiceImpl
 *
 * @author hcj
 * @date 2023-06-15
 */
@Service("servicePointService")
public class FastServicePointServiceImpl implements IFastServicePointService {

    @Autowired
    private FastServicePointDao fastServicePointDao;

    @Autowired
    private FastUsersDao fastUsersDao;

    @Autowired
    private FastEmpsDao fastEmpsDao;

    @Autowired
    private FastSendLogsDao fastSendLogsDao;

    @Autowired
    private FastPacksLogsDao fastPacksLogsDao;

    @Autowired
    private FastTransitLogsDao fastTransitLogsDao;

    // 查询所有网点信息
    @Override
    @Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
    public List<FastServicePoint> findFastServicePointAll() {
        QueryWrapper<FastServicePoint> qw = new QueryWrapper<>();
        List<FastServicePoint> list = fastServicePointDao.selectList(qw);
        return list;
    }

    // 根据分页查询及搜索条件查询所有网点信息
    @Override
    @Transactional(readOnly=true, propagation=Propagation.SUPPORTS)
    public PageInfo findFastServicePointByPage(Long pageIndex, Long pageSize, String name) {
        QueryWrapper<FastServicePoint> qw = new QueryWrapper<>();
        // 判断网点名不为空
        if(StringUtils.isNotNullOrEmpty(name)){
            qw.like("name",name);
        }
        // 查询根据网点建立时间的降序
        qw.orderByDesc("create_time");
        Page<FastServicePoint> p = fastServicePointDao.selectPage(new Page<FastServicePoint>(pageIndex,pageSize),qw);
        return parsePage(p);
    }
    /**
     * 转换分页查询结果
    */
    public PageInfo parsePage(Page p){
        // 创建分页对象
        PageInfo pageInfo = new PageInfo();
        pageInfo.setPageIndex(p.getCurrent());
        pageInfo.setPageSize(p.getSize());


        Long pageTotal = (p.getRecords().size()%p.getSize())==0?(p.getRecords().size()/p.getSize()):(p.getRecords().size()/p.getSize()+1);
        pageInfo.setPageTotal(pageTotal);

        pageInfo.setCount(p.getRecords().size());
        pageInfo.setData(p.getRecords());
        return pageInfo;
    }

    @Override
    @Transactional
    public void add(FastServicePoint fastServicePoint) {
        fastServicePoint.setId(IDUtils.makeIDByCurrent());
        fastServicePoint.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        fastServicePointDao.insert(fastServicePoint);
    }

    @Override
    public void update(FastServicePoint fastServicePoint) {
        fastServicePointDao.updateById(fastServicePoint);
    }

    @Override
    public void delete(FastServicePoint fastServicePoint) {
        // 先根据service_id查询此网点下所有的用户
        QueryWrapper<FastUsers> qwUsers = new QueryWrapper<>();
        qwUsers.eq("service_id",fastServicePoint.getId());
        List<FastUsers> usersList = fastUsersDao.selectList(qwUsers);
        // 遍历usersList，删除此网点下是所有用户
        for (FastUsers user: usersList) {
            // 遍历一个删除一个
            QueryWrapper<FastEmps> qwEmps = new QueryWrapper<>();
            qwEmps.eq("id",user.getId());
            fastEmpsDao.delete(qwEmps);
            // 重置此员工的派送记录
            QueryWrapper<FastSendLogs> qwSendLogs = new QueryWrapper<>();
            qwSendLogs.eq("emp_id",user.getId());
            // 获取所有的派送记录列表
            List<FastSendLogs> sendLogsList = fastSendLogsDao.selectList(qwSendLogs);
            // 遍历所有的派送记录列表，删除
            for (FastSendLogs sendLogs : sendLogsList) {
                sendLogs.setEmpId(null);
                // 逻辑删除  派送记录不能直接删除
                fastSendLogsDao.updateById(sendLogs);
            }
            // 删除用户
            fastUsersDao.delete(qwUsers);
        }
        // 重置相关网点的配送记录
        /*QueryWrapper<FastSendLogs> qwSend= new QueryWrapper<>();
        qwSend.eq("service_id",fastServicePoint.getId());
        List<FastSendLogs> sendList = fastSendLogsDao.selectList(qwSend);
        for (FastSendLogs send : sendList) {
            send.setServiceId(null);
            fastSendLogsDao.updateById(send);
        }*/

        // 重置网点揽收信息
        QueryWrapper<FastPacksLogs> qwPack= new QueryWrapper<>();
        qwPack.eq("collect_point",fastServicePoint.getId());
        List<FastPacksLogs> packList = fastPacksLogsDao.selectList(qwPack);
        for (FastPacksLogs pack : packList) {
            pack.setCollectPoint(null);
            fastPacksLogsDao.updateById(pack);
        }

        // 重置运输记录
        QueryWrapper<FastTransitLogs> qwTransit= new QueryWrapper<>();
        qwTransit.eq("send_point",fastServicePoint.getId());
        List<FastTransitLogs> transitList = fastTransitLogsDao.selectList(qwTransit);
        for (FastTransitLogs sendPoint : transitList) {
            sendPoint.setSendPoint(null);
            fastTransitLogsDao.updateById(sendPoint);
        }
        // 重置下一个网点
        QueryWrapper<FastTransitLogs> qwNextTransit= new QueryWrapper<>();
        qwNextTransit.eq("next_point",fastServicePoint.getId());
        List<FastTransitLogs> nextTransitList = fastTransitLogsDao.selectList(qwNextTransit);
        for (FastTransitLogs nextPoint : nextTransitList) {
            nextPoint.setNextPoint(null);
            fastTransitLogsDao.updateById(nextPoint);
        }
        // 删除网点信息
        fastServicePointDao.deleteById(fastServicePoint);
    }

    @Override
    public FastServicePoint getOne(String id) {
        FastServicePoint fastServicePoint = fastServicePointDao.selectById(id);
        return fastServicePoint;
    }
}

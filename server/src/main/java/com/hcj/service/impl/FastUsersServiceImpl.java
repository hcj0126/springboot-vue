package com.hcj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hcj.comm.PageInfo;
import com.hcj.dao.FastEmpsDao;
import com.hcj.dao.FastServicePointDao;
import com.hcj.dao.FastUsersDao;
import com.hcj.entity.FastEmps;
import com.hcj.entity.FastServicePoint;
import com.hcj.entity.FastUsers;
import com.hcj.service.IFastUsersService;
import com.hcj.util.IDUtils;
import com.hcj.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * FastUsersServiceImpl
 *
 * @author hcj
 * @date 2023-06-14
 */
@Service("fastUsersService")
public class FastUsersServiceImpl implements IFastUsersService {

    // 依赖注入
    @Autowired
    private FastUsersDao fastUsersDao;

    @Autowired
    private FastServicePointDao fastServicePointDao;

    @Autowired
    private FastEmpsDao fastEmpsDao;

    @Override
    @Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
    public FastUsers findFastUsersByUserName(String userName) {
        // 创建QueryWrapper对象
        QueryWrapper<FastUsers> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name",userName);
        // 调用dao
        FastUsers fastUsers = fastUsersDao.selectOne(queryWrapper);
        return fastUsers;
    }

    @Override
    @Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
    public PageInfo findFastUsersByPage(Long pageIndex, Long pageSize, FastUsers fastUsers) {
        QueryWrapper<FastUsers> qw = new QueryWrapper<>();
        // 判断网点id不为空
        if(StringUtils.isNotNullOrEmpty(fastUsers.getServiceId())){
            qw.like("service_id",fastUsers.getServiceId());
        }
        // 判断用户名不为空
        if(StringUtils.isNotNullOrEmpty(fastUsers.getUserName())){
            qw.like("user_name",fastUsers.getUserName());
        }
        // 查询根据网点建立时间的降序
        qw.orderByDesc("create_time");
        Page<FastUsers> p = fastUsersDao.selectPage(new Page<FastUsers>(pageIndex,pageSize),qw);
        return parsePage(p);
    }

    /**
     * 转换分页查询结果
     */
    public PageInfo parsePage(Page<FastUsers> p){
        // 创建分页对象
        PageInfo pageInfo = new PageInfo();
        pageInfo.setPageIndex(p.getCurrent());
        pageInfo.setPageSize(p.getSize());


        Long pageTotal = (p.getRecords().size()%p.getSize())==0?(p.getRecords().size()/p.getSize()):(p.getRecords().size()/p.getSize()+1);
        pageInfo.setPageTotal(pageTotal);

        pageInfo.setCount(p.getRecords().size());

        List<Map<String,Object>> list = new ArrayList<>();
        // 遍历用户列表
        for (FastUsers fastUsers : p.getRecords()) {
            Map<String,Object> map = new HashMap<>();
            map.put("id",fastUsers.getId());
            map.put("userName",fastUsers.getUserName());
            map.put("type",fastUsers.getType());
            map.put("createTime",fastUsers.getCreateTime());
            // 根据service_id去网点表中查询
            if(StringUtils.isNotNullOrEmpty(fastUsers.getServiceId())){
                FastServicePoint fastServicePoint = fastServicePointDao.selectById(fastUsers.getServiceId());
                map.put("pointId",fastServicePoint.getId());
                map.put("pointName",fastServicePoint.getName());
            }
            // 添加相关信息(去emp表中查询)
            FastEmps fastEmps = fastEmpsDao.selectById(fastUsers.getId());
            if(fastEmps!=null){
                map.put("empInfo",fastEmps);
            }
            list.add(map);
        }
        pageInfo.setData(list);
        return pageInfo;
    }


    @Override
    public void add(FastUsers fastUsers) {
        // 添加id和create_time
        fastUsers.setId(IDUtils.makeIDByCurrent());
        fastUsers.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        fastUsersDao.insert(fastUsers);
    }

    @Override
    public void update(FastUsers fastUsers) {
        fastUsersDao.updateById(fastUsers);
    }

    @Override
    public void delete(FastUsers fastUsers) {

    }

    @Override
    public FastUsers getOne(String s) {
        FastUsers fastUsers = fastUsersDao.selectById(s);
        return fastUsers;
    }
}

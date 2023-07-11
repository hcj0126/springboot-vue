package com.hcj.service;

import com.hcj.comm.BaseService;
import com.hcj.comm.PageInfo;
import com.hcj.entity.FastUsers;

/**
 * IFastUsersService
 *
 * @author hcj
 * @date 2023-06-14
 */
public interface IFastUsersService extends BaseService<FastUsers,String> {

    /**
     * 根据用户名查询一条用户信息
    */
    FastUsers findFastUsersByUserName(String userName);

    // 根据分页查询及搜索条件查询所有用户信息
    PageInfo findFastUsersByPage(Long pageIndex, Long pageSize, FastUsers fastUsers);
}

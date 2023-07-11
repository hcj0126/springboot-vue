package com.hcj.service;

import com.hcj.comm.BaseService;
import com.hcj.comm.PageInfo;
import com.hcj.entity.FastServicePoint;

import java.util.List;

/**
 * IFastServicePointService
 *
 * @author hcj
 * @date 2023-06-15
 */
public interface IFastServicePointService extends BaseService<FastServicePoint,String> {

    // 查询所有网点信息
    List<FastServicePoint> findFastServicePointAll();

    // 根据分页查询及搜索条件查询所有网点信息
    PageInfo findFastServicePointByPage(Long pageIndex, Long pageSize, String name);

}

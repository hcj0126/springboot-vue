package com.hcj.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hcj.entity.FastUsers;
import org.springframework.stereotype.Repository;

/**
 * FastUsersDao
 *
 * @author hcj
 * @date 2023-06-14
 */
@Repository("fastUsersDao")
public interface FastUsersDao extends BaseMapper<FastUsers> {
}

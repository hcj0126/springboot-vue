package com.hcj.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hcj.entity.FastEmps;
import org.springframework.stereotype.Repository;

/**
 * FastEmpsDao
 *
 * @author hcj
 * @date 2023-06-14
 */
@Repository("fastEmpsDao")
public interface FastEmpsDao extends BaseMapper<FastEmps> {
}

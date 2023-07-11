package com.hcj.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hcj.entity.FastServicePoint;
import org.springframework.stereotype.Repository;

/**
 * FastServicePointDap
 *
 * @author hcj
 * @date 2023-06-15
 */
@Repository("fastServicePointDao")
public interface FastServicePointDao extends BaseMapper<FastServicePoint> {

}

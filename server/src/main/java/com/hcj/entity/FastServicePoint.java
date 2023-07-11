package com.hcj.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hcj.comm.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * FastServicePoint
 *  网点表
 * @author hcj
 * @date 2023-06-15
 */

@TableName(value = "fast_service_point")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FastServicePoint extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     *  网点id
     */
    @TableId(value = "id")
    private String id;

    /**
     *  网点名称
     */
    @TableField(value = "name")
    private String name;

    /**
     *  网点描述
     */
    @TableField(value = "comm")
    private String comm;

    /**
     *  网点建立时间
     */
    @TableField(value = "create_time")
    private String createTime;
}

package com.hcj.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hcj.comm.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 数据实体类
 * 运输记录
 */
@TableName(value = "fast_transit_logs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FastTransitLogs extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 记录ID
	 */
	@TableId(value = "id")
	private String id;

	/**
	 * 运出时间
	 */
	@TableField(value = "send_time")
	private String sendTime;

	/**
	 * 运出网点
	 */
	@TableField(value = "send_point")
	private String sendPoint;

	/**
	 * 下一站网点
	 */
	@TableField(value = "next_point")
	private String nextPoint;

	/**
	 * 快件ID
	 */
	@TableField(value = "fast_pack_id")
	private String fastPackId;

	/**
	 * 处理员工ID
	 */
	@TableField(value = "emp_id")
	private String empId;

}

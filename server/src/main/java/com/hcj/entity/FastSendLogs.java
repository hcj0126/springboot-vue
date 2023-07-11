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
 * 派送记录
 */
@TableName(value = "fast_send_logs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FastSendLogs extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 记录ID
	 */
	@TableId(value = "id")
	private String id;

	/**
	 * 员工ID
	 */
	@TableField(value = "emp_id")
	private String empId;

	/**
	 * 派送时间
	 */
	@TableField(value = "send_time")
	private String sendTime;

	/**
	 * 收件时间
	 */
	@TableField(value = "gain_time")
	private String gainTime;

	/**
	 * 快件ID
	 */
	@TableField(value = "pack_id")
	private String packId;

	/**
	 * 网点ID
	 */
	@TableField(value = "service_id")
	private String serviceId;

}

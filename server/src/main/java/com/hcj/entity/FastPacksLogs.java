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
 * 快件记录
 */
@TableName(value = "fast_packs_logs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FastPacksLogs extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 记录ID
	 */
	@TableId(value = "id")
	private String id;

	/**
	 * 寄件人姓名
	 */
	@TableField(value = "send_name")
	private String sendName;

	/**
	 * 寄件人电话
	 */
	@TableField(value = "send_phone")
	private String sendPhone;

	/**
	 * 寄送地址
	 */
	@TableField(value = "send_address")
	private String sendAddress;

	/**
	 * 寄送时间
	 */
	@TableField(value = "send_time")
	private String sendTime;

	/**
	 * 收货人姓名
	 */
	@TableField(value = "gain_name")
	private String gainName;

	/**
	 * 收获人地址
	 */
	@TableField(value = "gain_address")
	private String gainAddress;

	/**
	 * 收件人联系电话
	 */
	@TableField(value = "gain_phone")
	private String gainPhone;

	/**
	 * 揽收时间
	 */
	@TableField(value = "collect_time")
	private String collectTime;

	/**
	 * 揽收网点
	 */
	@TableField(value = "collect_point")
	private String collectPoint;

	/**
	 * 支付费用
	 */
	@TableField(value = "pay_total")
	private Double payTotal;

	/**
	 * 快递状态 0:下单 1:揽收 2:运输 3:派送 4:签收
	 */
	@TableField(value = "status")
	private Integer status;

}

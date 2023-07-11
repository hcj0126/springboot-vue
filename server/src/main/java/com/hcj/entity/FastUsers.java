package com.hcj.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hcj.comm.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * FastUsers
 *   实体类对应表fast_users
 *   用户类
 * @author hcj
 * @date 2023-06-14
 */
@TableName(value = "fast_users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FastUsers extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 记录ID
     */
    @TableId(value = "id")
    private String id;

    /**
     * 用户账号
     */
    @TableField(value = "user_name")
    private String userName;

    /**
     * 用户密码
     */
    @TableField(value = "pass_word")
    private String passWord;

    /**
     * 用户身份
     * 0:系统管理员  1:网点管理员  2:网点员工
     */
    @TableField(value = "type")
    private Integer type;

    /**
     * 注册时间
     */
    @TableField(value = "create_time")
    private String createTime;

    /**
     * 网点ID
     */
    @TableField(value = "service_id")
    private String serviceId;

}

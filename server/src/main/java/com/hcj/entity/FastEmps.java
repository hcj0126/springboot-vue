package com.hcj.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hcj.comm.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * FastEmps
 *
 * @author hcj
 */
@TableName(value = "fast_emps")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FastEmps extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 记录ID
     */
    @TableId(value = "id")
    private String id;

    /**
     * 员工姓名
     */
    @TableField(value = "name")
    private String name;

    /**
     * 员工性别
     */
    @TableField(value = "gender")
    private String gender;

    /**
     * 员工学历
     */
    @TableField(value = "record")
    private String record;

    /**
     * 员工年龄
     */
    @TableField(value = "age")
    private Integer age;

    /**
     * 员工职位
     */
    @TableField(value = "job")
    private String job;
}

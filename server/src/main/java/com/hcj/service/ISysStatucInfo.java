package com.hcj.service;

/**
 * SysStatucInfo
 *  系统首页统计数据
 * @author hcj
 * @date 2023-06-17
 */
public interface ISysStatucInfo {
    /**
     * 统计当前系统累计收到的快件数量
    */
    Integer getCollectPackTotal();

    /**
     * 统计当前公司营业网点数目
     */
    Integer getServicePointTotal();
    /**
     * 统计已成功签收的快件数量
     */
    Integer getSuccessGainPackTotal();
}

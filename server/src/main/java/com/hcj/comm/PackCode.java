package com.hcj.comm;

public class PackCode {
    /**
     * 定义常量状态码，用于规范
     */

    /** 用户下单 */
    public static final Integer PACK_STATUS_ORDER = 0;

    /** 网点揽收 */
    public static final Integer PACK_STATUS_COLLECT = 1;

    /** 快件运输 */
    public static final Integer PACK_STATUS_TRAN = 2;

    /** 快件派送 */
    public static final Integer PACK_STATUS_SEND = 3;

    /** 快件签收 */
    public static final Integer PACK_STATUS_GAIN = 4;
}

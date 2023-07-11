package com.hcj.util;

import java.util.UUID;

/**
 * IDUtils
 *  生成id的工具类
 * @author hcj
 * @date 2023-06-16
 */
public class IDUtils {
    public IDUtils() {
    }

    public static String makeIDByUUID() {
        String id = UUID.randomUUID().toString().replaceAll("-", "");
        return id;
    }

    public static String makeIDByCurrent() {
        Long mills = DateUtils.getCurrent();
        return mills.toString();
    }
}

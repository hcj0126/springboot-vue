package com.hcj.comm;

import java.io.Serializable;
import java.util.UUID;

/**
 * BaseEntity
 *
 * @author hcj
 * @date 2023-06-16
 */
public abstract class BaseEntity implements Serializable {
    public BaseEntity() {
    }

    public static String makeIDByUUID() {
        String id = UUID.randomUUID().toString().replaceAll("-", "");
        return id;
    }

    public static String makeIDByCurrent() {
        Long mills = System.currentTimeMillis();
        return mills.toString();
    }
}

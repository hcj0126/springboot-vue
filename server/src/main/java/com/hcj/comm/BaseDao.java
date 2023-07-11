package com.hcj.comm;

/**
 * BaseDao
 *
 * @author hcj
 * @date 2023-06-16
 */
public interface BaseDao<T, Pk> {
    void add(T var1);

    void update(T var1);

    void delete(T var1);

    T qryOne(Pk var1);
}

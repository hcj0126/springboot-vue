package com.hcj.comm;

/**
 * BaseService
 *
 * @author hcj
 * @date 2023-06-16
 */
public interface BaseService<T, Pk> {
    void add(T var1);

    void update(T var1);

    void delete(T var1);

    T getOne(Pk var1);
}

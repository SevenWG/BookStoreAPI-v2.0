package com.team404.bookstore.dao;

public interface UnifiedDaoInterface<T> {

    int AddEntity(T entity);
    boolean DeleteEntity(T entity);
    T GetEntityById(Class<T> clz, int id);
    boolean UpdateEntity(T entity);
}

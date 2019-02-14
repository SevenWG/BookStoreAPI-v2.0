package com.team404.bookstore.dao;

import java.util.List;
import java.util.Map;

public interface UnifiedDaoInterface<T> {

    int AddEntity(T entity);

    boolean DeleteEntity(T entity);

    T GetEntityById(Class<T> clz, int id);

    boolean UpdateEntity(T entity);

    List<T> GetDynamicList (String hql, int firstResult, int maxResults, Map<String, Object> map);
}

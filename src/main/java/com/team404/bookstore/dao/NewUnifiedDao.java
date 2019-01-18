package com.team404.bookstore.dao;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.*;


/* Another implementation of the generic DAO class
Reference: Https://www.cnblogs.com/fanjingfeng/p/6713722.html
* */
public class NewUnifiedDao<T> implements UnifiedDaoInterface<T> {

    public int AddEntity(T entity) {
        Session session = HibernateConnection.getSession();
        Transaction transaction = null;
        int id = 0;

        try {
            transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
            try{
                Method m = entity.getClass().getDeclaredMethod("getId");
                id = Integer.parseInt(String.valueOf(m.invoke(entity)));
            }catch (Exception e) {
                e.printStackTrace();
            }
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return id;
    }

    public boolean DeleteEntity(T entity) {
        Session session = HibernateConnection.getSession();
        Transaction transaction = null;
        boolean flag = true;

        try {
            transaction = session.beginTransaction();
            session.delete(entity);
            flag = true;
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            flag = false;
        } finally {
            session.close();
        }
        return flag;
    }

    public T GetEntityById(Class<T> clz,  int id) {
        Session session = HibernateConnection.getSession();
        Transaction transaction = null;
        T entity = null;
        try {
            transaction = session.beginTransaction();
            entity = session.get(clz, id);
            transaction.commit();

        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return  (T)entity;
    }

    public boolean UpdateEntity(T entity) {
        Session session = HibernateConnection.getSession();
        Transaction transaction = null;
        boolean flag = true;

        try {
            transaction = session.beginTransaction();
            session.update(entity);
            flag = true;
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            flag = false;
        } finally {
            session.close();
        }
        return flag;
    }

    public List<T> GetDynamicList(String hql, int firstResult, int maxResults, Map<String, Object> map){
        Session session = HibernateConnection.getSession();
        Transaction transaction = null;
        List<T> list = null;

        try {
            Query query = GetQuery(session, hql, map);
            System.out.println(query);
            if(firstResult != 0 && maxResults != 0) {
                list = query.setFirstResult(firstResult).setMaxResults(maxResults).list();
            }
            else {
                list = query.list();
            }
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return list;
    }

    public Query GetQuery(Session session, String hql, Map<String, Object> map) {
        Query query = session.createQuery(hql);
        if(map != null) {
            Set<String> keySet = map.keySet();
            for(String string : keySet) {
                Object object = map.get(string);

                if(object instanceof Collection<?>) {
                    query.setParameterList(string, (Collection<?>)object);
                }
                else if(object instanceof Object[]) {
                    query.setParameterList(string, (Object[])object);
                }else {
                    query.setParameter(string, object);
                }
            }
        }
        System.out.println(query.toString());
        return query;
    }
}

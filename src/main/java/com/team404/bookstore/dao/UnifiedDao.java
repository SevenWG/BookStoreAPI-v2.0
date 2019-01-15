package com.team404.bookstore.dao;

import com.team404.bookstore.entity.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.lang.reflect.Method;

public class UnifiedDao {

    public int AddEntity(Object entity) {
        Session session = HibernateConnection.getSession();
        Transaction transaction = null;
        int id = 0;

        try {
            transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
            try{
                Method m = entity.getClass().getDeclaredMethod("getId");
                Object object = m.invoke(entity);
                id = Integer.parseInt(String.valueOf(object));
            }catch (Exception e) {
                e.printStackTrace();
            }
        } catch (HibernateException e) {
            if (transaction!=null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return id;
    }

    public boolean DeleteEntity(Object entity) {
        Session session = HibernateConnection.getSession();
        Transaction transaction = null;
        boolean flag = true;

        try {
            transaction = session.beginTransaction();
            session.delete(entity);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction!=null) transaction.rollback();
            flag = false;
            e.printStackTrace();
        } finally {
            session.close();
        }
        return flag;
    }

    public Object GetEntityById(String className, int id) {
        String fullClassName = "com.team404.bookstore.entity." + className;
        Object object = null;

        Session session = HibernateConnection.getSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            try {
                Class<?> clz = Class.forName(fullClassName);
                transaction = session.beginTransaction();
                object = session.get(clz, id);
            }catch (Exception e) {
                e.printStackTrace();
            }

            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return  object;
    }

    public boolean UpdateEntity(Object entity) {
        Session session = HibernateConnection.getSession();
        Transaction transaction = null;
        boolean flag = true;

        try {
            transaction = session.beginTransaction();
            session.update(entity);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction!=null) transaction.rollback();
            flag = false;
            e.printStackTrace();
        } finally {
            session.close();
        }
        return flag;
    }


}

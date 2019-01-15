package com.team404.bookstore.service;

import com.team404.bookstore.dao.BookDao;
import com.team404.bookstore.entity.BookEntity;

import java.util.List;

public class ServiceTest {


    public static void main(String args[]) throws Exception {
        BookDao bookDao = new BookDao();
        List<BookEntity> list = bookDao.getListById(0);
        System.out.println(list.size());
    }
}

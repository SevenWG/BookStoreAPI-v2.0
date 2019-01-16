package com.team404.bookstore.service;

import com.team404.bookstore.dao.BookDao;
import com.team404.bookstore.entity.BookEntity;
import com.team404.bookstore.entity.ShoppingCartEntity;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import java.util.List;

public class ServiceTest {

    private static Jsonb jsonb = JsonbBuilder.create();
    public static void main(String args[]) throws Exception {
//        BookDao bookDao = new BookDao();
//        List<BookEntity> list = bookDao.getListById(0);
//        System.out.println(list.size());

        ShoppingCartEntity shoppingCartEntity = new ShoppingCartEntity();
        shoppingCartEntity.setBookid("1118008189");
        shoppingCartEntity.setQuantity(2);
        shoppingCartEntity.setUserid(1);

        String json = jsonb.toJson(shoppingCartEntity);
        System.out.println(json);

        int userid = 1;
        System.out.println(jsonb.toJson(userid));
    }
}

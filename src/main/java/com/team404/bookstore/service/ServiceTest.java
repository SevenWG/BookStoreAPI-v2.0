package com.team404.bookstore.service;

import com.team404.bookstore.dao.BookDao;
import com.team404.bookstore.entity.BookEntity;
import com.team404.bookstore.entity.ShoppingCartEntity;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import java.math.BigDecimal;
import java.util.List;

public class ServiceTest {

    private static Jsonb jsonb = JsonbBuilder.create();
    public static void main(String args[]) throws Exception {

        ShoppingCartEntity shoppingCartEntity = new ShoppingCartEntity();
        shoppingCartEntity.setBookid("1187189032");
        shoppingCartEntity.setQuantity(2);
        shoppingCartEntity.setUserid(21);

        String json = jsonb.toJson(shoppingCartEntity);
        System.out.println(json);

        BigDecimal n = new BigDecimal(Double.valueOf(4096*4096));
        BigDecimal result = new BigDecimal(Double.valueOf(1));
        System.out.println(result.divide(n).doubleValue());

    }
}

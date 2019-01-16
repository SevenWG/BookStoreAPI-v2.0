package com.team404.bookstore.service;

import com.team404.bookstore.dao.*;
import com.team404.bookstore.entity.BookEntity;
import com.team404.bookstore.entity.OrderBookEntity;
import com.team404.bookstore.entity.OrdersEntity;
import com.team404.bookstore.entity.ShoppingCartEntity;

import java.sql.Timestamp;
import java.util.List;

/*
 * Implementation of Facade Pattern
 * */


public class OrderServiceFacade {

    public int OrderGnerator(int userid) {
        ShoppingCartDao shoppingCartDao = new ShoppingCartDao();
        AddressDao addressDao = new AddressDao();
        OrderDao orderDao = new OrderDao();


        TimeGeneratorInterface timeGenerator = new TimeGenerator();
        AmountCalculatorInterface amountCalculator = new AmountCalculator();
        PriceCalculatorInterface priceCalculator = new PriceCalculator();

        OrdersEntity orderEntity = new OrdersEntity();
        List<ShoppingCartEntity> list = shoppingCartDao.getListById(userid);

        orderEntity.setUserid(userid);
        orderEntity.setGenerationtime(timeGenerator.GetTimestampValue());
        orderEntity.setTotalprice(priceCalculator.CalculateTotalPrice(list));
        orderEntity.setAddressid(addressDao.getAddressByUid(userid).getId());
        orderEntity.setStatus("Processing");

        orderEntity = priceCalculator.setMorePriceValues(orderEntity, userid);

        orderEntity.setAmount(amountCalculator.CalculateAmount(list));

        int id = orderDao.AddOrder(orderEntity);

        this.createOrderBook(list, id);

//        shoppingCartDao.DeleteShoppingItems(userid);

        return id;

    }

    private void createOrderBook(List<ShoppingCartEntity> list, int id) {
        OrderBookDao orderBookDao = new OrderBookDao();

        for(ShoppingCartEntity i : list) {
            OrderBookEntity orderBookEntity = new OrderBookEntity();

            orderBookEntity.setOrderid(id);
            orderBookEntity.setBookid(i.getBookid());
            orderBookEntity.setQuantity(i.getQuantity());

            orderBookDao.AddOrderBook(orderBookEntity);
        }
    }

    public void updateBookInventory(List<ShoppingCartEntity> list) {
        NewUnifiedDao newUnifiedDao = new NewUnifiedDao();
        BookDao bookDao = new BookDao();

        for(ShoppingCartEntity i : list) {
            BookEntity bookEntity = bookDao.getEntityById(Integer.parseInt(i.getBookid()));

            int newInventory = bookEntity.getInventory() - i.getQuantity();

            bookEntity.setInventory(newInventory);

            newUnifiedDao.UpdateEntity(bookEntity);
        }
    }
}

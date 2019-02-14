package com.team404.bookstore.service;

import com.team404.bookstore.dao.BookDao;
import com.team404.bookstore.dao.NewUnifiedDao;
import com.team404.bookstore.dao.UnifiedDao;
import com.team404.bookstore.dao.UnifiedDaoInterface;
import com.team404.bookstore.entity.*;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.core.Response;
import java.awt.image.RescaleOp;
import java.math.BigDecimal;
import java.util.List;

public class ServiceTest {

    private static Jsonb jsonb = JsonbBuilder.create();
    public static void main(String args[]) throws Exception {

//        OrderProcessAPI orderProcessAPI = new OrderProcessAPI();
//        AddressEntity addressEntity = new AddressEntity();
//        addressEntity.setCountry("Canada");addressEntity.setProvince("ON"); addressEntity.setStreet("171 LEES AVE");
//        addressEntity.setZip("K1S 5P3"); addressEntity.setPhone("818-818-8888");
//
//
//        UserEntity userEntity = new UserEntity();
//        userEntity.setUsername("test@test.com"); userEntity.setFirstname("Wei"); userEntity.setLastname("Test"); userEntity.setPassword("654321");
//
//        UserAddressCombine userAddressCombine = new UserAddressCombine();
//        userAddressCombine.setAddressEntity(addressEntity); userAddressCombine.setUserEntity(userEntity);
//
//        String json = jsonb.toJson(userAddressCombine);
//
//        System.out.println(json);
//        System.out.println(jsonb.toJson(userEntity));
//
//        UnifiedDaoInterface unifiedDaoInterface = new NewUnifiedDao();
//        System.out.println(unifiedDaoInterface.AddEntity(addressEntity));
//        Response response = orderProcessAPI.GetUserByAccount("test2@test.com");
//
//        System.out.println(response.getStatusInfo().equals(Response.Status.OK));

        ShoppingCartEntity shoppingCartEntity = new ShoppingCartEntity();
        shoppingCartEntity.setBookid("1187189032");
        shoppingCartEntity.setQuantity(2);
        shoppingCartEntity.setUserid(21);

        String json1 = jsonb.toJson(shoppingCartEntity);
        System.out.println(json1);
//
//        BigDecimal n = new BigDecimal(Double.valueOf(4096*4096));
//        BigDecimal result = new BigDecimal(Double.valueOf(1));
//        System.out.println(result.divide(n).doubleValue());

    }
}

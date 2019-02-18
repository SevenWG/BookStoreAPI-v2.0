package com.team404.bookstore.service;

import com.team404.bookstore.entity.*;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ServiceTest {

    private static Jsonb jsonb = JsonbBuilder.create();
    public static void main(String args[]) throws Exception {

        OrderProcessAPI orderProcessAPI = new OrderProcessAPI();
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

        List<ShoppingCartEntity> list = null;
        System.out.println(list.size());

//        ShoppingCartEntity shoppingCartEntity = new ShoppingCartEntity();
//        shoppingCartEntity.setBookid("1187189032");
//        shoppingCartEntity.setQuantity(3);
//        shoppingCartEntity.setUserid(21);
//
//        String json1 = jsonb.toJson(shoppingCartEntity);
//        System.out.println(json1);
//
//        Response response = orderProcessAPI.DisplayShoppingCart(21);
//
//        List<ShoppingCartEntity> list = new ArrayList<>();
//
//        ShoppingCartEntity[] arr = jsonb.fromJson(response.getEntity().toString(), ShoppingCartEntity[].class);
//        list = Arrays.asList(arr);
//
//        System.out.println(list.get(0).getBookid());
//
//        BigDecimal n = new BigDecimal(Double.valueOf(4096*4096));
//        BigDecimal result = new BigDecimal(Double.valueOf(1));
//        System.out.println(result.divide(n).doubleValue());

    }
}

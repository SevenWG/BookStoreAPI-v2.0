package com.team404.bookstore.service;

import com.team404.bookstore.dao.*;
import com.team404.bookstore.entity.*;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.*;


@Path("/OrderProcess")
public class OrderProcessAPI {

    private UnifiedDaoInterface unifiedDao = new NewUnifiedDao();
    private static Jsonb jsonb = JsonbBuilder.create();

    /* gets the user entity by user account .*/
    /*
    When the user entity is equals to null ( == null, do not use .equals())
    return HTTP 400 + wrong info message
    otherwise, return 200 + user entity
    * */
    @GET
    @Path("/GetUserByAccount/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response GetUserByAccount(@PathParam("username") String username){

        String hql = "FROM UserEntity WHERE username = :username";

        int firstResult = 0; int maxResult = 0;

        Map<String, Object> map = new HashMap<>();

        map.put("username", username);

        List<UserEntity> list = unifiedDao.GetDynamicList(hql, firstResult, maxResult, map);

        if(list == null || list.size() == 0) {
            String errorMessage = "Wrong user name, Cannot find this user!";
            return Response.status(Response.Status.BAD_REQUEST).entity(jsonb.toJson(errorMessage)).build();
        }
        else {
            UserEntity userEntity = list.get(0);
            return Response.status(Response.Status.OK).entity(jsonb.toJson(userEntity)).build();
        }
    }

    /*Submit Function
     *Different from the registration method in the previous project, because I am not familiar with the POST method, I don't know how to pass the object through Jersey.
     * So I convert the object to json string before receiving it. The method receives the data and then converts it back to the object.*/
    /*Reference:https://www.ibm.com/developerworks/cn/java/j-javaee8-json-binding-1/index.html?ca=drs-&utm_source=tuicool&utm_medium=referral*/
    /*
    When the user entity is NOT equals to null ( == null, do not use .equals())
    return HTTP 400 + wrong info message
    otherwise, return 200 + true
    * */
    @POST
    @Path("/CreateAccount")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response CreateAccount(String json) {
        boolean flag = true;

        UserAddressCombine userAddressCombine = jsonb.fromJson(json, UserAddressCombine.class);

        UserEntity userEntity = userAddressCombine.getUserEntity();
        AddressEntity addressEntity = userAddressCombine.getAddressEntity();

        Response response = this.GetUserByAccount(userEntity.getUsername());

        if(response.getStatusInfo().equals(Response.Status.OK)) {
            flag = false;
        }
        else {
            int id = unifiedDao.AddEntity(userEntity);
            addressEntity.setUserid(id);
            unifiedDao.AddEntity(addressEntity);
        }

        if(!flag) {
            String errorMessage = "Registration Failed! User account already exist";
            return Response.status(Response.Status.BAD_REQUEST).entity(jsonb.toJson(flag)).build();
        }
        else {
            return Response.status(Response.Status.OK).entity(jsonb.toJson(flag)).build();
        }
    }

    /*
    When the user entity is equals to null ( == null, do not use .equals())
    return HTTP 400 + wrong info message
    otherwise, return 200 + user entity
    * */
    @POST
    @Path("/getAccount")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getAccount(String json) {

        boolean flag = true;

        UserEntity userEntity = jsonb.fromJson(json, UserEntity.class);

        Response response = this.GetUserByAccount(userEntity.getUsername());

        if(response.getStatusInfo().equals(Response.Status.OK)){

            UserEntity userEntity1 = jsonb.fromJson( (String) response.getEntity(), UserEntity.class);

            if(userEntity.getPassword().equals(userEntity1.getPassword())) {
                flag = true;
            } else {
                flag = false;
            }

        } else {
            flag = false;
        }

        if(!flag) {
            String errorMessage = "Get Account Failed!";
            return Response.status(Response.Status.BAD_REQUEST).entity(jsonb.toJson(flag)).build();
        }
        else {
            return Response.status(Response.Status.OK).entity(jsonb.toJson(flag)).build();
        }
    }

    @GET
    @Path("/getUserinfo/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserinfo(@PathParam("id") int id) {

        UserEntity userEntity = (UserEntity) unifiedDao.GetEntityById(UserEntity.class, id);

        if(userEntity == null) {
            String errorMessage = "Wroing User ID!";
            return Response.status(Response.Status.BAD_REQUEST).entity(jsonb.toJson(errorMessage)).build();
        }
        else  {
            return Response.status(Response.Status.OK).entity(jsonb.toJson(userEntity)).build();
        }

    }

    @GET
    @Path("/getAddressinfo/{userid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAddressinfo(@PathParam("userid") int userid) {

        String hql = "FROM AddressEntity WHERE userid = :userid";

        int firstResult = 0; int maxResult = 0;

        Map<String, Object> map = new HashMap<>();

        map.put("userid", userid);

        List<AddressEntity> list = unifiedDao.GetDynamicList(hql, firstResult, maxResult, map);

        if(list == null || list.size() == 0) {
            String errorMessage = "Wroing User ID!";
            return Response.status(Response.Status.BAD_REQUEST).entity(jsonb.toJson(errorMessage)).build();
        }
        else  {
            AddressEntity addressEntity = list.get(0);
            return Response.status(Response.Status.OK).entity(jsonb.toJson(addressEntity)).build();
        }
    }

    @POST
    @Path("/AddItemtoCart")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response AddItemtoCart(String json) {

        ShoppingCartEntity shoppingCartEntity = jsonb.fromJson(json, ShoppingCartEntity.class);

        String hql = "FROM ShoppingCartEntity WHERE userid = :userid AND bookid = :bookid";

        int firstResult = 0; int maxResult = 0;

        Map<String, Object> map = new HashMap<>();

        map.put("userid", shoppingCartEntity.getUserid());
        map.put("bookid", shoppingCartEntity.getBookid());

        if(unifiedDao.GetDynamicList(hql, firstResult, maxResult, map).size() == 0) {

            int flag = unifiedDao.AddEntity(shoppingCartEntity);

            if(flag == 0) {
                String errorMessage = "Add Items Action Failed!";
                return Response.status(Response.Status.BAD_REQUEST).entity(jsonb.toJson(flag + " " + errorMessage)).build();
            }
            else {
                return Response.status(Response.Status.OK).entity(jsonb.toJson(flag)).build();
            }
        }

        else {
            boolean flag = unifiedDao.UpdateEntity(shoppingCartEntity);
            if(!flag) {
                String errorMessage = "Update Items Action Failed!";
                return Response.status(Response.Status.BAD_REQUEST).entity(jsonb.toJson(flag + " " +errorMessage)).build();
            }
            else {
                return Response.status(Response.Status.OK).entity(jsonb.toJson(flag)).build();
            }
        }
    }

    @GET
    @Path("/DisplayShoppingCart/{userid}")
    @Produces(MediaType.APPLICATION_JSON)
    /*Display customers' shopping cart contents*/
    /*
     * Implementation of Factory Pattern
     * */
    public Response DisplayShoppingCart(@PathParam("userid") int userid) {

        String hql = "FROM ShoppingCartEntity WHERE userid = :userid";

        int firstResult = 0; int maxResult = 0;

        Map<String, Object> map = new HashMap<>();

        map.put("userid", userid);

        List<ShoppingCartEntity> list = unifiedDao.GetDynamicList(hql, firstResult, maxResult, map);

        if(list == null || list.size() == 0) {
            String erroMessage = "Error! Check the Database";
            return Response.status(Response.Status.BAD_REQUEST).entity(jsonb.toJson(erroMessage)).build();
        }
        else
        return  Response.status(Response.Status.OK).entity(jsonb.toJson(list)).build();
    }

    @DELETE
    @Path("/DeleteSingleItem/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    /*Delete single item in shopping cart*/
    public Response DeleteSingleItem(@PathParam("id") int id) {
        ShoppingCartEntity shoppingCartEntity = (ShoppingCartEntity) unifiedDao.GetEntityById(ShoppingCartEntity.class, id);
        boolean flag = unifiedDao.DeleteEntity(shoppingCartEntity);

        if(!flag) {
            String errorMessage = "Wroing Book ID! Delete Action Failed";
            return Response.status(Response.Status.BAD_REQUEST).entity(jsonb.toJson(flag)).build();
        }
        else {
            return Response.status(Response.Status.OK).entity(jsonb.toJson(flag)).build();
        }
    }

    @POST
    @Path("/createOrder")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createOrder(String json) {

        Jsonb jsonb = JsonbBuilder.create();
        int userid = jsonb.fromJson(json, int.class);

        Response response = this.DisplayShoppingCart(userid);

        if(response.getStatus() == 200) {

            ShoppingCartEntity[] arr = jsonb.fromJson(response.getEntity().toString(), ShoppingCartEntity[].class);

            List<ShoppingCartEntity> list = Arrays.asList(arr);

            int amount = this.CalculateAmount(list);

            float totalprice = this.CalculateTotalPrice(list);

            Response response1 = this.getAddressinfo(userid);

            AddressEntity addressEntity = null;

            if(response1.getStatus() == 200) {
                addressEntity = jsonb.fromJson(response1.getEntity().toString(), AddressEntity.class);
            }
            else {
                return Response.status(Response.Status.BAD_REQUEST).entity(jsonb.toJson("No Address Found!")).build();
            }

            boolean flag = true;

            if(!addressEntity.getProvince().equals("ON")) {
                flag = false;
            }

            int addressid = addressEntity.getId();

            OrdersEntity ordersEntity = new OrdersEntity(userid, amount, addressid, totalprice, flag);

            int id = unifiedDao.AddEntity(ordersEntity);

            if(id != 0){
                this.createOrderBook(list, id);
                return Response.status(Response.Status.OK).entity(jsonb.toJson(id)).build();
            }
            else{
                String errorMessage = "Create Order Failed!";
                return Response.status(Response.Status.BAD_REQUEST).entity(jsonb.toJson(errorMessage)).build();
            }

        }

        else {
            return Response.status(Response.Status.BAD_REQUEST).entity(jsonb.toJson("Wrong User id!")).build();
        }
    }

    /*Confirm Order Action*/
    @GET
    @Path("/confirmOrder/{orderid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response confirmOrder(@PathParam("orderid") int orderid) {

        CountsEntity countsEntity = (CountsEntity) unifiedDao.GetEntityById(CountsEntity.class, 1);

        OrdersEntity ordersEntity = (OrdersEntity) unifiedDao.GetEntityById(OrdersEntity.class, orderid);

        boolean flag = true;

        if(ordersEntity.getStatus().equals("Processing")) {

            if(countsEntity.getCounts() % 5 == 0 && countsEntity.getCounts() >= 5) {

                ordersEntity.setStatus("Failed");

                flag = unifiedDao.UpdateEntity(ordersEntity);

            }
            else {
                ordersEntity.setStatus("Success");

                flag = unifiedDao.UpdateEntity(ordersEntity);

                int userid = ordersEntity.getUserid();

                Response response = this.DisplayShoppingCart(userid);

                if(response.getStatus() == 200) {
                    ShoppingCartEntity[] arr = jsonb.fromJson(response.getEntity().toString(), ShoppingCartEntity[].class);

                    List<ShoppingCartEntity> list = Arrays.asList(arr);

                    this.updateBookInventory(list);

                    this.DeleteShoppingItems(list);

                }
                else {
                    return Response.status(Response.Status.BAD_REQUEST).entity(jsonb.toJson("Wrong User id!")).build();
                }
            }

            countsEntity.setCounts(countsEntity.getCounts() + 1);

            unifiedDao.UpdateEntity(countsEntity);

            return Response.status(Response.Status.OK).entity(jsonb.toJson(flag)).build();
        }

        else {
            return Response.status(Response.Status.BAD_REQUEST).entity(jsonb.toJson("Cannot confirm finished Order!")).build();
        }
    }

    @GET
    @Path("/DisplayMyOrder/{userid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response DisplayMyOrder (@PathParam("userid") int userid) {

        String hql = "FROM OrdersEntity WHERE userid = :userid";

        int firstResult = 0; int maxResult = 0;

        Map<String, Object> map = new HashMap<>();

        map.put("userid", userid);

        List<OrderBookEntity> list = unifiedDao.GetDynamicList(hql, firstResult, maxResult, map);

        if(list == null || list.size() == 0) {
            String erroMessage = "Error! Check the Database";
            return Response.status(Response.Status.BAD_REQUEST).entity(jsonb.toJson(erroMessage)).build();
        }
        else
        return  Response.status(Response.Status.OK).entity(jsonb.toJson(list)).build();
    }

    @GET
    @Path("/GetOrderBooks/{orderid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response GetOrderBooks (@PathParam("orderid") int orderid) {

        String hql = "FROM OrderBookEntity WHERE orderid = :orderid";

        int firstResult = 0; int maxResult = 0;

        Map<String, Object> map = new HashMap<>();

        map.put("orderid", orderid);

        List<OrderBookEntity> list = unifiedDao.GetDynamicList(hql, firstResult, maxResult, map);

        if(list == null || list.size() != 0) {
            return Response.status(Response.Status.OK).entity(jsonb.toJson(list)).build();
        }
        else {
            String errorMessage = "Wroing Order ID!";
            return Response.status(Response.Status.BAD_REQUEST).entity(jsonb.toJson(errorMessage)).build();
        }
    }

    public int CalculateAmount(List<ShoppingCartEntity> list) {
        int amount = 0;
        for(int i = 0; i < list.size(); i++){
            ShoppingCartEntity shoppingCartEntity = list.get(i);
            amount += shoppingCartEntity.getQuantity();
        }
        return amount;
    }


    private float CalculateTotalPrice(List<ShoppingCartEntity> list) {
        float totalPrice = 0;

        for(ShoppingCartEntity i : list) {
            BookEntity bookEntity = (BookEntity) unifiedDao.GetEntityById(BookEntity.class, Integer.valueOf(i.getBookid()));
            totalPrice += bookEntity.getPrice()*i.getQuantity();
        }
        return totalPrice;
    }

    private void createOrderBook(List<ShoppingCartEntity> list, int orderid) {

        for(ShoppingCartEntity i : list) {
            OrderBookEntity orderBookEntity = new OrderBookEntity();

            orderBookEntity.setOrderid(orderid);
            orderBookEntity.setBookid(i.getBookid());
            orderBookEntity.setQuantity(i.getQuantity());

            unifiedDao.AddEntity(orderBookEntity);
        }
    }

    private void updateBookInventory(List<ShoppingCartEntity> list) {

        for(ShoppingCartEntity i : list) {
            BookEntity bookEntity = (BookEntity) unifiedDao.GetEntityById(BookEntity.class, Integer.parseInt(i.getBookid()));

            int newInventory = bookEntity.getInventory() - i.getQuantity();

            bookEntity.setInventory(newInventory);

            unifiedDao.UpdateEntity(bookEntity);
        }

    }

    private void DeleteShoppingItems(List<ShoppingCartEntity> list) {
        for(ShoppingCartEntity i : list) {
            unifiedDao.DeleteEntity(i);
        }
    }
}

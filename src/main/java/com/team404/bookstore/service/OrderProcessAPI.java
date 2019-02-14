package com.team404.bookstore.service;

import com.team404.bookstore.dao.*;
import com.team404.bookstore.entity.*;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Path("/OrderProcess")
public class OrderProcessAPI {
    private UserDao userDao;
    private AddressDao addressDao;
    private ShoppingCartDao shoppingCartDao;
    private CountDao countDao;
    private OrderDao orderDao;
    private OrderBookDao orderBookDao;

    private UnifiedDaoInterface unifiedDao = new NewUnifiedDao();

    private DaoFactoryImpl daoFactory = DaoFactoryImpl.SingleDaoFactory();
    private OrderServiceFacade orderServiceFacade;
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

        int firstResult = 0;

        int maxResult = 0;

        Map<String, Object> map = new HashMap<>();

        map.put("username", username);

        List<UserEntity> list = unifiedDao.GetDynamicList(hql, firstResult, maxResult, map);

        if(list.size() == 0) {
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

        int firstResult = 0;

        int maxResult = 0;

        Map<String, Object> map = new HashMap<>();

        map.put("userid", userid);

        List<AddressEntity> list = unifiedDao.GetDynamicList(hql, firstResult, maxResult, map);

        if(list.size() == 0) {
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

        shoppingCartDao = new ShoppingCartDao();

        ShoppingCartEntity shoppingCartEntity = jsonb.fromJson(json, ShoppingCartEntity.class);

        String hql = "FROM ShoppingCartEntity WHERE userid = :userid AND bookid = :bookid";

        int firstResult = 0;

        int maxResult = 0;

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

        List<ShoppingCartEntity> list = (List<ShoppingCartEntity>)daoFactory.
                ListSomethingById("ShoppingCartDao", "getListById", userid);

        return  Response.status(Response.Status.OK).entity(list).build();
    }

    @DELETE
    @Path("/DeleteSingleItem/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    /*Delete single item in shopping cart*/
    public Response DeleteSingleItem(@PathParam("id") int id) {
        shoppingCartDao = new ShoppingCartDao();

        boolean flag = shoppingCartDao.DeleteShoppingItemById(id);

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

        orderServiceFacade = new OrderServiceFacade();

        int id = orderServiceFacade.OrderGnerator(userid);

        if(id != 0)
            return Response.status(Response.Status.OK).entity(jsonb.toJson(id)).build();
        else{
            String errorMessage = "Create Order Failed!";
            return Response.status(Response.Status.BAD_REQUEST).entity(jsonb.toJson(errorMessage)).build();
        }
    }

    /*Confirm Order Action*/
    @GET
    @Path("/confirmOrder/{orderid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response confirmOrder(@PathParam("orderid") int orderid) {
        countDao = new CountDao();
        orderDao = new OrderDao();
        shoppingCartDao = new ShoppingCartDao();
        orderServiceFacade = new OrderServiceFacade();

        boolean flag = true;

        if(countDao.getCount().getCounts() % 5 == 0 && countDao.getCount().getCounts() >= 5) {
            countDao.CountUpdate();
            flag = false;
            orderDao.UpdateOrderStatus(orderid, flag);
        }else {
            countDao.CountUpdate();
            flag = true;
            orderDao.UpdateOrderStatus(orderid, flag);

            OrdersEntity ordersEntity = orderDao.getEntityById(orderid);
            int userid = ordersEntity.getUserid();
            List<ShoppingCartEntity> list = shoppingCartDao.getListById(userid);
            orderServiceFacade.updateBookInventory(list);
            shoppingCartDao.DeleteShoppingItems(userid);
        }

        return Response.status(Response.Status.OK).entity(jsonb.toJson(flag)).build();
    }

    @GET
    @Path("/DisplayMyOrder/{userid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response DisplayMyOrder (@PathParam("userid") int userid) {

        List<OrdersEntity> list =
                (List<OrdersEntity>)daoFactory.
                        ListSomethingById("OrderDao", "getListById", userid);

        return  Response.status(Response.Status.OK).entity(jsonb.toJson(list)).build();
    }

    @GET
    @Path("/GetOrderBooks/{orderid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response GetOrderBooks (@PathParam("orderid") int orderid) {
        orderBookDao = new OrderBookDao();

        List<OrderBookEntity> list = orderBookDao.GetOrderBookByOid(orderid);

        return Response.status(Response.Status.OK).entity(jsonb.toJson(list)).build();
    }
}

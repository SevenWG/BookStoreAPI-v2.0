package com.team404.bookstore.service;

import com.team404.bookstore.dao.*;
import com.team404.bookstore.entity.BookEntity;
import com.team404.bookstore.entity.CategoryEntity;
import org.eclipse.yasson.internal.JsonBinding;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

@Path("/ProductCatalog")
public class ProductCatalogAPI {

    private UnifiedDaoInterface unifiedDao = new NewUnifiedDao();

    private static Jsonb jsonb = JsonbBuilder.create();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String hello() {
        return "Hello World!";
    }

    @GET
    @Path("/getCategoryList")
    @Produces(MediaType.APPLICATION_JSON)
    /* gets the list of product categories for the store */
    public Response getCategoryList() {

        String hql = "FROM CategoryEntity";

        int firstResult = 0;

        int maxResult = 0;

        HashMap<String, Object> map = null;

        List<CategoryEntity> list = unifiedDao.GetDynamicList(hql, 0, 0, map);

        if(list == null || list.size() == 0) {
            String erroMessage = "Error! Check the Database";
            return Response.status(Response.Status.BAD_REQUEST).entity(jsonb.toJson(erroMessage)).build();
        }
        else
        return Response.status(Response.Status.OK).entity(jsonb.toJson(list)).build();

    }


    @GET
    @Path("/getProductList")
    @Produces(MediaType.APPLICATION_JSON)
    /*gets the list of products*/
    /*
     * Implementation of Factory Pattern
     * */
    public Response getProductList() {
        String hql = "FROM BookEntity WHERE inventory > 0";

        int firstResult = 0;

        int maxResult = 0;

        HashMap<String, Object> map = null;

        List<BookEntity> list = unifiedDao.GetDynamicList(hql, firstResult, maxResult, map);

        if(list == null || list.size() == 0) {
            String erroMessage = "Error! Check the Database";
            return Response.status(Response.Status.BAD_REQUEST).entity(jsonb.toJson(erroMessage)).build();
        }
        else
        return Response.status(Response.Status.OK).entity(jsonb.toJson(list)).build();
    }

    /*gets the list of products for a specific category*/
    /*
     * Implementation of Factory Pattern
     * */
    /*
    When the size of list is 0(Wrong Category or no book in this catefory)
    return HTTP 400 + wrong info message
    otherwise, return 200 + list
    * */
    @GET
    @Path("/getProductList/{categoryid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductList(@PathParam("categoryid") int categoryid) {

        String hql = "FROM BookEntity WHERE inventory > 0 and categoryid = :categoryid";

        int firstResult = 0;

        int maxResult = 0;

        Map<String, Object> map = new HashMap<>();

        map.put("categoryid", categoryid);

        List<BookEntity> list = unifiedDao.GetDynamicList(hql, firstResult, maxResult, map);

        if(list == null || list.size() == 0) {
            String erroMessage = "Wrong Category or No book in this Category!";
            return Response.status(Response.Status.BAD_REQUEST).entity(jsonb.toJson(erroMessage)).build();
        }
        else {
            return Response.status(Response.Status.OK).entity(jsonb.toJson(list)).build();
        }
    }

    /* gets the detailed product information for a product.*/
    /*
     * Implementation of Factory Pattern
     * */
    /*
    When the book entity is equals to null ( == null, do not use .equals())
    return HTTP 400 + wrong info message
    otherwise, return 200 + book entity
    * */
    @GET
    @Path("/getProductInfo/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductInfo(@PathParam("id") String id) {

        int id_int = Integer.parseInt(id.trim());

        BookEntity bookEntity = (BookEntity) unifiedDao.GetEntityById(BookEntity.class, id_int);

        if(bookEntity == null) {
            String errorMessage = "Wrong Book ID!";
            return Response.status(Response.Status.BAD_REQUEST).entity(jsonb.toJson(errorMessage)).build();
        }
        else {
            return Response.status(Response.Status.OK).entity(jsonb.toJson(bookEntity)).build();
        }
    }

    /* gets the detailed information for a category.*/
    /*
    When the category entity is equals to null ( == null, do not use .equals())
    return HTTP 400 + wrong info message
    otherwise, return 200 + category entity
    * */
    @GET
    @Path("/getCategory/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCategory(@PathParam("id") int id) {

        CategoryEntity categoryEntity = (CategoryEntity) unifiedDao.GetEntityById(CategoryEntity.class, id);

        if(categoryEntity == null) {
            String errorMessage = "Wrong Category ID!";
            return Response.status(Response.Status.BAD_REQUEST).entity(jsonb.toJson(errorMessage)).build();
        }
        else {
            return Response.status(Response.Status.OK).entity(jsonb.toJson(categoryEntity)).build();
        }

    }
}

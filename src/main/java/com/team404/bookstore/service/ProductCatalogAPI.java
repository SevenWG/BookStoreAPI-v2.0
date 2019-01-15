package com.team404.bookstore.service;

import com.team404.bookstore.dao.BookDao;
import com.team404.bookstore.dao.CategoryDao;
import com.team404.bookstore.dao.DaoFactoryImpl;
import com.team404.bookstore.entity.CategoryEntity;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/ProductCatalog")
public class ProductCatalogAPI {

    private CategoryDao categoryDao;
    private BookDao bookDao;
    private DaoFactoryImpl daoFactory = DaoFactoryImpl.SingleDaoFactory();
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
        categoryDao = new CategoryDao();

        List<CategoryEntity> list = categoryDao.ListCategory();

        return Response.status(Response.Status.OK).entity(jsonb.toJson(list)).build();
    }
}

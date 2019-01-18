package com.team404.bookstore.service;

import com.team404.bookstore.dao.NewUnifiedDao;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.math.BigDecimal;
import java.util.*;

@Path("/AdminService")
public class AdminServiceAPI<T> {

    private NewUnifiedDao newUnifiedDao = new NewUnifiedDao();
    private static Jsonb jsonb = JsonbBuilder.create();

    @POST
    @Path("/AddEntity")
    @Produces(MediaType.APPLICATION_JSON)
    public Response AddEntity(Form form) {
        Response response = null;
        MultivaluedMap<String, String> map = form.asMap();
        int id = 0;

        String className = "com.team404.bookstore.entity." +
                map.get("ClassName").toString().replaceAll("[\\[\\]]","");

        String json = map.get("Json").toString().replaceAll("[\\[\\]]", "");

        try {
            Class<?> clz = Class.forName(className);
            Object entity = jsonb.fromJson(json, clz);
            id = newUnifiedDao.AddEntity(entity);
        }catch (Exception e) {
            e.printStackTrace();
        }

        if(id != 0) {
            response = Response.status(Response.Status.OK).entity(jsonb.toJson(id)).build();
            return response;
        }
        else {
            String errorMessage = "Add Action Failed!";
            response = Response.status(Response.Status.BAD_REQUEST).entity(jsonb.toJson(errorMessage)).build();
            return  response;
        }
    }

    @POST
    @Path("/DeleteEntity")
    @Produces(MediaType.APPLICATION_JSON)
    public Response DeleteEntity(Form form) {
        Response response = null;
        MultivaluedMap<String, String> map = form.asMap();
        boolean flag = true;

        String className = "com.team404.bookstore.entity." +
                map.get("ClassName").toString().replaceAll("[\\[\\]]","");

        String json = map.get("Json").toString().replaceAll("[\\[\\]]", "");

        try {
            Class<?> clz = Class.forName(className);
            Object entity = jsonb.fromJson(json, clz);
            flag = newUnifiedDao.DeleteEntity(entity);
            System.out.println(flag);
        }catch (Exception e) {
            e.printStackTrace();
        }
        if(flag) {
            response = Response.status(Response.Status.OK).entity(jsonb.toJson(flag)).build();
            return response;
        }
        else {
            String errorMessage = "Delte Action Failed!";
            response = Response.status(Response.Status.BAD_REQUEST).entity(jsonb.toJson(errorMessage)).build();
            return  response;
        }
    }

    @POST
    @Path("/GetEntity")
    @Produces(MediaType.APPLICATION_JSON)
    public Response GetEntity(Form form) {
        Response response = null;
        MultivaluedMap<String, String> map = form.asMap();
        Object object = null;

        String className = "com.team404.bookstore.entity." +
                map.get("ClassName").toString().replaceAll("[\\[\\]]","");

        String json = map.get("id").toString().replaceAll("[\\[\\]]", "");
        int id = Integer.parseInt(json);

        try {
            Class<?> clz = Class.forName(className);
            object = newUnifiedDao.GetEntityById(clz, id);
        }catch (Exception e) {
            e.printStackTrace();
        }

        if(object != null) {
            response = Response.status(Response.Status.OK).entity(jsonb.toJson(object)).build();
            return response;
        }
        else {
            String errorMessage = "Get Action Failed!";
            response = Response.status(Response.Status.BAD_REQUEST).entity(jsonb.toJson(errorMessage)).build();
            return  response;
        }
    }

    @POST
    @Path("/UpdateEntity")
    @Produces(MediaType.APPLICATION_JSON)
    public Response UpdateEntity(Form form) {
        Response response = null;
        MultivaluedMap<String, String> map = form.asMap();
        boolean flag = true;

        String className = "com.team404.bookstore.entity." +
                map.get("ClassName").toString().replaceAll("[\\[\\]]","");

        String json = map.get("Json").toString().replaceAll("[\\[\\]]", "");

        try {
            Class<?> clz = Class.forName(className);
            Object entity = jsonb.fromJson(json, clz);
            flag = newUnifiedDao.UpdateEntity(entity);
        }catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(flag);
        if(flag) {
            response = Response.status(Response.Status.OK).entity(jsonb.toJson(flag)).build();
            return response;
        }
        else {
            String errorMessage = "Update Action Failed!";
            response = Response.status(Response.Status.BAD_REQUEST).entity(jsonb.toJson(errorMessage)).build();
            return  response;
        }
    }


    /*
     Dynamically output a list of query results based on hql statements and parameters
     The received form includes four parameters
     "maxResults": hql statement
     "firstResult": paging query start parameter
     "maxResults": paging query end parameter
     "map": HashMap carrying the specific parameter values in the hql statement
     The above four parameters are in json format
     After receiving the form, convert the form to MultivaluedMap
     Then convert each parameter to its corresponding format according to the column name of the form.
     The most complicated of these is the format conversion of map, you need to pay attention!
    * */
    @POST
    @Path("/DynamicList")
    @Produces(MediaType.APPLICATION_JSON)
    public Response DynamicList(Form form) {
        Response response = null;

        MultivaluedMap<String, String> map = form.asMap();

        String hql = map.get("hql").toString().replaceAll("[\\[\\]]","");
        String firstResultString = map.get("firstResult").toString().replaceAll("[\\[\\]]","");
        String maxResultsString = map.get("maxResults").toString().replaceAll("[\\[\\]]","");
        int firstResult = Integer.valueOf(firstResultString);
        int maxResults = Integer.valueOf(maxResultsString);

        /*
        Two statements can not be combined
          must first instantiate map1, then assign
        */
        Map<String, Object> map1 = new HashMap<>();
        String temp = map.get("map").toString();

        /*Remove the first and last [] symbols,
          because the map exists in the array also use [],
          so you can not remove the [] symbol in the previous way
        */
        String mapString = map.get("map").toString().substring(1, temp.length()-2);
        map1 = jsonb.fromJson(mapString, map1.getClass());
        /*
        !!!!!
        The int variable in the list or array stored in the original map has become the BigDecimal type after the REST POST request,
        so it must be converted back to the int type.
        * */
        Map<String, Object> newMap = Transform(map1);

        List<?> list = newUnifiedDao.GetDynamicList(hql, firstResult, maxResults, newMap);

        response = Response.status(Response.Status.OK).entity(jsonb.toJson(list)).build();

        return response;
    }

    /*Convert a variable type in a List or an array from BigDecimal to int*/
    public Map Transform(Map map) {
        Map<String, Object> newMap = new HashMap<>();
        if(map != null) {
            Set<String> keySet = map.keySet();
            for(String string : keySet) {
                Object object = map.get(string);

                if(object instanceof Collection<?>) {
                    List<?> objects = (List<?>) object;

                    if (objects.get(0) instanceof BigDecimal) {
                        List<Integer> list = new ArrayList<>();
                        for(int i = 0; i < objects.size(); i++) {
                            BigDecimal b = (BigDecimal)objects.get(i);
                            int x = b.intValue();
                            list.add(x);
                        }
                        newMap.put(string, list);
                    }
                }

                else if (object instanceof Object[]) {
                    Object[] objects = (Object[]) object;

                    if(objects[0] instanceof BigDecimal) {
                        int[] arr = new int[objects.length];
                        for(int i = 0; i < objects.length; i++) {
                            BigDecimal b = (BigDecimal)objects[i];
                            int x =b.intValue();
                            arr[i] = x;
                        }
                        newMap.put(string, arr);
                    }
                }

                else  {
                    if(object instanceof BigDecimal) {
                        BigDecimal b = (BigDecimal) object;
                        int x = b.intValue();
                        newMap.put(string, x);
                    }
                }
            }
        }
        return newMap;
    }
}

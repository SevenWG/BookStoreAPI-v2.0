import com.team404.bookstore.entity.BookEntity;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import java.util.ArrayList;
import java.util.List;

/**
 * OrderProcessAPI Tester.
 *
 * @author <Wei Wei>
 * @since <pre>January 18, 2019</pre>
 * @version 1.0
 */

public class ProductCatalogAPITest {
    private static Jsonb jsonb = JsonbBuilder.create();

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     *
     * Method: getProductList()
     *
     */
    @Test
    public void testGetProductList() throws Exception {
        Response response = RestAssured.get("http://localhost:8080/rest/ProductCatalog/getProductList");

        int code = response.getStatusCode();

        System.out.println("Status Code: " +code);

        Assert.assertEquals(code, 200);

        String data = response.asString();

        System.out.println(data);

        List<BookEntity> bookEntityList = jsonb.fromJson(data,
                new ArrayList<BookEntity>(){}.getClass().getGenericSuperclass());

        for(BookEntity i : bookEntityList) {
            System.out.println(i.toString());
        }
    }

    /**
     *
     * Method: getProductList(@PathParam("categoryid") int categoryid)
     *
     */
    @Test
    public void testGetProductListCategoryid() throws Exception {
        /*Change the id whatever you want*/
        String id = "1";

        Response response = RestAssured.get("http://localhost:8080/rest/ProductCatalog/getProductList/"  + id);

        int code = response.getStatusCode();

        System.out.println("Status Code: " +code);

        Assert.assertEquals(code, 200);

        String data = response.asString();

        System.out.println(data);
        List<BookEntity> bookEntityList = jsonb.fromJson(data,
                new ArrayList<BookEntity>(){}.getClass().getGenericSuperclass());

        for(BookEntity i : bookEntityList) {
            System.out.println(i.toString());
        }
    }

    @Test
    public void testGetProductListwithWrongCatagoryid() throws Exception {
        String woringId = "100";

        Response response = RestAssured.get("http://localhost:8080/rest/ProductCatalog/getProductList/"  + woringId);

        int code = response.getStatusCode();

        System.out.println("Status Code: " +code);

        String data = response.asString();

        System.out.println(data);

    }


}

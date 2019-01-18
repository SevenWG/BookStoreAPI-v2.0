
import com.team404.bookstore.entity.ShoppingCartEntity;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;

/**
 * OrderProcessAPI Tester.
 *
 * @author <Wei Wei>
 * @since <pre>January 18, 2019</pre>
 * @version 1.0
 */
public class OrderProcessAPITest {

    private static Jsonb jsonb = JsonbBuilder.create();

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }
    /**
     *
     * Method: AddItemtoCart(String json)
     *
     */
    @Test
    public void testAddItemtoCart() throws Exception {
        ShoppingCartEntity shoppingCartEntity = new ShoppingCartEntity();
        shoppingCartEntity.setBookid("1323508820");
        shoppingCartEntity.setQuantity(5);
        shoppingCartEntity.setUserid(21);

        String json = jsonb.toJson(shoppingCartEntity);

        RequestSpecification request = RestAssured.given();

        request.header("Content-Type", "application/json");
        request.body(json);

        Response response = request.post("http://localhost:8080/rest/OrderProcess/AddItemtoCart");

        int code = response.getStatusCode();
        System.out.println("Status Code: " + code);

        Assert.assertEquals(code, 200);

        String data = response.asString();

        System.out.println(data);
    }

    @Test
    public void testAddTooManyItemtoCart() throws Exception {
        ShoppingCartEntity shoppingCartEntity = new ShoppingCartEntity();
        shoppingCartEntity.setBookid("1187189032");
        shoppingCartEntity.setQuantity(20000);
        shoppingCartEntity.setUserid(21);

        String json = jsonb.toJson(shoppingCartEntity);

        RequestSpecification request = RestAssured.given();

        request.header("Content-Type", "application/json");
        request.body(json);

        Response response = request.post("http://localhost:8080/rest/OrderProcess/AddItemtoCart");

        int code = response.getStatusCode();
        System.out.println("Status Code: " + code);

        Assert.assertEquals(code, 400);

        String data = response.asString();

        System.out.println(data);
    }

    /**
     *
     * Method: createOrder(String json)
     *
     */
    @Test
    public void testCreateOrder() throws Exception {
        int userid = 21;
        String json = jsonb.toJson(userid);

        RequestSpecification request = RestAssured.given();

        request.header("Content-Type", "application/json");
        request.body(json);

        Response response = request.post("http://localhost:8080/rest/OrderProcess/createOrder");

        int code = response.getStatusCode();
        System.out.println("Status Code: " + code);

        Assert.assertEquals(code, 200);

        String data = response.asString();

        System.out.println("Order ID:" + data);
    }

    /**
     *
     * Method: confirmOrder(@PathParam("orderid") int orderid)
     *
     */

    @Test
    public void testConfirmOrder() throws Exception {

        /*use the output in testCreateOrder() as orderid */
        String orderid = "38";

        Response response = RestAssured.get("http://localhost:8080/rest/OrderProcess/confirmOrder/" + orderid);

        int code = response.getStatusCode();
        System.out.println("Status Code: " + code);

        String data = response.asString();

        System.out.println(data);
    }



}

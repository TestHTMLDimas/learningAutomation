import io.restassured.http.ContentType;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class RestAssuredTaskTwoTest {

    private static final String BASE_URL = "http://localhost:8080/";

    public record postGoodsAdd(String name, Double price) {
    }


    @Test
    @Tag("regress")
    void postGoodsAddCheckStatusCode200() {

        given()
                .contentType(ContentType.JSON)
                .body(new postGoodsAdd("Бублики", 120.50))
                .auth()
                .basic("admin", "secret123")
                .post(BASE_URL + "goods/add")
                .then()
                .log().all()
                .statusCode(200)
                .body("data.id", is(notNullValue()))
                .body("message", equalTo("success"));
    }

    @Test
    @Tag("regress")
    void postGoodsAddCheckStatusCode400() {

        given()
                .contentType(ContentType.JSON)
                .body(" ")
                .auth()
                .basic("admin", "secret123")
                .post(BASE_URL + "goods/add")
                .then()
                .log().all()
                .statusCode(400);
    }

    @Test
    @Tag("regress")
    void getGoodsIdCheckStatusCode200() {

        given()
                .auth()
                .basic("admin", "secret123")
                .get(BASE_URL + "goods/1")
                .then()
                .log().all()
                .statusCode(200)
                .body("id", is(1))
                .body("name", is(notNullValue()))
                .body("price", is(notNullValue()));
    }

    @Test
    @Tag("regress")
    void getGoodsIdCheckStatusCode404() {

        given()
                .auth()
                .basic("admin", "secret123")
                .get(BASE_URL + "goods/100")
                .then()
                .log().all()
                .statusCode(404)
                .body("message", is("Good with id '100' is not found!"));
    }

    @Test
    @Tag("regress")
    void deleteGoodsIdCheckStatusCode200() {
        Integer id = 34;

        given()
                .auth()
                .basic("admin", "secret123")
                .delete(BASE_URL + "goods/" + id)
                .then()
                .log().all()
                .statusCode(200)
                .body(is("Good with id '" + id + "' has been deleted successfully!"));

    }

    @Test
    @Tag("regress")
    void deleteGoodsIdCheckStatusCode404() {

        given()
                .auth()
                .basic("admin", "secret123")
                .delete(BASE_URL + "goods/100")
                .then()
                .log().all()
                .statusCode(404)
                .body(is("Good with id '100' is not found"));
    }

    @Test
    @Tag("regress")
    void patchGoodsIdCheckStatusCode200() {
        Integer id = 36;
        String name = "Бублики";
        Double price = 106.50;

        given()
                .contentType(ContentType.JSON)
                .body(new postGoodsAdd(name, price))
                .auth()
                .basic("admin", "secret123")
                .patch(BASE_URL + "goods/" + id)
                .then()
                .log().all()
                .statusCode(200)
                .body("id", is(id))
                .body("name", is(name))
                .body("price", is(Float.valueOf(String.valueOf(price))));
    }

    @Test
    @Tag("regress")
    void patchGoodsIdCheckStatusCode404() {

        given()
                .contentType(ContentType.JSON)
                .body("")
                .auth()
                .basic("admin", "secret123")
                .patch(BASE_URL + "goods/")
                .then()
                .log().all()
                .statusCode(404)
                .body("error", is("Not Found"));
    }

    @Test
    @Tag("regress")
    void getGoodsListCheckStatusCode200() {

        given()
                .queryParam("page", 100000)
                .queryParam("size", 100)
                .get(BASE_URL + "goods/list")
                .then()
                .log().all()
                .statusCode(200)
                .body("goods", equalTo(notNullValue()));
    }
}

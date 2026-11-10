import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;

public class RestAssuredTaskOneTest {

    private static final String BASE_URL = "http://localhost:8080/";

    public record postGoodsAdd(String name, Double price) { }

    private RequestSpecification basic = new RequestSpecBuilder()
            .setBaseUri("http://localhost:8080/")
            .log(LogDetail.ALL)
            .build();

    @Test
    @Tag("regress")
    void givenWhenThenTest() {
        given()
                .spec(basic)
                .queryParam("page", 0)
                .queryParam("size", 1)
                .get("/goods/list")
                .then()
                .log().all()
                .statusCode(200)
                .body("goods", is(empty()));
    }

    @Test
    @Tag("regress")
    void requestSpecificationTest() {
        given()
                .queryParam("page", 0)
                .queryParam("size", 1)
                .get(BASE_URL + "goods/list")
                .then()
                .log().all()
                .statusCode(200)
                .body("goods", is(empty()));
    }

    @Test
    @Tag("regress")
    void postGoodsAddCheckRestAssured() {

        given()
                .contentType(ContentType.JSON)
                .body(new postGoodsAdd("Хлеб", 12.50))
                .auth()
                .basic("admin", "secret123")
                .post(BASE_URL + "goods/add")
                .then()
                .log().all()
                .statusCode(200);

        given()
                .queryParam("page", 0)
                .queryParam("size", 10)
                .get(BASE_URL + "goods/list")
                .then()
                .log().all()
                .statusCode(200)
                .body("goods[0].name", equalTo("Хлеб"))
                .body("goods[0].price", equalTo(12.5F));
    }

    @Test
    @Tag("regress")
    void requestSpecificationCheckJunit() {

        given()
                .contentType(ContentType.JSON)
                .body(new postGoodsAdd("Мука", 19.30))
                .auth()
                .basic("admin", "secret123")
                .post(BASE_URL + "goods/add")
                .then()
                .log().all()
                .statusCode(200);

        String responseBody = given()
                .queryParam("page", 0)
                .queryParam("size", 10)
                .get(BASE_URL + "goods/list")
                .then()
                .log().all()
                .statusCode(200)
                .extract().asString();

        assertThat(responseBody).isEqualTo("{\"goods\":[{\"id\":1,\"name\":\"Мука\",\"price\":19.3}]}");
    }
}

package com.petstore.test.store.get_order;

import com.petstore.data.OrderRepository;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;

public class StatusCodeTest {

    @BeforeClass
    public void setRequestSpec(){
        RequestSpecification requestSpec = new RequestSpecBuilder()
                .setBaseUri("http://192.168.99.100/")
                .setPort(8080)
                .setBasePath("api/v3/store/order")
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();
        RestAssured.requestSpecification = requestSpec;

        given().spec(requestSpecification).body(OrderRepository.validOrder()).
        when().post().
        then().statusCode(200);
    }

    @AfterClass
    public void cleanAll(){
        given()
                .spec(requestSpecification)
                .pathParam("id", 1).
        when()
                .delete("/{id}").
        then()
                .statusCode(200);
    }

    @Test
    public void get_order_returns_200(){
        given().
                spec(requestSpecification).
                pathParam("id", 1).
        when().
                get("/{id}").
        then().
                statusCode(200);
    }

    @Test
    public void get_order_returns_404() {
        given()
                .spec(requestSpecification)
                .pathParam("id", 10000).
        when()
                .get("/{id}").
        then()
                .statusCode(404);
    }

    @Test
    public void get_order_returns_400() {
        given()
                .spec(requestSpecification).
        when()
                .get("/id").
        then()
                .statusCode(400);
    }

    @Test
    public void get_order_returns_405() {
        given()
                .spec(requestSpecification).
        when()
                .get("/").
        then()
                .statusCode(405);
    }
}

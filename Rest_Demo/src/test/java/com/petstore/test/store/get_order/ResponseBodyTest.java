package com.petstore.test.store.get_order;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;
import static org.hamcrest.Matchers.equalTo;

public class ResponseBodyTest {

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
    }

    @Test
    public void getOrderByNonExistent_Id() {
        given().
                spec(requestSpecification).
                pathParam("id", 300).
                when().
                get("/{id}").
                then().
                body(equalTo("Order not found"));
    }

    @Test
    public void getOrderByInvalid_Id() {
        given().
                spec(requestSpecification).
                pathParam("id", "'300'").
                when().
                get("/{id}").
                then().
                body(Matchers.containsStringIgnoringCase("input error"));
    }
}
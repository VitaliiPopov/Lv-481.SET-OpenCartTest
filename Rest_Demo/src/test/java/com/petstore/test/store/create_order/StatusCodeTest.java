package com.petstore.test.store.create_order;

import com.petstore.data.OrderRepository;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;

public class StatusCodeTest {

    @BeforeClass
    public void setRequestSpec() {
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
}

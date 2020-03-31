package com.petstore.test.user.put_user;

import com.petstore.data.OrderRepository;
import com.petstore.data.User;
import com.petstore.data.UserRepository;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;

public class ResponseUser {

    @BeforeClass
    public void createNewUser() {

        RequestSpecification requestSpec = new RequestSpecBuilder()
                .setBaseUri("http://192.168.99.100/")
                .setPort(8080)
                .setBasePath("api/v3")
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();

        RestAssured.requestSpecification = requestSpec;

        given().spec(requestSpecification).body(UserRepository.spongeBob()).
                when().post("/user").
                then().statusCode(200);
    }

    @Test
    public void getUser() {

        given()
                .spec(requestSpecification)
                .pathParam("username", UserRepository.spongeBob().getUsername()).
        when()
                .get("/user/{username}").
        then()
                .statusCode(200);
    }

    @AfterClass
    public void deleteUser() {

    }
}

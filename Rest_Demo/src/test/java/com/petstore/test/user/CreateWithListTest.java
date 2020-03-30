package com.petstore.test.user;

import com.petstore.data.UserRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class CreateWithListTest {

    @Test
    public void checkCreateWithListFunction(){
        RestAssured.baseURI = "http://192.168.99.100:8080/api/v3/";

        Response response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                //.body(new File("response_1585550600599.json"))
                .body(UserRepository.listOfValidUsers())
                .post("/user/createWithList");

        System.out.println(response.getStatusCode());
        System.out.println(response.getBody().asString());
    }

}

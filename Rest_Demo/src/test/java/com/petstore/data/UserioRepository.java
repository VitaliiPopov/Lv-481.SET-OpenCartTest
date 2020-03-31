package com.petstore.data;

import java.util.ArrayList;
import java.util.List;

public class UserioRepository {
    private UserioRepository() {
    }

//    public static Userio defaultProduct() {
//
//    }



    public static Userio wrongUserStatham() {
        return new Userio(100,
                "aaaa",
                "aaaa",
                "aaaa",
                "aaaa@gmail.com",
                "aaaa",
                "1234",
                1);
    }

    public static Userio userForPost() {
        return new Userio(99,
                "userForPost",
                "firstName",
                "aaaa",
                "aaaa@gmail.com",
                "aaaa",
                "1",
                1);
    }

    public static Userio userForPut(){
        return new Userio(99,
                "Bob",
                "Boben",
                "Birkly",
                "bobboben@birkly.com",
                "bbb2000",
                "52165",
                1);
    }

//    public static List<User> listOfValidUsers(){
//        List users = new ArrayList();
//        users.add(userStatham());
//        return users;
//    }

}


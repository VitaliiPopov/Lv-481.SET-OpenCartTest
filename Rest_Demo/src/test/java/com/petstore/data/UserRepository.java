package com.petstore.data;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    private UserRepository() {
    }


    public static User userStatham() {
        return new User(4,
                "Jason",
                "Statham",
                "statham@gmail.com",
                "bigboy",
                "7777777",
                1);
    }
    public static User emptyUserStatham() {
        return new User(Integer.valueOf(""),
                "",
                "",
                "",
                "",
                "",
                Integer.valueOf(""));
    }

    public static User wrongUserStatham() {
        return new User(100,
                "aaaa",
                "aaaa",
                "aaaa@gmail.com",
                "aaaa",
                "1234",
                1);
    }

    public static User newUserBob(){
        return new User(99,
                "Bob",
                "Boben",
                "Birkly",
                "bobboben@birkly.com",
                "bbb2000",
                "52165",
                1);
    }

    public static List<User> listOfValidUsers(){
        List users = new ArrayList();
        users.add(userStatham());
        return users;
    }

}

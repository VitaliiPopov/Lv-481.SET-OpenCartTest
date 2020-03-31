package com.petstore.data;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    private UserRepository() {
    }

    public static User defaultProduct() {
        return userStatham();
    }

    public static User userStatham() {
        return new User(4,
                "Jason",
                "Statham",
                "statham@gmail.com",
                "bigboy",
                "7777777",
                "52114",
                1);
    }

    public static User emptyUserStatham() {
        return new User(Integer.valueOf(""),
                "",
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
                "aaaa",
                "aaaa@gmail.com",
                "aaaa",
                "1234",
                1);
    }

    public static User spongeBob() {
        return new User(99,
                "spongeBob",
                "firstName",
                "aaaa",
                "aaaa@gmail.com",
                "aaaa",
                "1",
                1);
    }

    public static List<User> listOfValidUsers(){
        List users = new ArrayList();
        users.add(userStatham());
        return users;
    }

}

package com.opencart.data.users;

public final class UserRepository {

    private static volatile UserRepository instance = null;

    private UserRepository() {
    }

    public static UserRepository get() {
        if (instance == null) {
            synchronized (UserRepository.class) {
                if (instance == null) {
                    instance = new UserRepository();
                }
            }
        }
        return instance;
    }

    public IUser defaultUser() {
        return firstCustomer();
    }

    public IUser firstCustomer() {
        return User.get()
                .setFirstName("Yurii")
                .setLastName("test")
                .setEmail("tttt@gmail.com")
                .setTelephone("0960558463")
                .setPassword("test")
                .setMainAddress("affdd")
                .setCity("222112")
                .setCounty("Ukraine")
                .setRegion("Crimea")
                .build();
    }

    public IUser secondCustomer() {
        return User.get()
                .setFirstName("yellow")
                .setLastName("yellow")
                .setEmail("yellow@yellow.com")
                .setTelephone("5214")
                .setPassword("yellowyellow")
                .setMainAddress("tyta")
                .setCity("tamka")
                .setCounty("United Kingdom")
                .setRegion("Devon")
                .build();
    }
}
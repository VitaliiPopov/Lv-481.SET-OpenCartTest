package com.petstore.data;

public final class ConstantVariables {

    public static final String API_URL = System.getenv("API_URL");
    public static final int API_PORT = Integer.parseInt(System.getenv("API_PORT"));
    public static final String API_PATH = System.getenv("API_PATH");
}

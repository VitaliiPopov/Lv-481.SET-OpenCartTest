package com.petstore.data;

public class OrderRepository {

    private OrderRepository(){

    }

    public static Order validOrder(){
        return new Order(1, 10, 7, "2020-03-30T11:44:16.23Z", "approved", true);
    }

    public static Order invalidOrder_WrongId(){
        return new Order(-987, 10, 7, "2020-03-30T11:44:16.23Z", "approved", true);
    }

    public static Order invalidOrder_WrongPetId(){
        return new Order(1, -1000, 7, "2020-03-30T11:44:16.23Z", "approved", true);
    }

    public static Order invalidOrder_WrongQuantity(){
        return new Order(1, 10, -35, "2020-03-30T11:44:16.23Z", "approved", true);
    }

    public static Order invalidOrder_WrongShipDate(){
        return new Order(1, 10, -35, "25-0003-30T11:44:16.23Z", "approved", true);
    }

    public static Order invalidOrder_WrongStatus(){
        return new Order(1, 10, -35, "25-0003-30T11:44:16.23Z", "unknown", true);
    }

    public static Order invalidOrder_WrongComplete(){
        return new Order(1, 10, -35, "25-0003-30T11:44:16.23Z", "unknown", false);
    }
}

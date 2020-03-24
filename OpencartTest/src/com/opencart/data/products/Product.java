package com.opencart.data.products;

import com.opencart.data.Currencies;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Product {

    private String name;
    private String description;
    private Map<Currencies, BigDecimal> price;

    public Product(String name, String description) {
        this.name = name;
        this.description = description;
        price = new HashMap<>();
    }

    public Product addPrice(Currencies currency, String price) {
        BigDecimal number = new BigDecimal(price);
        getPrice().put(currency, number);
        return this;
    }

    //Getters

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Map<Currencies, BigDecimal> getPrice() {
        return price;
    }

    public BigDecimal getPrice(Currencies currency) {
        return getPrice().get(currency);
    }
}
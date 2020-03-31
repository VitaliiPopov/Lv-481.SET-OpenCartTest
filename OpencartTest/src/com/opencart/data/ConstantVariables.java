package com.opencart.data;

import java.math.BigDecimal;

public interface ConstantVariables {
    public static String BROWSER_NAME = "chrome";

    //public static String BROWSER_NAME = "firefox";
    //public static String BROWSER_NAME = "edge";
    //
//    public static final String URL = "http://52.232.108.109/";
//    public static final String ADMIN_URL = "http://52.232.108.109/admin";
//    public static final String CART_PAGE_URL = "http://52.232.108.109/index.php?route=checkout/cart";

    //
    public static final String URL = System.getenv("URL");
    public static final String ADMIN_URL = System.getenv("ADMIN_URL");
    public static final String MACBOOK_DESCRIPTION = "Intel Core 2 Duo processor Powered by an Intel Core 2 Duo processor at speeds up to 2.1..";
    //
    public static BigDecimal ONE_EUR = new BigDecimal(0.78460002);
    public static BigDecimal ONE_GBP = new BigDecimal(0.61250001);
}


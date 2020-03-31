package com.opencart.data;

import java.math.BigDecimal;

public interface ConstantVariables {
    public static String BROWSER_NAME = "chrome";
    //
    public static final String URL = System.getenv("URL");
    public static final String ADMIN_URL = System.getenv("ADMIN_URL");
    public static final String MACBOOK_DESCRIPTION = "Intel Core 2 Duo processor Powered by an Intel Core 2 Duo processor at speeds up to 2.1..";
    //
    public static BigDecimal ONE_EUR = new BigDecimal(0.78460002);
    public static BigDecimal ONE_GBP = new BigDecimal(0.61250001);
}


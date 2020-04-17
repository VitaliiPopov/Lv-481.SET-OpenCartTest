package com.opencart.data;

public final class ConstantVariables {

    private ConstantVariables() {
        throw new IllegalStateException("Utility class");
    }
    public static final String HUB_URL = "http://20.43.56.98:4444/wd/hub";
    public static final String URL = System.getenv("URL");
    public static final String ADMIN_URL = System.getenv("ADMIN_URL");
    public static final String MACBOOK_DESCRIPTION = "Intel Core 2 Duo processor Powered by an Intel Core 2 Duo processor at speeds up to 2.1..";
}


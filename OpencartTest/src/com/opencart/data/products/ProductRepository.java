package com.opencart.data.products;

import com.opencart.data.ConstantVariables;
import com.opencart.data.Currencies;

public final class ProductRepository {

    private ProductRepository() {
    }

    public static Product defaultProduct() {
        return macBook();
    }

    public static Product macBook() {
        return new Product("MacBook", ConstantVariables.MACBOOK_DESCRIPTION)
                .addPrice(Currencies.US_DOLLAR, "500.00")
                .addPrice(Currencies.EURO, "392.30")
                .addPrice(Currencies.POUND_STERLING, "306.25");
    }
}
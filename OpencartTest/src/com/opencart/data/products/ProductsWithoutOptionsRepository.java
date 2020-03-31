package com.opencart.data.products;

import com.opencart.data.ConstantVariables;
import com.opencart.data.Currencies;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProductsWithoutOptionsRepository implements ConstantVariables{

    private ProductsWithoutOptionsRepository() {
    }

    public static List<ProductWithoutOptions> productWithoutOptions() {
        List<ProductWithoutOptions> productWithoutOptionsList = new ArrayList<>();

        productWithoutOptionsList.add(new ProductWithoutOptions("HTC TOUCH HD", 28));

        productWithoutOptionsList.add(new ProductWithoutOptions("iMac", 41));

        productWithoutOptionsList.add(new ProductWithoutOptions("iPhone", 40));

        productWithoutOptionsList.add(new ProductWithoutOptions("iPod Classic", 48));

        productWithoutOptionsList.add(new ProductWithoutOptions("iPod Nano", 36));

        productWithoutOptionsList.add(new ProductWithoutOptions("iPod Shuffle", 34));

        productWithoutOptionsList.add(new ProductWithoutOptions("iPod Touch", 32));

        productWithoutOptionsList.add(new ProductWithoutOptions("MacBook", 43));

        productWithoutOptionsList.add(new ProductWithoutOptions("MacBook Air", 44));

        productWithoutOptionsList.add(new ProductWithoutOptions("MacBook Pro", 45));

        productWithoutOptionsList.add(new ProductWithoutOptions("Nikon D300", 31));

        productWithoutOptionsList.add(new ProductWithoutOptions("Palm Treo Pro", 29));

        productWithoutOptionsList.add(new ProductWithoutOptions("Samsung SyncMaster 941BW", 33));

        productWithoutOptionsList.add(new ProductWithoutOptions("Sony VAIO", 46));

        productWithoutOptionsList.add(new ProductWithoutOptions("Samsung Galaxy Tab 10.1", 49));

        return productWithoutOptionsList;
    }
}

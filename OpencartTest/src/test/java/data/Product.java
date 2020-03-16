package data;

import java.util.HashMap;
import java.util.Map;

public class Product {

    //
    private String name;
    //Currencies
    private Map<Currencies, String> prices;

    public Product(String name, String priceDollarExTax) {
        this.name = name;
        prices = new HashMap<Currencies, String>();
    }

    //Geters and Setters

    //name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //Price
    public Map<Currencies, String> getPrices() {
        return prices;
    }

    public void setPrices(Map<Currencies, String> prices) {
        this.prices = prices;
    }
}

package model.entities;

import java.math.BigDecimal;

public class Product {
    private String description;
    private String price;
    private BigDecimal value;

    public Product(String description, String price) {
        this.description = description;
        this.price = price;
        value = getValue(price);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }


    //Data parse for transform String in bigdecimal

    public BigDecimal getValue(String price) {
        return new BigDecimal(price.substring(price.indexOf('$'), price.indexOf('\n')).replace("$", "")
                .replaceAll("\\.", "") + "." +
                price.substring(price.indexOf('\n') + 1, price.indexOf('\n') + 3).trim());
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Produto [descrição =" + description + ", valor= " + price + "]";
    }
}

package model.entities;

import java.math.BigDecimal;

public class Product {
    private String description;
    private String price;
    private BigDecimal value;

    public Product(String description, String price, BigDecimal value) {
        this.description = description;
        this.price = price;
        this.value = value;
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

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
}

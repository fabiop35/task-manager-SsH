package com.ssh.model;

import java.math.BigDecimal;

public class Purchase {

    private int id;
    private String product;
    private BigDecimal price;

    public int getId() {
        return this.id;
    }

    public String getProduct() {
        return product;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}

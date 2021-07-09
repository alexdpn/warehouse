package com.warehouse.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public abstract class Product {

    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate expiry;
    private Type type;
    private BigDecimal price;

    public Product() {}

    public Product(LocalDate expiry, Type type, BigDecimal price) {
        this.expiry = expiry;
        this.type = type;
        this.price = price;
    }

    public LocalDate getExpiry() {
        return expiry;
    }

    public Type getType() {
        return type;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setExpiry(LocalDate expiry) {
        this.expiry = expiry;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return getExpiry().equals(product.getExpiry()) && getType() == product.getType() && getPrice().equals(product.getPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getExpiry(), getType(), getPrice());
    }
}

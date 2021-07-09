package com.warehouse.model.food;

import com.warehouse.model.Product;
import com.warehouse.model.Type;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Food extends Product {
    private String color;
    private double weight;

    protected Food() {}

    protected Food (double weight, LocalDate expiry, Type type, BigDecimal price, String color) {
        super(expiry, type, price);
        this.weight = weight;
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Food food = (Food) o;
        return Double.compare(food.getWeight(), getWeight()) == 0 && getColor().equals(food.getColor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getColor(), getWeight());
    }
}

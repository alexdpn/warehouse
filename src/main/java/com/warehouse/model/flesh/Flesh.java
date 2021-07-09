package com.warehouse.model.flesh;

import com.warehouse.model.Product;
import com.warehouse.model.Type;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Flesh extends Product {
    private double weight;

    public Flesh() {}

    protected Flesh (double weight, LocalDate expiry, Type type, BigDecimal price) {
        super(expiry, type, price);
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if(!super.equals(o))
            return false;
        if (this == o) return true;
        if (!(o instanceof Flesh)) return false;
        Flesh flesh = (Flesh) o;
        return Double.compare(flesh.getWeight(), getWeight()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getWeight());
    }
}

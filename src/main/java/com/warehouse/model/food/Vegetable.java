package com.warehouse.model.food;

import com.warehouse.model.Type;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Vegetable extends Food {
    public Vegetable() {}

    public Vegetable (double weight, LocalDate expiry, BigDecimal price, String color) {
        super(weight, expiry, Type.VEGETABLE, price, color);
    }
}

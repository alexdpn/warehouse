package com.warehouse.model.food;

import com.warehouse.model.Type;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Fruit extends Food {
    public Fruit () {}

    public Fruit (double weight, LocalDate expiry, BigDecimal price, String color) {
        super(weight, expiry, Type.FRUIT, price, color);
    }
}
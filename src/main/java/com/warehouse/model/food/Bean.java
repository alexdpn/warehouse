package com.warehouse.model.food;

import com.warehouse.model.Type;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Bean extends Food {
    public Bean() {}

    public Bean (double weight, LocalDate expiry, BigDecimal price, String color) {
        super(weight, expiry, Type.BEAN, price, color);
    }
}

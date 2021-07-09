package com.warehouse.model.food;

import com.warehouse.model.Type;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Rice extends Food {
    public Rice() {}

    public Rice (double weight, LocalDate expiry, BigDecimal price, String color) {
        super(weight, expiry, Type.RICE, price, null);
    }
}

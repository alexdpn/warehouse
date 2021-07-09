package com.warehouse.model.flesh;

import java.math.BigDecimal;
import java.time.LocalDate;

import static com.warehouse.model.Type.BEEF;

public class Beef extends Flesh {
    public Beef () {}

    public Beef (double weight, LocalDate expiry, BigDecimal price) {
        super(weight, expiry, BEEF, price);
    }
}

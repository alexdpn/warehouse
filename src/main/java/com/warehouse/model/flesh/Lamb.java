package com.warehouse.model.flesh;

import java.math.BigDecimal;
import java.time.LocalDate;

import static com.warehouse.model.Type.LAMB;

public class Lamb extends Flesh {
    public Lamb() {}

    public Lamb (double weight, LocalDate expiry, BigDecimal price) {
        super(weight, expiry, LAMB, price);
    }
}

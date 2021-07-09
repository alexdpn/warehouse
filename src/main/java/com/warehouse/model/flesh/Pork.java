package com.warehouse.model.flesh;

import java.math.BigDecimal;
import java.time.LocalDate;

import static com.warehouse.model.Type.PORK;

public class Pork extends Flesh {
    public Pork() {}

    public Pork (double weight, LocalDate expiry, BigDecimal price) {
        super(weight, expiry, PORK, price);
    }


}

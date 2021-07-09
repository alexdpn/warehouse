package com.warehouse.model.flesh;

import java.math.BigDecimal;
import java.time.LocalDate;

import static com.warehouse.model.Type.POULTRY;

public class Poultry extends Flesh {
    public Poultry() {}

    public Poultry (double weight, LocalDate expiry, BigDecimal price) {
        super(weight, expiry, POULTRY, price);
    }
}
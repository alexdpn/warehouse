package com.warehouse.model;

import com.warehouse.model.flesh.Beef;
import com.warehouse.model.flesh.Lamb;
import com.warehouse.model.flesh.Pork;
import com.warehouse.model.flesh.Poultry;
import com.warehouse.model.food.Bean;
import com.warehouse.model.food.Fruit;
import com.warehouse.model.food.Rice;
import com.warehouse.model.food.Vegetable;

public enum Type {
    FRUIT(Fruit.class),
    VEGETABLE(Vegetable.class),
    BEAN(Bean.class),
    RICE(Rice.class),
    POULTRY(Poultry.class),
    BEEF(Beef.class),
    PORK(Pork.class),
    LAMB(Lamb.class);

    private Class<? extends Product> correspondingClass;

    Type (Class<? extends Product> correspondingClass){
        this.correspondingClass = correspondingClass;
    }

    public Class<? extends Product> getCorrespondingClass() {
        return correspondingClass;
    }
}

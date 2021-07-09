package com.warehouse.model;

import java.util.Objects;

public class WeightValue {
    private Type type;
    private double totalWeight;

    public WeightValue() {
    }

    public WeightValue(Type type, double totalWeight) {
        this.type = type;
        this.totalWeight = totalWeight;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setTotalWeight(double totalWeight) {
        this.totalWeight = totalWeight;
    }

    public Type getType() {
        return type;
    }

    public double getTotalWeight() {
        return totalWeight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeightValue that = (WeightValue) o;
        return Double.compare(that.getTotalWeight(), getTotalWeight()) == 0 && getType() == that.getType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getType(), getTotalWeight());
    }
}

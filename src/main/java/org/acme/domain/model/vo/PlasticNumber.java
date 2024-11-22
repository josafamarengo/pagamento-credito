package org.acme.domain.model.vo;

import java.util.Objects;

public class PlasticNumber {
    private final Long value;

    public PlasticNumber(Long value) {
        this.value = value;
    }

    public Long get() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlasticNumber plastic = (PlasticNumber) o;
        return Objects.equals(value, plastic.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value.toString();
    }
}

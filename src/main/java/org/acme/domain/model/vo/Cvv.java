package org.acme.domain.model.vo;

import java.util.Objects;

public class Cvv {
    private final Integer value;

    public Cvv(Integer value) {
        this.value = value;
    }

    public Integer get() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cvv cvv = (Cvv) o;
        return Objects.equals(value, cvv.value);
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

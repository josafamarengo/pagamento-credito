package org.acme.domain.model.vo;

import java.util.Objects;

public class CpfCnpj {
    private final String value;

    public CpfCnpj(String value) {
        this.value = value;
    }

    public String get() {
        return  value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CpfCnpj cpfCnpj = (CpfCnpj) o;
        return Objects.equals(value, cpfCnpj.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value;
    }
}

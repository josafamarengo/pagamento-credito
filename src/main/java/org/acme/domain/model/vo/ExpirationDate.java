package org.acme.domain.model.vo;

import java.time.YearMonth;
import java.util.Objects;

public class ExpirationDate {
    private final YearMonth value;

    public ExpirationDate(YearMonth value) {
        this.value = value;
    }

    public YearMonth get() {
        return value;
    }

    public boolean isExpired() {
        return YearMonth.now().isAfter(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExpirationDate date = (ExpirationDate) o;
        return Objects.equals(value, date.value);
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

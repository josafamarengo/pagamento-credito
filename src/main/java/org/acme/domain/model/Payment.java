package org.acme.domain.model;

import lombok.Builder;
import lombok.Getter;
import org.acme.common.consts.PersonType;

import java.math.BigDecimal;
import java.time.YearMonth;

@Getter
@Builder
public class Payment {
    private String plasticNumber;
    private PersonType personType;
    private String cpfCnpj;
    private YearMonth expirationDate;
    private String cvv;
    private BigDecimal amount;

    public boolean isExpired() {
        YearMonth currentMonth = YearMonth.now();
        return currentMonth.isAfter(this.expirationDate);
    }
}

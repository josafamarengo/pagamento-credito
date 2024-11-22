package org.acme.fixtures;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.Data;
import org.acme.infrastructure.persistence.entity.PaymentEntity;

import java.math.BigDecimal;
import java.time.YearMonth;

@ApplicationScoped
@Data
public class PaymentFixture {

    String plasticNumber;
    int personType;
    String cpfCnpj;
    YearMonth expiration;
    String cvv;
    BigDecimal amount;

    public PaymentFixture() {
        this.plasticNumber = "1234567890123456";
        this.personType = 1;
        this.cpfCnpj = "12345678900";
        this.expiration = YearMonth.now();
        this.cvv = "123";
        this.amount = BigDecimal.valueOf(100.00);
    }

    public PaymentEntity createPayment() {

        return new PaymentEntity(
                this.plasticNumber,
                this.personType,
                this.cpfCnpj,
                this.expiration,
                this.cvv,
                this.amount
        );
    }
}

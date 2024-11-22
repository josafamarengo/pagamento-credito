package org.acme.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.YearMonth;

@Getter
@Setter
@Entity
@Table(name="PAGAMENTOS")
public class PaymentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NR_PAGAMENTO", nullable = false)
    private Long paymentId;

    @Column(name = "NR_CARTAO", nullable = false, length = 16)
    private String plasticNumber;

    @Column(name = "TIPO_PESSOA", nullable = false)
    private int personType;

    @Column(name = "NR_DOCUMENTO", nullable = false)
    private String cpfCnpj;

    @Column(name = "DATA_VENCIMENTO", nullable = false)
    private YearMonth expiration;

    @Column(name = "CVV", nullable = false)
    private String cvv;

    @Column(name = "DATA", nullable = false)
    private final LocalDateTime time = LocalDateTime.now();

    @Column(name = "VALOR", nullable = false)
    private BigDecimal amount;

    public PaymentEntity() {}

    public PaymentEntity(
            String plasticNumber,
            int personType,
            String cpfCnpj,
            YearMonth expiration,
            String cvv,
            BigDecimal amount
    ) {
        this.plasticNumber = plasticNumber;
        this.personType = personType;
        this.cpfCnpj = cpfCnpj;
        this.expiration = expiration;
        this.cvv = cvv;
        this.amount = amount;
    }
}

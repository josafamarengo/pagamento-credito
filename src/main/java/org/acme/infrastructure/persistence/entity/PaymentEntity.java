package org.acme.infrastructure.persistence.entity;

import jakarta.persistence.*;
import org.acme.common.enums.PersonType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "PAGAMENTOS")
public class PaymentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NR_PAGAMENTO", nullable = false)
    private Long paymentId;

    @Column(name = "NR_CARTAO", nullable = false, length = 16)
    private Long plasticNumber;

    @Column(name = "TIPO_PESSOA", nullable = false)
    private PersonType personType;

    @Column(name = "NR_DOCUMENTO", nullable = false)
    private String cpfCnpj;

    @Column(name = "DATA_VENCIMENTO", nullable = false)
    private String expiration;

    @Column(name = "CVV", nullable = false)
    private Integer cvv;

    @Column(name = "DATA", nullable = false)
    private final LocalDateTime time = LocalDateTime.now();

    @Column(name = "VALOR", nullable = false)
    private BigDecimal amount;

    public PaymentEntity() {}

    public PaymentEntity(
            final Long plasticNumber,
            final PersonType personType,
            final String cpfCnpj,
            final String expiration,
            final Integer cvv,
            final BigDecimal amount
    ) {
        this.plasticNumber = plasticNumber;
        this.personType = personType;
        this.cpfCnpj = cpfCnpj;
        this.expiration = expiration;
        this.cvv = cvv;
        this.amount = amount;
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public Long getPlasticNumber() {
        return plasticNumber;
    }

    public PersonType getPersonType() {
        return personType;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public String getExpiration() {
        return expiration;
    }

    public Integer getCvv() {
        return cvv;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}

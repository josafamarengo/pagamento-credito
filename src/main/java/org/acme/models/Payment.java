package org.acme.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.acme.commons.Month;
import org.acme.commons.PersonType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name="PAGAMENTOS")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NR_PAGAMENTO")
    private Long paymentId;
    @NotNull
    @Column(name = "NR_CARTAO")
    private String plasticNumber;
    @NotNull
    @Column(name = "TIPO_PESSOA")
    private int personType;
    @NotNull
    @Column(name = "NR_DOCUMENTO")
    private String cpfOrCnpj;
    @NotNull
    @Column(name = "MES_VENCIMENTO")
    private int expirationMonth;
    @NotNull
    @Pattern(regexp = "\\d{4}", message = "O ano de vencimento deve ser um número de 4 dígitos")
    @Column(name = "ANO_VENCIMENTO")
    private int expirationYear;
    @NotNull
    private String cvv;
    @Column(name = "DATA")
    private LocalDateTime time = LocalDateTime.now();
    @NotNull
    @Column(name = "VALOR")
    private BigDecimal amount;

    private Payment(Builder builder) {
        this.plasticNumber = builder.plasticNumber;
        this.personType = builder.personType;
        this.cpfOrCnpj = builder.cpfOrCnpj;
        this.expirationMonth = builder.expirationMonth;
        this.expirationYear = builder.expirationYear;
        this.cvv = builder.cvv;
        this.amount = builder.amount;
    }

    public Payment() {

    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String plasticNumber;
        private int personType;
        private String cpfOrCnpj;
        private int expirationMonth;
        private int expirationYear;
        private String cvv;
        private BigDecimal amount;

        public Builder plasticNumber(String plasticNumber) {
            this.plasticNumber = plasticNumber;
            return this;
        }

        public Builder personType(int personType) {
            this.personType = personType;
            return this;
        }

        public Builder cpfOrCnpj(String cpfOrCnpj) {
            this.cpfOrCnpj = cpfOrCnpj;
            return this;
        }

        public Builder expirationMonth(int expirationMonth) {
            this.expirationMonth = expirationMonth;
            return this;
        }

        public Builder expirationYear(int expirationYear) {
            this.expirationYear = expirationYear;
            return this;
        }

        public Builder cvv(String cvv) {
            this.cvv = cvv;
            return this;
        }

        public Builder amount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public Payment build() {
            return new Payment(this);
        }

    }

    public Long getPaymentId() {
        return paymentId;
    }

    public String getPlasticNumber() {
        return plasticNumber;
    }

    public int getPersonType() {
        return personType;
    }

    public String getCpfOrCnpj() {
        return cpfOrCnpj;
    }

    public int getExpirationMonth() {
        return expirationMonth;
    }

    public int getExpirationYear() {
        return expirationYear;
    }

    public String getCvv() {
        return cvv;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}

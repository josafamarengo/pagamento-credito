package org.acme.models;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.acme.commons.Month;
import org.acme.commons.PersonType;

import java.time.Year;
import java.util.UUID;

@Entity
@Table(name="PAGAMENTOS")
public class Payment extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private Long paymentNumber;
    @NotNull
    private String plasticNumber;
    @NotNull
    private PersonType personType;
    @NotNull
    private String cpfOrCnpj;
    @NotNull
    private Month expirationMonth;
    @NotNull
    private String expirationYear;
    @NotNull
    private String cvv;

    private Payment(Builder builder) {
        this.paymentNumber = builder.paymentNumber;
        this.plasticNumber = builder.plasticNumber;
        this.personType = builder.personType;
        this.cpfOrCnpj = builder.cpfOrCnpj;
        this.expirationMonth = builder.expirationMonth;
        this.expirationYear = builder.expirationYear;
        this.cvv = builder.cvv;
    }

    public Payment() {

    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private UUID id;
        private Long paymentNumber;
        private String plasticNumber;

        private PersonType personType;
        private String cpfOrCnpj;
        private Month expirationMonth;
        private String expirationYear;
        private String cvv;

        public Builder() {
            this.id = UUID.randomUUID();
        }

        public Builder paymentNumber(Long paymentNumber) {
            this.paymentNumber = paymentNumber;
            return this;
        }

        public Builder plasticNumber(String plasticNumber) {
            this.plasticNumber =plasticNumber;
            return this;
        }

        public Builder personType(PersonType personType) {
            this.personType = personType;
            return this;
        }

        public Builder cpfOrCnpj(String cpfOrCnpj) {
            this.cpfOrCnpj = cpfOrCnpj;
            return this;
        }

        public Builder expirationMonth(Month expirationMonth) {
            this.expirationMonth = expirationMonth;
            return this;
        }

        public Builder expirationYear(String expirationYear) {
            this.expirationYear = expirationYear;
            return this;
        }

        public Builder cvv(String cvv) {
            this.cvv = cvv;
            return this;
        }

        public Payment build() {
            return new Payment(this);
        }

    }

    public UUID getId() {
        return id;
    }

    public Long getPaymentNumber() {
        return paymentNumber;
    }

    public String getPlasticNumber() {
        return plasticNumber;
    }

    public PersonType getPersonType() {
        return personType;
    }

    public String getCpfOrCnpj() {
        return cpfOrCnpj;
    }

    public Month getExpirationMonth() {
        return expirationMonth;
    }

    public String getExpirationYear() {
        return expirationYear;
    }

    public String getCvv() {
        return cvv;
    }
}

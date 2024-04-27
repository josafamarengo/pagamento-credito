package org.acme.models;

import org.acme.commons.Month;
import org.acme.commons.PersonType;

import java.time.Year;
import java.util.UUID;

public class Payment {
    private UUID id;
    private String paymentNumber;
    private String plasticNumber;

    private PersonType personType;
    private Document cpfOrCnpj;
    private Month expirationMonth;
    private Year expirationYear;
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

    public static class Builder {
        private UUID id;
        private String paymentNumber;
        private String plasticNumber;

        private PersonType personType;
        private Document cpfOrCnpj;
        private Month expirationMonth;
        private Year expirationYear;
        private String cvv;

        public Builder() {
            this.id = UUID.randomUUID();
        }

        public Builder paymentNumber(String paymentNumber) {
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

        public Builder cpfOrCnpj(Document cpfOrCnpj) {
            this.cpfOrCnpj = cpfOrCnpj;
            return this;
        }

        public Builder expirationMonth(Month expirationMonth) {
            this.expirationMonth = expirationMonth;
            return this;
        }

        public Builder expirationYear(Year expirationYear) {
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

}

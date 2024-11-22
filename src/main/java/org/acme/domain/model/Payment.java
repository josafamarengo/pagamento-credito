package org.acme.domain.model;

import org.acme.common.enums.PersonType;
import org.acme.domain.model.vo.*;

import java.math.BigDecimal;
import java.time.YearMonth;

public class Payment {
    private final PlasticNumber plasticNumber;
    private final PersonType personType;
    private final CpfCnpj cpfCnpj;
    private final ExpirationDate expirationDate;
    private final Cvv cvv;
    private final Amount amount;
  
    public static class Builder {
        private PlasticNumber plasticNumber;
        private PersonType personType;
        private CpfCnpj cpfCnpj;
        private ExpirationDate expirationDate;
        private Cvv cvv;
        private Amount amount;

        
        public Builder plasticNumber(Long plasticNumber) {
            this.plasticNumber = new PlasticNumber(plasticNumber);
            return this;
        }

        public Builder personType(PersonType personType) {
            this.personType = personType;
            return this;
        }

        public Builder cpfCnpj(String cpfCnpj) {
            this.cpfCnpj = new CpfCnpj(cpfCnpj);
            return this;
        }

        public Builder expirationDate(String date) {
            YearMonth expiration = YearMonth.parse(date);
            this.expirationDate = new ExpirationDate(expiration);
            return this;
        }

        public Builder cvv(Integer cvv) {
            this.cvv = new Cvv(cvv);
            return this;
        }

        public Builder amount(BigDecimal amount) {
            this.amount = new Amount(amount);
            return this;
        }

        public Payment build() {
            return new Payment(this);
        }
    }

    private Payment(Builder builder) {
        this.plasticNumber = builder.plasticNumber;
        this.personType = builder.personType;
        this.cpfCnpj = builder.cpfCnpj;
        this.expirationDate = builder.expirationDate;
        this.cvv = builder.cvv;
        this.amount = builder.amount;
    }

    public static Builder builder() {
        return new Builder();
    }

    public PlasticNumber getPlasticNumber() {
        return plasticNumber;
    }

    public PersonType getPersonType() {
        return personType;
    }

    public CpfCnpj getCpfCnpj() {
        return cpfCnpj;
    }

    public ExpirationDate getExpirationDate() {
        return expirationDate;
    }

    public Cvv getCvv() {
        return cvv;
    }

    public Amount getAmount() {
        return amount;
    }
}

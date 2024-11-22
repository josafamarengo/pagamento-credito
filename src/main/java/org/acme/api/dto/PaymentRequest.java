package org.acme.api.dto;

import jakarta.enterprise.context.RequestScoped;
import jakarta.validation.constraints.*;
import org.acme.api.validator.ValidCpfCnpj;
import org.acme.common.consts.PersonType;

import java.math.BigDecimal;
import java.time.YearMonth;

@ValidCpfCnpj
public record PaymentRequest (
        @NotNull(message = "O campo 'plasticNumber' não foi informado.")
        @Pattern(regexp = "\\d{16}", message = "O número do cartão não está no formato correto.")
        String plasticNumber,

        @NotNull(message = "O campo 'personType' não foi informado.")
        Integer personType,

        @NotNull(message = "O campo 'cpfCnpj' não foi informado.")
        String cpfCnpj,

        @NotNull(message = "O campo 'expirationMonth' não foi informado.")
        @Min(value = 1, message = "Mês Inválido")
        @Max(value = 12,message = "Mês Inválido")
        Integer expirationMonth,

        @NotNull(message = "O campo 'expirationYear' não foi informado.")
        @Min(value = 1900, message = "Ano inválido")
        @Max(value = 2100, message = "Ano inválido")
        Integer expirationYear,

        @NotNull(message = "O campo 'cvv' não foi informado.")
        @Pattern(regexp = "\\d{3,4}", message = "CVV não está no formato correto.")
        String cvv,

        @NotNull(message = "O campo 'amount' não foi informado.")
        @Positive(message = "O valor de amount deve ser maior que zaro.")
        @Pattern(regexp = "\\d+(\\.\\d{1,2})", message = "Valor deve ser um número com até duas casas decimais.")
        BigDecimal amount
) {
        public YearMonth getExpiration() {
                return YearMonth.of(this.expirationYear, this.expirationMonth);
        }

        public PersonType getPersonType() {
                return PersonType.of(this.personType);
        }
}

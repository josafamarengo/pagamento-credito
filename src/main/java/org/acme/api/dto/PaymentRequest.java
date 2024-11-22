package org.acme.api.dto;

import jakarta.validation.constraints.*;
import org.acme.api.validator.ValidCpfCnpj;

@ValidCpfCnpj
public record PaymentRequest (
        @NotNull(message = "O campo 'plasticNumber' não foi informado.")
        @Pattern(regexp = "\\d{16}", message = "O número do cartão não está no formato correto.")
        String plasticNumber,

        @NotNull(message = "O campo 'personType' não foi informado.")
        Integer personType,

        @NotNull(message = "O campo 'cpfCnpj' não foi informado.")
        String cpfCnpj,

        @NotNull(message = "O campo 'expirationDate' não foi informado.")
        @Pattern(regexp = "^(\\d{4}-(0[1-9]|1[0-2]))$")
        String expirationDate,

        @NotNull(message = "O campo 'cvv' não foi informado.")
        @Pattern(regexp = "\\d{3,4}", message = "CVV não está no formato correto.")
        String cvv,

        @NotNull(message = "O campo 'amount' não foi informado.")
        @Positive(message = "O valor de amount deve ser maior que zero.")
        @Pattern(regexp = "\\d+(\\.\\d{1,2})", message = "Valor deve ser um número com até duas casas decimais.")
        String amount
) {}

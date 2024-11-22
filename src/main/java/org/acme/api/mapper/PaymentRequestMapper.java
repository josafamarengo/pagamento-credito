package org.acme.api.mapper;

import jakarta.enterprise.context.RequestScoped;

import java.math.BigDecimal;

import org.acme.api.dto.PaymentRequest;
import org.acme.common.enums.PersonType;
import org.acme.domain.model.Payment;

@RequestScoped
public class PaymentRequestMapper {
    public Payment toDomain(PaymentRequest request) {
        return Payment.builder()
                .plasticNumber(Long.valueOf(request.plasticNumber()))
                .expirationDate(request.expirationDate())
                .cvv(Integer.parseInt(request.cvv()))
                .personType(PersonType.of(request.personType()))
                .cpfCnpj(request.cpfCnpj())
                .amount(new BigDecimal(request.amount()))
                .build();
    }
}

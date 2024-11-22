package org.acme.api;

import jakarta.enterprise.context.RequestScoped;
import org.acme.api.dto.PaymentRequest;
import org.acme.domain.model.Payment;

@RequestScoped
public class PaymentRequestMapper {
    Payment toDomain(PaymentRequest request) {
        return Payment.builder()
                .plasticNumber(request.plasticNumber())
                .expirationDate(request.getExpiration())
                .cvv(request.cvv())
                .personType(request.getPersonType())
                .cpfCnpj(request.cpfCnpj())
                .amount(request.amount())
                .build();
    }
}

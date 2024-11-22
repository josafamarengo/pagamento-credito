package org.acme.infrastructure.persistence.mapper;

import jakarta.enterprise.context.ApplicationScoped;
import org.acme.common.consts.PersonType;
import org.acme.domain.model.Payment;
import org.acme.infrastructure.persistence.entity.PaymentEntity;

@ApplicationScoped
public class PaymentEntityMapper {
    public PaymentEntity toEntity(Payment payment) {
        PaymentEntity paymentEntity = new PaymentEntity();
        paymentEntity.setPlasticNumber(payment.getPlasticNumber());
        paymentEntity.setPersonType(payment.getPersonType().getCode());
        paymentEntity.setCpfCnpj(payment.getCpfCnpj());
        paymentEntity.setExpiration(payment.getExpirationDate());
        paymentEntity.setCvv(payment.getCvv());
        paymentEntity.setAmount(payment.getAmount());
        return paymentEntity;
    }

    public Payment toDomain(PaymentEntity entity) {
        return Payment.builder()
        		.plasticNumber(entity.getPlasticNumber())
        		.personType(PersonType.of(entity.getPersonType()))
        		.cpfCnpj(entity.getCpfCnpj())
        		.expirationDate(entity.getExpiration())
        		.cvv(entity.getCvv())
        		.amount(entity.getAmount())
        		.build();
    }
}

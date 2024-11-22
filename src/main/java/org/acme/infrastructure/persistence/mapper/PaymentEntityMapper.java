package org.acme.infrastructure.persistence.mapper;

import jakarta.enterprise.context.ApplicationScoped;

import org.acme.domain.model.Payment;
import org.acme.infrastructure.persistence.entity.PaymentEntity;

/**
 * Mapper responsável por converter objetos entre a camada de domínio e a camada
 * de persistência.
 * Esta classe realiza o mapeamento de um objeto {@link Payment} para um
 * {@link PaymentEntity} e vice-versa.
 * 
 * @see Payment
 * @see PaymentEntity
 */
@ApplicationScoped
public final class PaymentEntityMapper {

    /**
     * Converte um objeto {@link Payment} para a entidade {@link PaymentEntity}.
     * 
     * @param payment O objeto {@link Payment} a ser convertido.
     * @return O objeto {@link PaymentEntity} gerado a partir do {@link Payment}.
     */
    public PaymentEntity toEntity(final Payment payment) {
        return new PaymentEntity(
            payment.getPlasticNumber().get(),
            payment.getPersonType(),
            payment.getCpfCnpj().get(),
            payment.getExpirationDate().get().toString(),
            payment.getCvv().get(),
            payment.getAmount().get()
        );
    }

    /**
     * Converte um objeto {@link PaymentEntity} para um objeto de domínio
     * {@link Payment}.
     * 
     * @param entity O objeto {@link PaymentEntity} a ser convertido.
     * @return O objeto {@link Payment} gerado a partir do {@link PaymentEntity}.
     */
    public Payment toDomain(final PaymentEntity entity) {
        return Payment.builder()
        		.plasticNumber(entity.getPlasticNumber())
        		.personType(entity.getPersonType())
        		.cpfCnpj(entity.getCpfCnpj())
        		.expirationDate(entity.getExpiration())
        		.cvv(entity.getCvv())
        		.amount(entity.getAmount())
        		.build();
    }
}

package org.acme.infrastructure.persistence.dao;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;
import org.acme.common.exceptions.ApiException;
import org.acme.common.exceptions.Errors;
import org.acme.infrastructure.persistence.entity.PaymentEntity;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class PaymentDao {

    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    public PaymentEntity create(PaymentEntity payment) {
        try {
            entityManager.merge(payment);
        } catch (PersistenceException e) {
            throw new ApiException(Errors.PERSISTENCE_EXCEPTION.getMessage());
        }
        return payment;
    }

    public List<PaymentEntity> read() {
        return entityManager.createQuery("SELECT p FROM Payment p", PaymentEntity.class)
                .getResultList();
    }

    public PaymentEntity findById(Long paymentId) {
        var payment = entityManager.find(PaymentEntity.class, paymentId);
        if (payment == null) throw new ApiException(Errors.PAYMENT_NOT_FOUND.getExceptionWithArgs(paymentId));
        return payment;
    }
}

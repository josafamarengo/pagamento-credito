package org.acme.repositories;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.acme.models.Payment;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class PaymentRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    public Payment create(Payment payment) {
        entityManager.merge(payment);
        return payment;
    }

    public List<Payment> read() {
        return entityManager.createQuery("SELECT p FROM Payment p", Payment.class)
                .getResultList();
    }

    public Optional<Payment> findById(UUID paymentId) {
        return Optional.ofNullable(entityManager.find(Payment.class, paymentId));
    }

    @Transactional
    public void delete(UUID paymentId) {
        Payment payment = entityManager.find(Payment.class, paymentId);
        if (payment != null) {
            entityManager.remove(payment);
        } else {
            throw new IllegalArgumentException("Payment not found with ID: " + paymentId);
        }
    }
}

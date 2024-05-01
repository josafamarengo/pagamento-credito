package org.acme.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.models.Payment;
import org.acme.repositories.PaymentRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class PaymentService {

    @Inject
    PaymentRepository paymentRepository;

    public Payment create(Payment payment) {
        return paymentRepository.create(payment);
    }

    public List<Payment> findAll() {
        return Payment.listAll();
    }

    public void delete(UUID paymentId) {
        paymentRepository.delete(paymentId);
    }

}

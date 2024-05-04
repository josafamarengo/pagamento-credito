package org.acme.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.models.Payment;
import org.acme.repositories.PaymentRepository;
import org.acme.validators.PaymentValidator;

import java.util.List;

@ApplicationScoped
public class PaymentService {

    @Inject
    PaymentRepository paymentRepository;

    @Inject
    PaymentValidator paymentValidator;

    public Payment create(Payment payment) {
        paymentValidator.validate(payment);
        return paymentRepository.create(payment);
    }

    public List<Payment> findAll() {
        return paymentRepository.read();
    }

    public Payment findById(Long paymentId) {
        return paymentRepository.findById(paymentId);
    }

    public void delete(Long paymentId) {
        paymentRepository.delete(paymentId);
    }

}

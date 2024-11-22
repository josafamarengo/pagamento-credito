package org.acme.domain.contracts;

import org.acme.domain.model.Payment;

import java.util.List;

public interface IPaymentRepository {
    void create(Payment payment);
    List<Payment> findAll();
    Payment findById(Long paymentId);
}

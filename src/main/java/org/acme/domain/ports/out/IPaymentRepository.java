package org.acme.domain.ports.out;

import org.acme.domain.model.Payment;

import java.util.List;

public interface IPaymentRepository {
    void create(final Payment payment);
    List<Payment> findAll();
    Payment findById(final Long paymentId);
}

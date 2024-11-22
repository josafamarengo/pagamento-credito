package org.acme.domain.ports.in;

import org.acme.domain.model.Payment;

public interface CreatePayment {
    void create(Payment payment);
}

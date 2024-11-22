package org.acme.domain.use_case;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.domain.model.Payment;
import org.acme.infrastructure.persistence.repository.PaymentRepository;

@ApplicationScoped
public class Pay {

    @Inject
    PaymentRepository paymentRepository;

    public void execute(Payment payment) {
        paymentRepository.create(payment);
    }
}

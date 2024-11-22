package org.acme.domain.use_case;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.domain.model.Payment;
import org.acme.domain.ports.in.CreatePayment;
import org.acme.infrastructure.persistence.repository.PaymentRepository;

@ApplicationScoped
public class CreatePaymentUseCase implements CreatePayment {

    @Inject
    PaymentRepository paymentRepository;

    @Override
    public void create(Payment payment) {
        paymentRepository.create(payment);
    }
}

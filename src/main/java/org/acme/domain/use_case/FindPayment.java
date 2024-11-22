package org.acme.domain.use_case;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.domain.contracts.IPaymentRepository;
import org.acme.domain.model.Payment;

@ApplicationScoped
public class FindPayment {

    @Inject
    IPaymentRepository repository;

    public Payment execute(Long id) {
        return repository.findById(id);
    }
}

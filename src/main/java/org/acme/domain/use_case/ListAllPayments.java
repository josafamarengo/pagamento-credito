package org.acme.domain.use_case;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.domain.contracts.IPaymentRepository;
import org.acme.domain.model.Payment;

import java.util.List;

@ApplicationScoped
public class ListAllPayments {

    @Inject
    IPaymentRepository repository;

    public List<Payment> execute() {
        return repository.findAll();
    }
}

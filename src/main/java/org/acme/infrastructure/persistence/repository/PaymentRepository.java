package org.acme.infrastructure.persistence.repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.domain.contracts.IPaymentRepository;
import org.acme.domain.model.Payment;
import org.acme.infrastructure.persistence.entity.PaymentEntity;
import org.acme.infrastructure.persistence.dao.PaymentDao;
import org.acme.infrastructure.persistence.mapper.PaymentEntityMapper;

import java.util.List;

@ApplicationScoped
public class PaymentRepository  implements IPaymentRepository {

    @Inject
    PaymentDao dao;

    @Inject
    PaymentEntityMapper mapper;

    @Override
    public void create(Payment payment) {
        dao.create(mapper.toEntity(payment));
    }

    @Override
    public List<Payment> findAll() {
        List<PaymentEntity> payments = dao.read();
        return payments.stream().map(p -> mapper.toDomain(p)).toList();
    }

    @Override
    public Payment findById(Long paymentId) {
        var payment = dao.findById(paymentId);
        return mapper.toDomain(payment);
    }
}

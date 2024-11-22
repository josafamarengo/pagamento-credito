package org.acme.api;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.acme.api.dto.PaymentRequest;
import org.acme.domain.model.Payment;
import org.acme.domain.use_case.FindPayment;
import org.acme.domain.use_case.ListAllPayments;
import org.acme.domain.use_case.Pay;
import org.acme.infrastructure.persistence.entity.PaymentEntity;
import org.acme.infrastructure.persistence.repository.PaymentRepository;

import java.util.List;

@Path("/pagamentos")
@Produces(value = MediaType.APPLICATION_JSON)
@RequestScoped
public class PaymentResource {

    @Inject
    PaymentRequestMapper mapper;

    @Inject
    Pay payUseCase;

    @Inject
    ListAllPayments listAllPayments;

    @Inject
    FindPayment findPayment;

    @POST
    @Consumes(value = MediaType.APPLICATION_JSON)
    public void createPayment(@Valid PaymentRequest request) {
        payUseCase.execute(mapper.toDomain(request));
    }

    @GET
    public List<Payment> getPayments() {
        return listAllPayments.execute();
    }

    @GET
    @Path("/{id}")
    public Payment findPayment(@PathParam("id") Long id) {
        return findPayment.execute(id);
    }
}

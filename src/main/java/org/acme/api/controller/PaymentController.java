package org.acme.api.controller;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import org.acme.api.dto.PaymentRequest;
import org.acme.api.mapper.PaymentRequestMapper;
import org.acme.domain.model.Payment;
import org.acme.domain.ports.in.CreatePayment;
import org.acme.domain.use_case.FindPayment;
import org.acme.domain.use_case.ListAllPayments;

import java.util.List;

@Path("/pagamentos")
@Produces(value = MediaType.APPLICATION_JSON)
@RequestScoped
public class PaymentController {

    @Inject
    PaymentRequestMapper mapper;

    @Inject
    CreatePayment createPaymentUseCase;

    @Inject
    ListAllPayments listAllPayments;

    @Inject
    FindPayment findPayment;

    @POST
    @Consumes(value = MediaType.APPLICATION_JSON)
    public void createPayment(@Valid PaymentRequest request) {
        createPaymentUseCase.create(mapper.toDomain(request));
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

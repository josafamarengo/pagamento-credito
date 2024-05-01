package org.acme.controllers;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.models.Payment;
import org.acme.services.PaymentService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Path("pagamentos")
@Consumes(value = MediaType.APPLICATION_JSON)
@Produces(value = MediaType.APPLICATION_JSON)
public class PaymentController {

    @Inject
    PaymentService paymentService;

    @POST
    public Response create(Payment payment) {
        paymentService.create(payment);
        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    public Response read() {
        List<Payment> payments = paymentService.findAll();
        return Response.ok().entity(payments).build();
    }

    @DELETE
    @Path("{idPagamento}")
    public Response delete(@PathParam(value="idPagamento") UUID paymentId) {
        paymentService.delete(paymentId);
        return Response.ok().build();
    }
}

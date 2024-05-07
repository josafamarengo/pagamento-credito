package org.acme.rest;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.models.Payment;
import org.acme.services.PaymentService;

import java.util.List;

@Path("pagamentos")
@Consumes(value = MediaType.APPLICATION_JSON)
@Produces(value = MediaType.APPLICATION_JSON)
public class PaymentResource {

    @Inject
    PaymentService paymentService;

    @POST
    public Response create(Payment payment) {
        paymentService.create(payment);
        try {
            return Response.status(Response.Status.CREATED).build();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Dados inválidos");
        } catch (Exception e) {
            throw new RuntimeException("Erro");
        }
    }

    @GET
    public Response read() {
        List<Payment> payments = paymentService.findAll();
        return Response.ok().entity(payments).build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        Payment payment = paymentService.findById(id);

        if (payment == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            return Response.ok(payment).build();
        }
    }

    @DELETE
    @Path("{idPagamento}")
    public Response delete(@PathParam(value="idPagamento") Long paymentId) {
        paymentService.delete(paymentId);
        return Response.ok().build();
    }
}

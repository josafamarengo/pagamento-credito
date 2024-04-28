package org.acme.controllers;

import org.acme.commons.Month;
import org.acme.commons.PersonType;
import org.acme.models.Cpf;
import org.acme.models.Payment;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.Year;
import java.util.List;

@Path("/v1/pagamentos")
@Produces(MediaType.APPLICATION_JSON)
public class PaymentController {
    @GET
    public Response getPayments() {
        var payment = Payment.builder()
                .paymentNumber("1")
                .plasticNumber("1234123412341234")
                .personType(PersonType.PESSOA_FISICA)
                .cpfOrCnpj(new Cpf("123456789"))
                .expirationMonth(Month.ABRIL)
                .expirationYear(Year.of(2026))
                .cvv("1234")
                .build();

        return Response.ok(List.of(payment)).build();
    }
}

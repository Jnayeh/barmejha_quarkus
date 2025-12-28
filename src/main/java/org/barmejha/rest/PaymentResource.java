package org.barmejha.rest;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.AllArgsConstructor;
import org.barmejha.domain.entities.Payment;
import org.barmejha.services.PaymentService;

import java.util.List;

@Path("/payments")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@AllArgsConstructor
@ApplicationScoped
public class PaymentResource {

  private final PaymentService paymentService;

  @GET
  public Uni<List<Payment>> getAllPayments() {
    return paymentService.getAll();
  }

  @GET
  @Path("/{id}")
  public Uni<Payment> getPaymentById(@PathParam("id") Long id) {
    return paymentService.getById(id);
  }

  @POST

  public Uni<Response> createPayment(Payment payment) {
    return paymentService.create(payment);
  }

  @PUT
  @Path("/{id}")

  public Uni<Payment> updatePayment(@PathParam("id") Long id, Payment updatedPayment) {
    return paymentService.update(id, updatedPayment);
  }

  @DELETE
  @Path("/{id}")

  public Uni<Boolean> deletePayment(@PathParam("id") Long id) {
    return paymentService.delete(id);
  }
}
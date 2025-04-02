package org.barmejha.rest;

import io.smallrye.mutiny.Uni;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
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
  @Transactional
  public Uni<Response> createPayment(Payment payment) {
    return paymentService.create(payment);
  }

  @PUT
  @Path("/{id}")
  @Transactional
  public Uni<Payment> updatePayment(@PathParam("id") Long id, Payment updatedPayment) {
    return paymentService.update(id, updatedPayment);
  }

  @DELETE
  @Path("/{id}")
  @Transactional
  public Uni<Boolean> deletePayment(@PathParam("id") Long id) {
   return paymentService.delete(id);
  }
}
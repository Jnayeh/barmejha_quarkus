package org.barmejha.rest;

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
import org.barmejha.repositories.PaymentRepository;

import java.util.List;

@Path("/payments")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@AllArgsConstructor
public class PaymentResource {

  private final PaymentRepository paymentRepository;

  @GET
  public List<Payment> getAllPayments() {
    return paymentRepository.listAll();
  }

  @GET
  @Path("/{id}")
  public Payment getPaymentById(@PathParam("id") Long id) {
    return paymentRepository.findById(id);
  }

  @POST
  @Transactional
  public Response createPayment(Payment payment) {
    paymentRepository.persist(payment);
    return Response.status(201).entity(payment).build();
  }

  @PUT
  @Path("/{id}")
  @Transactional
  public Payment updatePayment(@PathParam("id") Long id, Payment updatedPayment) {
    Payment payment = paymentRepository.findById(id);
    if (payment != null) {
      payment.setAmount(updatedPayment.getAmount());
      payment.setCurrency(updatedPayment.getCurrency());
      payment.setStatus(updatedPayment.getStatus());
      payment.setTransactionId(updatedPayment.getTransactionId());
    }
    return payment;
  }

  @DELETE
  @Path("/{id}")
  @Transactional
  public void deletePayment(@PathParam("id") Long id) {
    paymentRepository.deleteById(id);
  }
}
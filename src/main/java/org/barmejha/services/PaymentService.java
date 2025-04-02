package org.barmejha.services;

import io.quarkus.hibernate.reactive.panache.common.WithSession;
import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import lombok.AllArgsConstructor;
import org.barmejha.domain.entities.Payment;
import org.barmejha.repositories.PaymentRepository;
import org.barmejha.services.utils.ServiceUtils;

import java.util.List;

@ApplicationScoped
@AllArgsConstructor
public class PaymentService {

  private final PaymentRepository paymentRepository;

  @WithSession
  public Uni<List<Payment>> getAll() {
    return paymentRepository.listAll();
  }

  @WithSession
  public Uni<Payment> getById(Long id) {
    return paymentRepository.findById(id);
  }

  @Transactional
  @WithTransaction
  public Uni<Response> create(Payment payment) {
    return paymentRepository.persist(payment).map(ServiceUtils::okResponse);
  }

  @Transactional
  @WithTransaction
  public Uni<Payment> update(Long id, Payment updatedPayment) {
    return paymentRepository.findById(id).onItem().transform(payment -> {
      if (payment != null) {
        payment.setAmount(updatedPayment.getAmount());
        payment.setCurrency(updatedPayment.getCurrency());
        payment.setStatus(updatedPayment.getStatus());
        payment.setTransactionId(updatedPayment.getTransactionId());
      }
      return payment;
    });
  }

  @Transactional
  @WithTransaction
  public Uni<Boolean> delete(Long id) {
    return paymentRepository.deleteById(id);
  }
}
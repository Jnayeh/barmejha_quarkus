package org.barmejha.repositories;

import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import org.barmejha.domain.entities.Payment;

@ApplicationScoped
public class PaymentRepository implements PanacheRepository<Payment> {
  public Uni<Payment> findByTransactionId(String transactionId) {
    return find("transactionId", transactionId).firstResult();
  }
}
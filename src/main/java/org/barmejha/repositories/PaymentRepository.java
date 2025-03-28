package org.barmejha.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.barmejha.domain.entities.Payment;

import java.util.Optional;

@ApplicationScoped
public class PaymentRepository implements PanacheRepository<Payment> {
  public Optional<Payment> findByTransactionId(String transactionId) {
    return find("transactionId", transactionId).firstResultOptional();
  }
}
package org.barmejha.domain.entities;

import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.barmejha.domain.enums.PaymentStatus;
import org.barmejha.domain.id.ULID;

import java.math.BigDecimal;
import java.time.Instant;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Valid
@Entity
@Table(name = "payments")
public class Payment extends PanacheEntityBase {

  @Id
  @ULID
  private String id;

  @OneToOne
  @JoinColumn(name = "plan_id")
  private Plan plan;

  @Column(nullable = false)
  private BigDecimal amount;

  private String currency;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private PaymentStatus status;

  @Column(name = "transaction_id", unique = true)
  private String transactionId;

  @Column(name = "processed_at", nullable = false)
  private Instant processedAt;


  @PrePersist
  void onCreate() {
    this.processedAt = Instant.now();
    // Populate createdBy from security context
  }
}
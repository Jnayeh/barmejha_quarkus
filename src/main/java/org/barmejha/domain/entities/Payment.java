package org.barmejha.domain.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;
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
  @JsonBackReference
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
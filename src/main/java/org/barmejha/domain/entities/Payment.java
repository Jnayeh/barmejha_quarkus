package org.barmejha.domain.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.barmejha.domain.enums.PaymentStatus;
import org.barmejha.domain.idgenerator.ULID;

import java.math.BigDecimal;
import java.time.Instant;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "payments")
public class Payment extends PanacheEntityBase {

  @Id
  @ULID
  public String id;

  @OneToOne
  @JoinColumn(name = "plan_id")
  public Plan plan;

  @Column(nullable = false)
  public BigDecimal amount;

  public String currency;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  public PaymentStatus status;

  @Column(unique = true)
  public String transactionId;

  @Column(nullable = false)
  public Instant processedAt;


  @PrePersist
  void onCreate() {
    this.processedAt = Instant.now();
    // Populate createdBy from security context
  }
}
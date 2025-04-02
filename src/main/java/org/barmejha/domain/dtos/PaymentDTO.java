package org.barmejha.domain.dtos;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.barmejha.domain.enums.PaymentStatus;

import java.math.BigDecimal;
import java.time.Instant;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Valid
public class PaymentDTO {

  private String id;

  private PlanDTO plan;

  private BigDecimal amount;

  private String currency;

  private PaymentStatus status;

  private String transactionId;

  private Instant processedAt;

}
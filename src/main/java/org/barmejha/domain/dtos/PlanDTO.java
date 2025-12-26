package org.barmejha.domain.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.*;
import org.barmejha.domain.dtos.utils.DTOUtils;
import org.barmejha.domain.entities.Plan;
import org.barmejha.domain.enums.PaymentType;
import org.barmejha.domain.enums.PlanStatus;
import org.barmejha.domain.enums.PlanType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Valid
public class PlanDTO {

  private Long id;

  private ScheduleDTO schedule;

  private UserDTO client;

  private LocalDateTime startTime;

  private LocalDateTime endTime;

  private PlanStatus status;

  private BigDecimal finalPrice;

  @Builder.Default
  private PlanType type = PlanType.PRIVATE;

  @Builder.Default
  private PaymentType paymentType = PaymentType.CREATOR;

  private String title;

  private String description;

  private Set<UserDTO> participants;

  private Set<CommentDTO> comments;

  @Min(1)
  @Builder.Default
  private int minParticipants = 1;

  private int maxParticipants;

  public static PlanDTO fromEntity(Plan entity, List<String> joins, String lang) {
    if (entity == null) return null;

    return new PlanDTO(
        entity.getId(),
        ScheduleDTO.fromEntity(entity.getSchedule(), joins, lang),
        UserDTO.fromEntity(entity.getClient(), joins, lang),
        entity.getStartTime(),
        entity.getEndTime(),
        entity.getStatus(),
        entity.getFinalPrice(),
        entity.getType(),
        entity.getPaymentType(),
        entity.getTitle(),
        entity.getDescription(),
        DTOUtils.mapToSetIfInitialized(entity.getParticipants(), p -> UserDTO.fromEntity(p, joins, lang)),
        DTOUtils.mapToSetIfInitialized(entity.getComments(), c -> CommentDTO.fromEntity(c, joins, lang)),
        entity.getMinParticipants(),
        entity.getMaxParticipants()
    );
  }
}
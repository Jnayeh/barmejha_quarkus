package org.barmejha.domain.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.barmejha.domain.dtos.utils.DTOUtils;
import org.barmejha.domain.entities.Plan;
import org.barmejha.domain.enums.PaymentType;
import org.barmejha.domain.enums.PlanStatus;
import org.barmejha.domain.enums.PlanType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Valid
public record PlanDTO(
    Long id,
    ScheduleDTO schedule,
    UserDTO client,
    LocalDateTime startTime,
    LocalDateTime endTime,
    PlanStatus status,
    BigDecimal finalPrice,

    @NotNull PlanType type,
    @NotNull PaymentType paymentType,
    String title,
    String description,
    Set<UserDTO> participants,
    Set<CommentDTO> comments,
    @Min(1) Integer minParticipants,
    int maxParticipants
) {

  public PlanDTO {
    // Compact constructor for validation and defaults
    if (minParticipants == null) {
      minParticipants = 1;
    }
    if (type == null) {
      type = PlanType.PRIVATE;
    }
    if (paymentType == null) {
      paymentType = PaymentType.CREATOR;
    }
  }

  public static PlanDTO fromEntity(Plan entity, List<String> joins, String lang) {
    if (entity == null) return null;

    return new PlanDTO(
        entity.getId(),
        joins.contains("schedule") ? ScheduleDTO.fromEntity(entity.getSchedule(), joins, lang) : null,
        UserDTO.fromEntity(entity.getClient(), joins, lang),
        entity.getStartTime(), entity.getEndTime(),
        entity.getStatus(),
        entity.getFinalPrice(),
        entity.getType(),
        entity.getPaymentType(),
        entity.getTitle(), entity.getDescription(),
        DTOUtils.mapToSetIfInitialized(entity.getParticipants(), p -> UserDTO.fromEntity(p, joins, lang)),
        DTOUtils.mapToSetIfInitialized(entity.getComments(), c -> CommentDTO.fromEntity(c, joins, lang)),
        entity.getMinParticipants(),
        entity.getMaxParticipants()
    );
  }
}
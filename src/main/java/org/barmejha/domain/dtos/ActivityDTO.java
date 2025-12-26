package org.barmejha.domain.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.barmejha.domain.dtos.utils.DTOUtils;
import org.barmejha.domain.entities.Activity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Valid
public class ActivityDTO {

  private Long id;

  @NotNull
  private String name;

  private String description;

  private List<MediaContentDTO> media;

  @NotNull
  private int duration;

  @NotNull
  private BigDecimal basePrice;

  private BigDecimal discountPrice;

  private UserDTO provider;

  private Set<TagDTO> tags;

  private List<ScheduleDTO> schedules;

  private List<ExperienceDTO> experiences;

  private LocationDTO location;

  private Set<CategoryDTO> categories;

  public static ActivityDTO fromEntity(Activity entity, List<String> joins, String lang) {
    if (entity == null) return null;

    return ActivityDTO.builder()
        .id(entity.getId())
        .name(entity.getName())
        .description(entity.getDescription())
        .media(DTOUtils.mapIfInitialized(entity.getMedia(), m -> MediaContentDTO.fromEntity(m, joins, lang)))
        .duration(entity.getDuration())
        .basePrice(entity.getBasePrice())
        .discountPrice(entity.getDiscountPrice())
        .provider(joins.contains("provider") ? UserDTO.fromEntity(entity.getProvider(), joins, lang): null)
        .location(joins.contains("location") ? LocationDTO.fromEntity(entity.getLocation(), joins, lang): null)
        .tags(DTOUtils.mapToSetIfInitialized(entity.getTags(), t -> TagDTO.fromEntity(t, joins, lang)))
        .schedules(DTOUtils.mapIfInitialized(entity.getSchedules(), s -> ScheduleDTO.fromEntity(s, joins, lang)))
        .experiences(DTOUtils.mapIfInitialized(entity.getExperiences(), e -> ExperienceDTO.fromEntity(e,  joins, lang)))
        .categories(DTOUtils.mapToSetIfInitialized(entity.getCategories(), c -> CategoryDTO.fromEntity(c, joins, lang)))
        .build();
  }

  public static Set<ActivityDTO> mapToSetIfInitialized(Set<Activity> entities, List<String> joins, String lang) {
    return DTOUtils.mapToSetIfInitialized(entities, e -> fromEntity(e, joins, lang));
  }

  public static List<ActivityDTO> mapToListIfInitialized(List<Activity> entities, List<String> joins, String lang) {
    return DTOUtils.mapIfInitialized(entities, e -> fromEntity(e, joins, lang));
  }
}
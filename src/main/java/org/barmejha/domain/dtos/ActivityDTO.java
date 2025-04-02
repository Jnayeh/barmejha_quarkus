package org.barmejha.domain.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
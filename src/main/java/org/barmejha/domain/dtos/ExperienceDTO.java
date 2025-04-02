package org.barmejha.domain.dtos;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Valid
public class ExperienceDTO {

  private Long id;

  private ActivityDTO activity;

  private UserDTO client;

  private List<MediaContentDTO> media;

  private String review;

  @Range(min = 1, max = 5)
  private int rating;
}
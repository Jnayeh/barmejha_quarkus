package org.barmejha.domain.dtos;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Valid
public class CategoryDTO {

  private Long id;

  private String name;

  private MediaContentDTO media;

  private String hexColor;

  private Set<ActivityDTO> activities;
}
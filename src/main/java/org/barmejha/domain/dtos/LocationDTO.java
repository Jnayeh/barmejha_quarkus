package org.barmejha.domain.dtos;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Valid
public class LocationDTO {

  private Long id;

  private String name;

  private String address;

  private Double latitude;

  private Double longitude;

  private List<ActivityDTO> activities;
}
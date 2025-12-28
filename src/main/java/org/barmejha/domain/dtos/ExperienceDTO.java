package org.barmejha.domain.dtos;

import jakarta.validation.Valid;
import lombok.*;
import org.barmejha.domain.dtos.utils.DTOUtils;
import org.barmejha.domain.entities.Experience;
import org.hibernate.validator.constraints.Range;

import java.util.List;
import java.util.Set;

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

  public static ExperienceDTO fromEntity(Experience entity, List<String> joins, String lang) {
    if (entity == null) return null;

    return new ExperienceDTO(
        entity.getId(),
        joins.contains("activity") ? ActivityDTO.fromEntity(entity.getActivity(), joins, lang): null,
        UserDTO.fromEntity(entity.getClient(), joins, lang),
        DTOUtils.mapIfInitialized(entity.getMedia(), m -> MediaContentDTO.fromEntity(m, joins, lang)),
        entity.getReview(),
        entity.getRating()
    );
  }

  public static Set<ExperienceDTO> mapToSetIfInitialized(Set<Experience> entities, List<String> joins, String lang) {
    return DTOUtils.mapToSetIfInitialized(entities, e -> fromEntity(e,  joins, lang));
  }

  public static List<ExperienceDTO> mapToListIfInitialized(List<Experience> entities, List<String> joins, String lang) {
    return DTOUtils.mapIfInitialized(entities, e -> fromEntity(e,  joins, lang));
  }
}
package org.barmejha.domain.dtos;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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

  public static ExperienceDTO fromEntity(Experience entity, String lang) {
    if (entity == null) return null;

    return new ExperienceDTO(
        entity.getId(),
        ActivityDTO.fromEntity(entity.getActivity(), lang),
        UserDTO.fromEntity(entity.getClient(), lang),
        DTOUtils.mapIfInitialized(entity.getMedia(), m -> MediaContentDTO.fromEntity(m, lang)),
        entity.getReview(),
        entity.getRating()
    );
  }

  public static Set<ExperienceDTO> mapToSetIfInitialized(Set<Experience> entities, String lang) {
    return DTOUtils.mapToSetIfInitialized(entities, e -> fromEntity(e, lang));
  }

  public static List<ExperienceDTO> mapToListIfInitialized(List<Experience> entities, String lang) {
    return DTOUtils.mapIfInitialized(entities, e -> fromEntity(e, lang));
  }
}
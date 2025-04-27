package org.barmejha.domain.dtos;

import org.barmejha.domain.dtos.utils.DTOUtils;
import org.barmejha.domain.entities.Category;

import java.util.List;
import java.util.Set;

public record CategoryDTO(
    Long id,
    String name,
    MediaContentDTO media,
    String hexColor
) {
  public static CategoryDTO fromEntity(Category entity, String lang) {
    if (entity == null) return null;

    return new CategoryDTO(
        entity.getId(),
        entity.getName(),
        MediaContentDTO.fromEntity(entity.getMedia(), lang),
        entity.getHexColor()
    );
  }

  public static Set<CategoryDTO> mapToSetIfInitialized(Set<Category> entities, String lang) {
    return DTOUtils.mapToSetIfInitialized(entities, e -> fromEntity(e, lang));
  }

  public static List<CategoryDTO> mapToListIfInitialized(List<Category> entities, String lang) {
    return DTOUtils.mapIfInitialized(entities, e -> fromEntity(e, lang));
  }
}
package org.barmejha.domain.dtos;

import org.barmejha.domain.dtos.utils.DTOUtils;
import org.barmejha.domain.entities.Tag;
import org.barmejha.domain.enums.TagType;

import java.util.List;
import java.util.Set;

public record TagDTO(
    Long id,
    String name,
    TagType type
) {
  public static TagDTO fromEntity(Tag entity, String lang) {
    if (entity == null) return null;

    return new TagDTO(
        entity.getId(),
        entity.getName(),
        entity.getType()
    );
  }

  public static Set<TagDTO> mapToSetIfInitialized(Set<Tag> entities, String lang) {
    return DTOUtils.mapToSetIfInitialized(entities, e -> fromEntity(e, lang));
  }

  public static List<TagDTO> mapToListIfInitialized(List<Tag> entities, String lang) {
    return DTOUtils.mapIfInitialized(entities, e -> fromEntity(e, lang));
  }
}
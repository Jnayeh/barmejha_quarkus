package org.barmejha.domain.dtos;

import org.barmejha.domain.entities.media.MediaContent;
import org.barmejha.domain.enums.MediaType;

import java.util.List;

public record MediaContentDTO(
    Long id,
    String url,
    MediaType type
) {
  public static MediaContentDTO fromEntity(MediaContent entity, String lang) {
    if (entity == null) return null;

    return new MediaContentDTO(
        entity.getId(),
        entity.getUrl(),
        entity.getType()
    );
  }

  public static List<MediaContentDTO> fromEntities(List<MediaContent> entities, String lang) {
    if (entities == null) return List.of();
    return entities.stream()
        .map(entity -> fromEntity(entity, lang))
        .toList();
  }
}
package org.barmejha.domain.dtos;

import org.barmejha.domain.dtos.utils.DTOUtils;
import org.barmejha.domain.entities.communities.Community;

import java.util.List;
import java.util.Set;

public record CommunityDTO(
    Long id,
    String name,
    String description,
    List<PostDTO> posts
) {
  public static CommunityDTO fromEntity(Community entity, String lang) {
    if (entity == null) return null;

    return new CommunityDTO(
        entity.getId(),
        entity.getName(),
        entity.getDescription(),
        DTOUtils.mapIfInitialized(entity.getPosts(), p -> PostDTO.fromEntity(p, lang))
    );
  }

  public static Set<CommunityDTO> mapToSetIfInitialized(Set<Community> entities, String lang) {
    return DTOUtils.mapToSetIfInitialized(entities, e -> fromEntity(e, lang));
  }

  public static List<CommunityDTO> mapToListIfInitialized(List<Community> entities, String lang) {
    return DTOUtils.mapIfInitialized(entities, e -> fromEntity(e, lang));
  }
}


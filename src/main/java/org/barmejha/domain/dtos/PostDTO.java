package org.barmejha.domain.dtos;

import org.barmejha.domain.dtos.utils.DTOUtils;
import org.barmejha.domain.entities.communities.Post;

import java.util.List;
import java.util.Set;

public record PostDTO(
    Long id,
    CommunityDTO community,
    UserDTO author,
    String title,
    String content,
    List<CommentDTO> comments
) {
  public static PostDTO fromEntity(Post entity, List<String> joins, String lang) {
    if (entity == null) return null;

    return new PostDTO(
        entity.getId(),
        joins.contains("community") ? CommunityDTO.fromEntity(entity.getCommunity(), joins, lang): null,
        UserDTO.fromEntity(entity.getAuthor(), joins, lang),
        entity.getTitle(),
        entity.getContent(),
        DTOUtils.mapIfInitialized(entity.getComments(), c -> CommentDTO.fromEntity(c, joins, lang))
    );
  }

  public static Set<PostDTO> mapToSetIfInitialized(Set<Post> entities, List<String> joins, String lang) {
    return DTOUtils.mapToSetIfInitialized(entities, e -> fromEntity(e,  joins, lang));
  }

  public static List<PostDTO> mapToListIfInitialized(List<Post> entities, List<String> joins, String lang) {
    return DTOUtils.mapIfInitialized(entities, e -> fromEntity(e,  joins, lang));
  }
}
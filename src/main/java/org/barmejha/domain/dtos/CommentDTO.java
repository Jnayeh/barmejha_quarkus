package org.barmejha.domain.dtos;

import org.barmejha.domain.entities.communities.Comment;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public record CommentDTO(
    Long id,
    PostDTO post,
    PlanDTO plan,
    UserDTO author,
    String content
) {
  public static CommentDTO fromEntity(Comment entity, List<String> joins, String lang) {
    if (entity == null) return null;

    return new CommentDTO(
        entity.getId(),
        PostDTO.fromEntity(entity.getPost(), joins, lang),
        PlanDTO.fromEntity(entity.getPlan(), joins, lang),
        UserDTO.fromEntity(entity.getAuthor(), joins, lang),
        entity.getContent()
    );
  }

  public static Set<CommentDTO> mapToSetIfInitialized(Set<Comment> entities, List<String> joins, String lang) {
    return entities == null ? Set.of() : entities.stream()
        .map(e -> fromEntity(e,  joins, lang))
        .collect(Collectors.toSet());
  }

  public static List<CommentDTO> mapToListIfInitialized(List<Comment> entities, List<String> joins, String lang) {
    return entities == null ? List.of() : entities.stream()
        .map(e -> fromEntity(e,  joins, lang))
        .toList();
  }
}
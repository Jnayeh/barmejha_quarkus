package org.barmejha.domain.mappers;

import org.barmejha.domain.dtos.CommentDTO;
import org.barmejha.domain.entities.communities.Comment;
import org.barmejha.domain.mappers.config.MappingConfig;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(
    config = MappingConfig.class,
    uses = {
        PostMapper.class,
        PlanMapper.class,
        UserMapper.class
    }
)
public interface CommentMapper {
  CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

  // Entity → DTO
  CommentDTO toDTO(Comment entity, String lang);

  // DTO → Entity
  Comment toEntity(CommentDTO dto);
}
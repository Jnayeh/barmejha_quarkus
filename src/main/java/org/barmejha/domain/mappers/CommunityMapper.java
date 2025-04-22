package org.barmejha.domain.mappers;

import org.barmejha.domain.dtos.CommentDTO;
import org.barmejha.domain.entities.communities.Community;
import org.barmejha.domain.mappers.config.MappingConfig;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(
    config = MappingConfig.class,
    uses = {PostMapper.class}
)
public interface CommunityMapper {
  CommunityMapper INSTANCE = Mappers.getMapper(CommunityMapper.class);

  // Entity → DTO
  CommentDTO toDTO(Community entity, String lang);

  // DTO → Entity
  Community toEntity(CommentDTO dto);
}
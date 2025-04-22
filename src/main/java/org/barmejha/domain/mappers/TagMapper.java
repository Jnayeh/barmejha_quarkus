package org.barmejha.domain.mappers;

import org.barmejha.domain.dtos.TagDTO;
import org.barmejha.domain.entities.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TagMapper {
  TagMapper INSTANCE = Mappers.getMapper(TagMapper.class);

  // Entity → DTO
  TagDTO toDTO(Tag entity, String lang);

  // DTO → Entity
  Tag toEntity(TagDTO dto);
}
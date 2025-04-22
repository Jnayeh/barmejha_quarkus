package org.barmejha.domain.mappers;

import org.barmejha.domain.dtos.MediaContentDTO;
import org.barmejha.domain.entities.media.MediaContent;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MediaMapper {
  MediaMapper INSTANCE = Mappers.getMapper(MediaMapper.class);

  // Entity → DTO
  MediaContentDTO toDTO(MediaContent entity, String lang);

  // DTO → Entity
  MediaContent toEntity(MediaContentDTO dto);
}
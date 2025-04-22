package org.barmejha.domain.mappers;

import org.barmejha.domain.dtos.LocationDTO;
import org.barmejha.domain.entities.Location;
import org.barmejha.domain.mappers.config.MappingConfig;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(
    config = MappingConfig.class,
    uses = {ActivityMapper.class}
)
public interface LocationMapper {
  LocationMapper INSTANCE = Mappers.getMapper(LocationMapper.class);

  // Entity → DTO
  LocationDTO toDTO(Location entity, String lang);

  // DTO → Entity
  Location toEntity(LocationDTO dto);
}
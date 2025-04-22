package org.barmejha.domain.mappers;

import org.barmejha.domain.dtos.ScheduleDTO;
import org.barmejha.domain.entities.Schedule;
import org.barmejha.domain.mappers.config.MappingConfig;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(
    config = MappingConfig.class,
    uses = {ActivityMapper.class}
)
public interface ScheduleMapper {
  ScheduleMapper INSTANCE = Mappers.getMapper(ScheduleMapper.class);

  // Entity → DTO
  ScheduleDTO toDTO(Schedule entity, String lang);

  // DTO → Entity
  Schedule toEntity(ScheduleDTO dto);
}
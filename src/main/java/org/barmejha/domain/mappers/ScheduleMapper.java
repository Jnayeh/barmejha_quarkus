package org.barmejha.domain.mappers;

import org.barmejha.domain.dtos.ScheduleDTO;
import org.barmejha.domain.entities.Schedule;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(
    config = MappingConfig.class,
    uses = {ActivityMapper.class}
)
public interface ScheduleMapper {
    ScheduleMapper INSTANCE = Mappers.getMapper(ScheduleMapper.class);

    // Entity → DTO
    ScheduleDTO toDTO(Schedule entity);

    // DTO → Entity
    Schedule toEntity(ScheduleDTO dto);
}
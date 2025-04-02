package org.barmejha.domain.mappers;

import org.barmejha.domain.dtos.PlanDTO;
import org.barmejha.domain.entities.Plan;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(
    config = MappingConfig.class,
    uses = {
        ScheduleMapper.class,
        UserMapper.class,
        CommentMapper.class
    }
)
public interface PlanMapper {
    PlanMapper INSTANCE = Mappers.getMapper(PlanMapper.class);

    // Entity → DTO
    PlanDTO toDTO(Plan entity);

    // DTO → Entity
    Plan toEntity(PlanDTO dto);
}
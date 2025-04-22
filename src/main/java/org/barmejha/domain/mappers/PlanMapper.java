package org.barmejha.domain.mappers;

import org.barmejha.domain.dtos.PlanDTO;
import org.barmejha.domain.entities.Plan;
import org.barmejha.domain.mappers.config.MappingConfig;
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
  PlanDTO toDTO(Plan entity, String lang);

  // DTO → Entity
  Plan toEntity(PlanDTO dto);
}
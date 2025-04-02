package org.barmejha.domain.mappers;

import org.barmejha.domain.dtos.ExperienceDTO;
import org.barmejha.domain.entities.Experience;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(
    config = MappingConfig.class,
    uses = {
        ActivityMapper.class,
        UserMapper.class,
        MediaMapper.class
    }
)
public interface ExperienceMapper {
    ExperienceMapper INSTANCE = Mappers.getMapper(ExperienceMapper.class);

    // Entity → DTO
    ExperienceDTO toDTO(Experience entity);

    // DTO → Entity
    Experience toEntity(ExperienceDTO dto);
}